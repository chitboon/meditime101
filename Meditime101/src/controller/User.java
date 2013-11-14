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
			
			
//			PrintWriter out =response.getWriter();
//			out.print(dev.getId());
//			out.println();
			
			
			String alarmJson = apiClientService.getClient().getAlarms(localUser, dev.getId());
		
			
			//break up json string
        	JSONObject jsonObject = new JSONObject(alarmJson);

        	JSONArray alarmJsonArray = jsonObject.getJSONArray("trackerAlarms");
        	
        	
        	for (int i = 0; i < alarmJsonArray.length(); i++) {

                JSONObject testJsonObject = alarmJsonArray.getJSONObject(i);
                
                long alarmId = testJsonObject.getLong("alarmId");
                boolean deleted = testJsonObject.getBoolean("deleted");
                boolean enabled = testJsonObject.getBoolean("enabled");
                boolean recurring = testJsonObject.getBoolean("recurring");
                int snoozeCount = testJsonObject.getInt("snoozeCount");
                int snoozeLength = testJsonObject.getInt("snoozeLength");
                boolean syncedToDevice = testJsonObject.getBoolean("syncedToDevice");
                String time = testJsonObject.getString("time");
                String vibe = testJsonObject.getString("vibe");

                request.setAttribute("vibe", vibe);
                request.setAttribute("deleted", deleted);
                request.setAttribute("enabled", enabled);
                request.setAttribute("recurring", recurring);
                request.setAttribute("syncedToDevice", syncedToDevice);
                request.setAttribute("time", time);
                request.setAttribute("alarmId", alarmId);
                request.setAttribute("snoozeCount", snoozeCount);
                request.setAttribute("snoozeLength", snoozeLength);
                
        	}
			
			request.setAttribute("userInfo", userInfo);
			request.setAttribute("alarmJson", alarmJson);
			
			//request.getRequestDispatcher("/getAlarm.jsp").forward(request, response);
			
			
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
	
String imageUrl = request.getParameter("inputFile");
request.setAttribute("imageUrl", imageUrl);
	
	
//	URL imageUrl = new URL(request.getParameter("inputFile"));
//    request.setAttribute("imageUrl", imageUrl);
	
	String alarm1 = null;
	
	//substring the first 2 digit for calculation
	String stHours = alarm.substring(0, 2);
	String stMin = alarm.substring(3, 5);
	
	
	//check the substring
	PrintWriter out =response.getWriter();
	out.print("stHours" + stHours + "\n");
	out.println();
	out.print("stMin" + stMin + "\n");
		
	int duration = Integer.parseInt(request.getParameter("duration"));
	int times;
	
	//check how many alarms will there be
	if(duration != 0)
		times = (24/duration);
	else
		times=1;
	
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
		inHours +=duration;
		//check if the time is pass 24
		if(inHours >=24)
			inHours = inHours - 24;

	}
	
	
	
	
//	ServletOutputStream out = response.getOutputStream();
//	String alarm = request.getParameter("alarm");
//	response.setContentType("text/html");
//	out.println("Address is: " + request.getAttribute("Address"));
	
//	out.println(alarmJson);
//	out.println(imageUrl);
//	out.close();
	
    request.getRequestDispatcher("/AlarmResult.jsp").forward(request, response);
    
} catch (FitbitAPIException e) {
	throw new ServletException("Exception during getting user info", e);

}
}
	
	protected void doDelete(HttpServletRequest request,
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
			
			String alarmId = request.getParameter("alarmId");
			
			request.getRequestDispatcher("/getAlarm.jsp").forward(request, response);
			
			
		} catch (FitbitAPIException e) {
			throw new ServletException("Exception during getting user info", e);

		}
	}
}
