package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.fitbit.api.model.APIFormat;

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

	public String getAlarms(LocalUserDetail localUser, String deviceId)
			throws FitbitAPIException {
		setAccessToken(localUser);
		// Example: GET /1/user/-/devices/tracker/456665/alarms.json
		String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(),
				"/user/-/devices/tracker/" + deviceId + "/alarms",
				APIFormat.JSON);
		Response response = httpGet(url, true);
		throwExceptionIfError(response);
		try {

			return response.asJSONObject().toString();

		} catch (JSONException e) {
			throw new FitbitAPIException(
					"Error parsing json response to list of Scale : ", e);
		}
	}

	// public Alarm addAlarms(LocalUserDetail localUser ,String deviceId, String
	// time, boolean enabled, String recurring, String[] weekDays) throws
	// FitbitAPIException{
	// setAccessToken(localUser);
	// //POST /1/user/-/devices/tracker/55777/alarms.json
	// String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(),
	// "/user/-/devices/tracker/" + deviceId, APIFormat.JSON);
	// try {
	// httpPost(url, null, true);
	// } catch (Exception e) {
	// throw new FitbitAPIException("Error adding alarm: " + e, e);
	// }
	// }
	// setAccessToken(localUser);
	// String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(),
	// "/user/-/devices/tracker/" + deviceId, APIFormat.JSON);
	//
	// try {
	// Response res = httpPost(url, null, true);
	// return new Alarm(res.asJSONObject().getJSONObject("alarm"));
	// }
	// catch (FitbitAPIException e) {
	// throw new FitbitAPIException("Error logging alarm: " + e, e);
	// }
	// catch (JSONException e) {
	// throw new FitbitAPIException("Error logging alarm: " + e, e);
	// }
	//
	// }

	// public Alarm logAlarm(LocalUserDetail localUser, String alarmId, boolean
	// deleted,boolean enabled, String label, boolean recurring, String
	// snoozeCount,String snoozeLength, boolean syncedToDevice,String time,
	// String vibe, String [] weekDays) throws FitbitAPIException {
	// public Alarm logAlarm(LocalUserDetail localUser, String time, boolean
	// enabled, boolean recurring) throws FitbitAPIException {
	// List<PostParameter> params = new ArrayList<PostParameter>(5);
	// params.add(new PostParameter("time", time));
	// params.add(new PostParameter("enabled", String.valueOf(enabled)));
	// params.add(new PostParameter("recurring", String.valueOf(recurring)));
	//
	//
	//
	// return logAlarm(localUser, params);
	// }
	//
	//
	// private Alarm logAlarm(LocalUserDetail localUser, List<PostParameter>
	// params) throws FitbitAPIException {
	// setAccessToken(localUser);
	// // Example: POST /1/user/-/food/log.json
	// String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(),
	// "/user/-/foods/log", APIFormat.JSON);
	//
	// Response res;
	// try {
	// res = httpPost(url, params.toArray(new PostParameter[params.size()]),
	// true);
	// } catch (Exception e) {
	// throw new FitbitAPIException("Error creating alarm log entry: " + e, e);
	// }
	// if (res.getStatusCode() != HttpServletResponse.SC_CREATED) {
	// throw new FitbitAPIException("Error creating activity: " +
	// res.getStatusCode());
	// }
	// try {
	// return new Alarm(res.asJSONObject().getJSONObject("alarm"));
	// } catch (JSONException e) {
	// throw new FitbitAPIException("Error creating alarm log entry: " + e, e);
	// }
	// }
	/*
	 * dummy method
	 */
	public void addAlarms(LocalUserDetail localUser, String deviceId,
			String time, boolean enabled, boolean recurring, String[] weekDays)
			throws FitbitAPIException {

	}

	/*
	 * demo method to show how to add Alarm
	 */
	public String addAlarms(LocalUserDetail localUser, String deviceId)
			throws FitbitAPIException {
		setAccessToken(localUser);
		String alarmStr = "";
		// Example: POST /1/user/-/devices/tracker/55777/alarms.json
		String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(),
				"/user/-/devices/tracker/" + deviceId + "/alarms",APIFormat.JSON);
		List<PostParameter> params = new ArrayList<PostParameter>();
		params.add(new PostParameter("time","10:00+11:00"));
		params.add(new PostParameter("enabled", "true"));
		params.add(new PostParameter("recurring", "true"));
		params.add(new PostParameter("weekDays", "MONDAY,SUNDAY"));

		Response res;
		try {
			res = httpPost(url,
					params.toArray(new PostParameter[params.size()]), true);
		} catch (Exception e) {
			throw new FitbitAPIException("Error creating activity: " + e, e);
		}

		if (res.getStatusCode() != HttpServletResponse.SC_CREATED) {
			throw new FitbitAPIException("Error creating activity: "
					+ res.getStatusCode());
		}
		return res.asString();
	}

	// public Alarm addAlarms(LocalUserDetail localUser ,String deviceId, String
	// time, boolean enabled, boolean recurring, String[] weekDays) throws
	// FitbitAPIException {
	// setAccessToken(localUser);
	// List<PostParameter> params = new ArrayList<PostParameter>();
	// params.add(new PostParameter("time", time));
	// params.add(new PostParameter("enabled", String.valueOf(enabled)));
	// params.add(new PostParameter("recurring", String.valueOf(recurring)));
	// params.add(new PostParameter("weekDays", String.valueOf(weekDays)));
	//
	// // Example: POST /1/user/-/devices/tracker/55777/alarms.json
	// String url = APIUtil.contextualizeUrl(getApiBaseUrl(), getApiVersion(),
	// "/user/-/devices/tracker/" + deviceId, APIFormat.JSON);
	//
	// Response response = httpPost(url, params.toArray(new
	// PostParameter[params.size()]), true);
	//
	// try {
	// return new Alarm(response.asJSONObject().getJSONObject("alarm"));
	// } catch (JSONException e) {
	// throw new FitbitAPIException("Error parsing json response to alarm : ",
	// e);
	// }
	// }

}