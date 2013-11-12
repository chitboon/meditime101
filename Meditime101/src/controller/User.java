package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
			
			request.getRequestDispatcher("/user.jsp").forward(request, response);
			
			
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
			
			List<Device> deviceList = apiClientService.getClient().getDevices(localUser);
			Device dev = deviceList.get(0);
			
			request.getAttribute("period");
			request.getAttribute("alarm");
			request.getAttribute("occuring");
	
			boolean recurring = true;
			boolean occuring = true;
			String alarmTime = "11:00";
			String [] weekDays = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};	

			apiClientService.getClient().addAlarms(localUser, dev.getId(), alarmTime, recurring, occuring, weekDays);
			
            request.setAttribute("alarm", alarmTime);
            request.setAttribute("recurring", recurring);
            request.setAttribute("occuring", occuring);
            request.setAttribute("weekDays", weekDays);

            request.getRequestDispatcher("/AlarmResult.jsp").forward(request, response);
            
		} catch (FitbitAPIException e) {
			throw new ServletException("Exception during getting user info", e);

		}
	}
	
}
