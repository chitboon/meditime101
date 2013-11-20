package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import util.FitbitApiAlarmAgent;
import model.AlarmData;
import model.AlarmDataAO;
import model.MedicineData;
import model.MedicineDataAO;
import model.ResourceCredentials;
import model.ResourceCredentialsAO;

import com.fitbit.api.FitbitAPIException;
import com.fitbit.api.client.FitbitAPIEntityCache;
import com.fitbit.api.client.FitbitApiClientAgent;
import com.fitbit.api.client.FitbitApiCredentialsCache;
import com.fitbit.api.client.FitbitApiCredentialsCacheMapImpl;
import com.fitbit.api.client.FitbitApiEntityCacheMapImpl;
import com.fitbit.api.client.FitbitApiSubscriptionStorage;
import com.fitbit.api.client.FitbitApiSubscriptionStorageInMemoryImpl;
import com.fitbit.api.client.LocalUserDetail;
import com.fitbit.api.client.service.FitbitAPIClientService;
import com.fitbit.api.common.model.user.UserInfo;
import com.fitbit.api.common.model.devices.*;
import com.fitbit.api.model.APIResourceCredentials;
import com.google.appengine.api.images.Image;

public class User extends HttpServlet {
	public static final String OAUTH_TOKEN = "oauth_token";
	public static final String OAUTH_VERIFIER = "oauth_verifier";

	private FitbitAPIEntityCache entityCache = new FitbitApiEntityCacheMapImpl();
	private FitbitApiCredentialsCache credentialsCache = new FitbitApiCredentialsCacheMapImpl();
	private FitbitApiSubscriptionStorage subscriptionStore = new FitbitApiSubscriptionStorageInMemoryImpl();

	private String apiBaseUrl;
	private String fitbitSiteBaseUrl;
	private String exampleBaseUrl;
	private String clientConsumerKey;
	private String clientSecret;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream(
					"config.properties"));
			apiBaseUrl = properties.getProperty("apiBaseUrl");
			fitbitSiteBaseUrl = properties.getProperty("fitbitSiteBaseUrl");
			exampleBaseUrl = properties.getProperty("exampleBaseUrl").replace(
					"/app", "");
			clientConsumerKey = properties.getProperty("clientConsumerKey");
			clientSecret = properties.getProperty("clientSecret");
		} catch (IOException e) {
			throw new ServletException("Exception during loading properties", e);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		FitbitAPIClientService<FitbitApiAlarmAgent> apiClientService = new FitbitAPIClientService<FitbitApiAlarmAgent>(
				new FitbitApiAlarmAgent(apiBaseUrl, fitbitSiteBaseUrl,
						credentialsCache), clientConsumerKey, clientSecret,
				credentialsCache, entityCache, subscriptionStore);

		ResourceCredentialsAO ao = new ResourceCredentialsAO();
		List<ResourceCredentials> list = ao.getResourceCredentials();
		ResourceCredentials rc = list.get(0);
		APIResourceCredentials resourceCredentials = new APIResourceCredentials(
				"-", "", "");
		resourceCredentials.setAccessToken(rc.getAccessToken());
		resourceCredentials.setAccessTokenSecret(rc.getAccessTokenSecret());
		resourceCredentials.setResourceId(rc.getResourceId());
		resourceCredentials.setLocalUserId(rc.getLocalUserId());
		apiClientService.saveResourceCredentials(new LocalUserDetail(
				resourceCredentials.getLocalUserId()), resourceCredentials);

		try {
			LocalUserDetail localUser = new LocalUserDetail(resourceCredentials.getLocalUserId());
			UserInfo userInfo = apiClientService.getClient().getUserInfo(localUser);
			
			List<Device> deviceList = apiClientService.getClient().getDevices(localUser);
			Device dev = deviceList.get(0);
			
			
			
			List<Alarm> alarmJson = apiClientService.getClient().getAlarms(localUser, dev.getId());

				request.setAttribute("alarms", alarmJson);
				
				MedicineDataAO md = new MedicineDataAO();
				
				List<MedicineData> medicineJson = md.getMedicineData();
			
				request.setAttribute("medicine", medicineJson);
			
			request.getRequestDispatcher("/getAlarm.jsp").forward(request, response);
			
			
		} catch (FitbitAPIException e) {
			throw new ServletException("Exception during getting user info", e);

		}
	}

	
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws IOException, ServletException {
FitbitAPIClientService<FitbitApiAlarmAgent> apiClientService = new FitbitAPIClientService<FitbitApiAlarmAgent>(
		new FitbitApiAlarmAgent(apiBaseUrl, fitbitSiteBaseUrl,
				credentialsCache), clientConsumerKey, clientSecret,
		credentialsCache, entityCache, subscriptionStore);

ResourceCredentialsAO ao = new ResourceCredentialsAO();
List<ResourceCredentials> list = ao.getResourceCredentials();
ResourceCredentials rc = list.get(0);
APIResourceCredentials resourceCredentials = new APIResourceCredentials(
		"-", "", "");
resourceCredentials.setAccessToken(rc.getAccessToken());
resourceCredentials.setAccessTokenSecret(rc.getAccessTokenSecret());
resourceCredentials.setResourceId(rc.getResourceId());
resourceCredentials.setLocalUserId(rc.getLocalUserId());
apiClientService.saveResourceCredentials(new LocalUserDetail(
		resourceCredentials.getLocalUserId()), resourceCredentials);

try {
	LocalUserDetail localUser = new LocalUserDetail(resourceCredentials.getLocalUserId());
	UserInfo userInfo = apiClientService.getClient().getUserInfo(localUser);
	
	List<Device> deviceList = apiClientService.getClient().getDevices(localUser);
	Device dev = deviceList.get(0);
	
	String alarm = request.getParameter("alarmTime");
	
	String alarm1 = null;
	
	//substring the first 2 digit for calculation
	String stHours = alarm.substring(0, 2);
	String stMin = alarm.substring(3, 5);
	
	
	//check the substring
	PrintWriter out =response.getWriter();
		
	int times = 0;
	String duration1 = request.getParameter("duration1").trim();
	int intDuration = 0;
	int dayToFinish = 0;
	String name = request.getParameter("name");
	int tablet = Integer.parseInt(request.getParameter("tablet"));
	String date = request.getParameter("date");
	
	
	//check how many alarms will there be
	if(duration1.equals("once"))
	{
		intDuration = 1;	
		dayToFinish = tablet/intDuration;
		times = 1;
	}
	
	else if(duration1.equals("twice"))
	{
		intDuration = 2;	
		dayToFinish = tablet/intDuration;
		times = 2;
		intDuration = 12;
	}
	else if (duration1.equals("thrice"))
	{
		intDuration = 3;
		dayToFinish = tablet/intDuration;
		times = 3;
		intDuration = 8;
	}
	else if(duration1.equals("thrice"))
	{
		intDuration = 4;
		dayToFinish = tablet/intDuration;
		times = 4;
		intDuration = 6;
	}
	
	
	//Declare the array
	String[] alarmArray = new String[times];
	
	//convert the first 2 digit to do calculation
	int inHours = Integer.parseInt(stHours); 
	
	String alarmJson = null;
	
	for(int i = 0; i<times; i++)
	{
		
		//convert the hours to string
		stHours = Integer.toString(inHours);
				
		//check if the hours is 1 digit
		if(inHours <10)
			alarm1 = "0" + stHours + ":" + stMin + "+08:00";
		else
			alarm1 = stHours + ":" + stMin + "+08:00";
				
		alarmArray[i] = alarm1;
		out.print(i + ": " +alarmArray[i]);
		out.println();
		
		alarmJson = apiClientService.getClient().addAlarms(localUser, dev.getId(), request, alarmArray[i]);
		
		//Add the hours into the original time
		inHours +=intDuration;
		//check if the time is pass 24
		if(inHours >=24)
			inHours = inHours - 24;

	}
	
	//ALARM
	medicineBean med = new medicineBean();
	med.setDate(date);
	med.setDuration(duration1);
	med.setName(name);
	med.setTablet(tablet);
	
	String stDay = date.substring(0, 2);
	String stMonth = date.substring(3, 5);
	String stYear = date.substring(6, 10);
	
	
	int intDay = Integer.parseInt(stDay);
	int intMonth = Integer.parseInt(stMonth);
	int intYear = Integer.parseInt(stYear);
	
	
	int finishDate = intDay + dayToFinish;
	String stFinishDate;
	
	Random randomNumber = new Random();
	long randomInt = randomNumber.nextInt(10000000);
	
//	MedicineData medDate = new MedicineData();
//	
//	medDate.setDuration(duration);
//	medDate.setName(name);
//	medDate.setStartDate(date);
//	medDate.setTablet(tablet);
//	

	 MedicineData md = new MedicineData();
     MedicineDataAO ao1 = new MedicineDataAO();


     
	if(intMonth == 1)
	{
		if(finishDate>31)
		{
		finishDate = finishDate - 31;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);			
	
		out.print(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 2)
	{
		if(finishDate>28)
		{
		finishDate = finishDate - 28;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 3)
	{
		if(finishDate>31)
		{
		finishDate = finishDate - 31;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 4)
	{
		if(finishDate>30)
		{
		finishDate = finishDate - 30;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 5)
	{
		if(finishDate>31)
		{
		finishDate = finishDate - 31;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 6)
	{
		if(finishDate>30)
		{
		finishDate = finishDate - 30;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 7)
	{
		if(finishDate>31)
		{
		finishDate = finishDate - 31;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 8)
	{
		if(finishDate>31)
		{
		finishDate = finishDate - 31;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 9)
	{
		if(finishDate>30)
		{
		finishDate = finishDate - 30;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 10)
	{
		if(finishDate>31)
		{
		finishDate = finishDate - 31;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 11)
	{
		if(finishDate>61)
		{
			finishDate = finishDate - 61;
			
			intMonth = 1;
			stYear = "2014";
			
			if(finishDate <10)
				stFinishDate = "0" + finishDate;
			else
				stFinishDate = Integer.toString(finishDate);
					
			
			request.setAttribute("intDay", finishDate);
			request.setAttribute("intMonth", intMonth);
			
			ao1.add(randomInt, name, date, stFinishDate + "/" + Integer.toString(intMonth) + "/" +  stYear, duration1, tablet);
	         request.setAttribute("id",randomInt);
	         request.setAttribute("name",name);
	         request.setAttribute("startDate",date);
	         request.setAttribute("endDate",stFinishDate + "/" + Integer.toString(intMonth) + "/" +  stYear);
	         request.setAttribute("duration",duration1);
	         request.setAttribute("tablet",tablet);
			
		}
		
		else if(finishDate>30)
		{
		finishDate = finishDate - 30;

		intMonth++;
		
		if(finishDate <10)
			stFinishDate = "0" + finishDate;
		else
			stFinishDate = Integer.toString(finishDate);
				
		
		request.setAttribute("intDay", finishDate);
		request.setAttribute("intMonth", intMonth);
		
		ao1.add(randomInt, name, date, stFinishDate + "/" + Integer.toString(intMonth) + "/" +  stYear, duration1, tablet);
         request.setAttribute("id",randomInt);
         request.setAttribute("name",name);
         request.setAttribute("startDate",date);
         request.setAttribute("endDate",stFinishDate + "/" + Integer.toString(intMonth) + "/" +  stYear);
         request.setAttribute("duration",duration1);
         request.setAttribute("tablet",tablet);
		
		
		//out.print(Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
		else
		{
			if(finishDate <10)
				stFinishDate = "0" + finishDate;
			else
				stFinishDate = Integer.toString(finishDate);
					
			request.setAttribute("intDay", finishDate);
			request.setAttribute("intMonth", intMonth);
			ao1.add(randomInt, name, date, stFinishDate + "/" + Integer.toString(intMonth) + "/" +  stYear, duration1, tablet);
	         request.setAttribute("id",randomInt);
	         request.setAttribute("name",name);
	         request.setAttribute("startDate",date);
	         request.setAttribute("endDate",stFinishDate + "/" + Integer.toString(intMonth) + "/" +  stYear);
	         request.setAttribute("duration",duration1);
	         request.setAttribute("tablet",tablet);
			
	 		//out.print(Integer.toString(finishDate) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	else if (intMonth == 12)
	{
		if(finishDate>31)
		{
		finishDate = finishDate - 31;
		intDay = finishDate;
		intMonth++;
		
		request.setAttribute("intDay", intDay);
		request.setAttribute("intMonth", intMonth);
		//medDate.setEndDate(Integer.toString(intDay) + "/" + Integer.toString(intMonth) + "/" +  stYear);
		}
	}
	
	
	
   request.getRequestDispatcher("/index.html").forward(request, response);
    
} catch (FitbitAPIException e) {
	throw new ServletException("Exception during getting user info", e);

}
}
	
	
//	protected void doDelete(HttpServletRequest request,
//			HttpServletResponse response) throws IOException, ServletException {
//		FitbitAPIClientService<FitbitApiAlarmAgent> apiClientService = new FitbitAPIClientService<FitbitApiAlarmAgent>(
//				new FitbitApiAlarmAgent(apiBaseUrl, fitbitSiteBaseUrl,
//						credentialsCache), clientConsumerKey, clientSecret,
//				credentialsCache, entityCache, subscriptionStore);
//
//		ResourceCredentialsAO ao = new ResourceCredentialsAO();
//		List<ResourceCredentials> list = ao.getResourceCredentials();
//		ResourceCredentials rc = list.get(0);
//		APIResourceCredentials resourceCredentials = new APIResourceCredentials(
//				"-", "", "");
//		resourceCredentials.setAccessToken(rc.getAccessToken());
//		resourceCredentials.setAccessTokenSecret(rc.getAccessTokenSecret());
//		resourceCredentials.setResourceId(rc.getResourceId());
//		resourceCredentials.setLocalUserId(rc.getLocalUserId());
//		apiClientService.saveResourceCredentials(new LocalUserDetail(
//				resourceCredentials.getLocalUserId()), resourceCredentials);
//
//		try {
//			LocalUserDetail localUser = new LocalUserDetail(resourceCredentials.getLocalUserId());
//			UserInfo userInfo = apiClientService.getClient().getUserInfo(localUser);
//			
//			List<Device> deviceList = apiClientService.getClient().getDevices(localUser);
//			Device dev = deviceList.get(0);
//			
//			
//			
//			String alarmId = request.getParameter("alarmId");
//			
//			PrintWriter out = response.getWriter();
//			out.print(alarmId);
//			//apiClientService.getClient().deleteAlarm(localUser,dev.getId(),alarmId);
//			
//			 //request.getRequestDispatcher("/getAlarm.jsp").forward(request, response);
//			
//		} catch (FitbitAPIException e) {
//			throw new ServletException("Exception during getting user info", e);
//
//		}
//	}
}
