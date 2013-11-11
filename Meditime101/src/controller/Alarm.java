package controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fitbit.api.common.model.user.UserInfo;

public class Alarm {

	private final String alarmId;
	private final boolean deleted;
	private final boolean enabled;
	private final String label;
	private final boolean recurring;
	private final String snoozeCount;
	private final String snoozeLength;
	private final boolean syncedToDevice;
	private final String time;
	private final String vibe;
	private final String weekDays;
	
    public Alarm(JSONObject json) throws JSONException {
        this(json, true);
    }

	public Alarm(JSONObject jsonObject, boolean wrapped) throws JSONException {
    	JSONObject alarmJson = wrapped ? jsonObject.getJSONObject("alarm") : jsonObject;
        alarmId = alarmJson.optString("alarmId");
        deleted = alarmJson.optBoolean("deleted");
        enabled = alarmJson.optBoolean("enabled");
        label = alarmJson.optString("label");
        recurring = alarmJson.optBoolean("recurring");
        snoozeCount = alarmJson.optString("snoozeCount");
        snoozeLength = alarmJson.optString("snoozeLength");
        syncedToDevice = alarmJson.optBoolean("syncedToDevice");
        time = alarmJson.optString("time");
        vibe = alarmJson.optString("vibe");
        weekDays = alarmJson.optString("weekDays");
    }

    public static List<Alarm> alarmArray(JSONArray array) throws JSONException {
        List<Alarm> AlarmList = new ArrayList<Alarm>(array.length());
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonAlarm = array.getJSONObject(i);
            AlarmList.add(new Alarm(jsonAlarm));
        }
        return AlarmList;
    }
	
	public String getAlarmId() {
		return alarmId;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public String getLabel() {
		return label;
	}
	public boolean isRecurring() {
		return recurring;
	}
	public String getSnoozeCount() {
		return snoozeCount;
	}
	public String getSnoozeLength() {
		return snoozeLength;
	}
	public boolean isSyncedToDevice() {
		return syncedToDevice;
	}
	public String getTime() {
		return time;
	}
	public String getVibe() {
		return vibe;
	}
	public String getWeekDays() {
		return weekDays;
	}
	
	
}
