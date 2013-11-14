package demo;

import com.fitbit.api.FitbitAPIException;
import com.fitbit.api.client.*;
import com.fitbit.api.client.service.FitbitAPIClientService;
import com.fitbit.api.common.model.devices.Device;
import com.fitbit.api.common.model.user.UserInfo;
import com.fitbit.api.model.APIResourceCredentials;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.ResourceCredentials;
import model.ResourceCredentialsAO;
import util.FitbitApiAlarmAgent;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Kiryl
 * Date: 6/22/11
 * Time: 7:05 AM
 */
public class FitbitApiAuthExampleServlet extends HttpServlet {

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
					
			
			request.setAttribute("userInfo", userInfo);
			
			request.getRequestDispatcher("/user.jsp").forward(request, response);
			
			
		} catch (FitbitAPIException e) {
			throw new ServletException("Exception during getting user info", e);

		}
	}
    }

