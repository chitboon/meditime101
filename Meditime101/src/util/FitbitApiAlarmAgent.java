package util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fitbit.api.APIUtil;
import com.fitbit.api.FitbitAPIException;
import com.fitbit.api.client.FitbitApiClientAgent;
import com.fitbit.api.client.FitbitApiCredentialsCache;
import com.fitbit.api.client.LocalUserDetail;
import com.fitbit.api.client.http.PostParameter;
import com.fitbit.api.client.http.Response;
import com.fitbit.api.common.model.activities.ActivityLog;
import com.fitbit.api.common.model.body.FatLog;
import com.fitbit.api.common.model.devices.Scale;
import com.fitbit.api.common.model.foods.Food;
import com.fitbit.api.common.model.foods.FoodFormType;
import com.fitbit.api.common.model.foods.FoodLog;
import com.fitbit.api.common.model.foods.NutritionalValuesEntry;
import com.fitbit.api.common.service.FitbitApiService;
import com.fitbit.api.model.APICollectionType;
import com.fitbit.api.model.APIFormat;
import com.fitbit.api.model.ApiCollectionProperty;
import com.fitbit.api.model.FitbitUser;

import controller.Alarm;

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

//	public String getAlarms(LocalUserDetail localUser, String deviceId)
//			throws FitbitAPIException {
//		setAccessToken(localUser);
//		// Example: GET /1/user/-/devices/tracker/456665/alarms.json
//		String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(), "/user/-/devices/tracker/" + deviceId + "/alarms", APIFormat.JSON);
//		Response response = httpGet(url, true);
//		throwExceptionIfError(response);
//		try {
//			
//			return response.asJSONObject().toString();
//			
//		} catch (JSONException e) {
//			throw new FitbitAPIException(
//					"Error parsing json response to list of Scale : ", e);
//		}
//	}
    public Response getCollectionResponseForAlarm(LocalUserDetail localUser, String deviceId) throws FitbitAPIException {
        setAccessToken(localUser);
        // Example: GET /1/user/-/devices/tracker/456665/alarms.json
        String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(), "/user/-/devices/tracker/" + deviceId + "/alarms", APIFormat.JSON);
        Response res = httpGet(url, true);
        throwExceptionIfError(res);
        return res;
    }
	
    public List<Alarm> getAlarms(LocalUserDetail localUser,String deviceId) throws FitbitAPIException {
        // Example: GET /1/user/-/devices/tracker/456665/alarms.json
    	Response res = getCollectionResponseForAlarm(localUser, deviceId);
        return Alarm.constructAlarmListFromArrayResponse(res);
    }
	
	public void addAlarms(LocalUserDetail localUser, String deviceId,
			String time, boolean enabled, boolean recurring, String[] weekDays)
			throws FitbitAPIException {
	}

	public String addAlarms(LocalUserDetail localUser, String deviceId, HttpServletRequest request, String alarm)
			throws FitbitAPIException {
		setAccessToken(localUser);
		String alarmStr = "";
		// Example: POST /1/user/-/devices/tracker/55777/alarms.json
		String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(), "/user/-/devices/tracker/" + deviceId + "/alarms",APIFormat.JSON);
		
		
		List<PostParameter> params = new ArrayList<PostParameter>();	
		
		params.add(new PostParameter("time",alarm));
		params.add(new PostParameter("enabled", "true"));
		params.add(new PostParameter("recurring", "true"));
		params.add(new PostParameter("weekDays", "MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY"));

		Response res;
		try {
			res = httpPost(url,
					params.toArray(new PostParameter[params.size()]), true);
		} catch (Exception e) {
			throw new FitbitAPIException("Error creating activity: " + e, e);
		}

		if (res.getStatusCode() != HttpServletResponse.SC_CREATED) {
			throw new FitbitAPIException("Error creating activity: " + res.getStatusCode());
		}
		return res.asString();
	}
	
    public void deleteAlarm(LocalUserDetail localUser, String deviceId, String alarmId) throws FitbitAPIException {
        setAccessToken(localUser);
        // Example: DELETE /1/user/-/devices/tracker/55777/alarms/123.json
        String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(),  "/user/-/devices/tracker/" + deviceId + "/alarms/" + alarmId, APIFormat.JSON);
        try {
            httpDelete(url, true);
        } catch (Exception e) {
            throw new FitbitAPIException("Error deleting Alarm Id:  " + e, e);
        }
    }

}