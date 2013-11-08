package util;

import java.util.List;

import org.json.JSONException;

import com.fitbit.api.APIUtil;
import com.fitbit.api.FitbitAPIException;
import com.fitbit.api.client.FitbitApiClientAgent;
import com.fitbit.api.client.FitbitApiCredentialsCache;
import com.fitbit.api.client.LocalUserDetail;
import com.fitbit.api.client.http.Response;
import com.fitbit.api.common.model.devices.Scale;
import com.fitbit.api.model.APIFormat;

public class FitbitApiAlarmAgent extends FitbitApiClientAgent {

	public FitbitApiAlarmAgent() {
		// TODO Auto-generated constructor stub
	}

	public FitbitApiAlarmAgent(String apiBaseUrl, String webBaseUrl,
			FitbitApiCredentialsCache credentialsCache) {
		super(apiBaseUrl, webBaseUrl, credentialsCache);
		// TODO Auto-generated constructor stub
	}

	public FitbitApiAlarmAgent(String requestTokenURL, String authorizationURL,
			String accessTokenURL) {
		super(requestTokenURL, authorizationURL, accessTokenURL);
		// TODO Auto-generated constructor stub
	}
	
    public String getAlarms(LocalUserDetail localUser, String deviceId) throws FitbitAPIException {
        setAccessToken(localUser);
        // Example: GET /1/user/-/devices/tracker/456665/alarms.json
        String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(), "/user/-/devices/tracker/"+deviceId+"/alarms", APIFormat.JSON);
        Response response = httpGet(url, true);
        throwExceptionIfError(response);
        try {
            return response.asJSONObject().toString();
        } catch (JSONException e) {
            throw new FitbitAPIException("Error parsing json response to list of Scale : ", e);
        }
    }
	
    public void addAlarms(LocalUserDetail localUser, String deviceId) throws FitbitAPIException {
    	
        setAccessToken(localUser);
        //POST /1/user/-/devices/tracker/55777/alarms.json
        String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(), "/user/-/devices/tracker/" + deviceId, APIFormat.JSON);
        try {
            httpPost(url, null, true);
        } catch (Exception e) {
            throw new FitbitAPIException("Error adding alarm: " + e, e);
        }
}
}
