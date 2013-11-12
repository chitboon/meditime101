package controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fitbit.api.common.model.foods.FoodUnit;
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
	private final String [] weekDays;
	
//    public Alarm(JSONObject json) throws JSONException {
//        this(json, true);
//    }
    
    public Alarm(String alarmId, boolean deleted, boolean enabled, String label, boolean recurring, String snoozeCount, String snoozeLength, boolean syncedToDevice, String time, String vibe, String [] weekDays) {
       this.alarmId = alarmId;
       this.deleted = deleted;
       this.enabled = enabled;
       this.label = label; 
       this.recurring = recurring;
       this.snoozeCount = snoozeCount;
       this.snoozeLength = snoozeLength;
       this.syncedToDevice = syncedToDevice;
       this.time = time;
       this.vibe = vibe;
       this.weekDays = weekDays;
    }
    
    public Alarm(JSONObject json) throws JSONException {
    	alarmId = json.getString("alarmId");
    	deleted = json.getBoolean("deleted");
    	enabled = json.getBoolean("enabled");
        label = json.getString("label");
        recurring = json.getBoolean("recurring");
        snoozeCount = json.getString("snoozeCount");
        snoozeLength = json.getString("snoozeLength");
        syncedToDevice = json.getBoolean("syncedToDevice");
        time = json.getString("time");
        vibe = json.getString("vibe");
        weekDays = jsonArrayToAlarmArray(json.getJSONArray("weekDays"));
    }

//	public Alarm(JSONObject jsonObject, boolean wrapped) throws JSONException {
//    	JSONObject alarmJson = wrapped ? jsonObject.getJSONObject("alarm") : jsonObject;
//        alarmId = alarmJson.optString("alarmId");
//        deleted = alarmJson.optBoolean("deleted");
//        enabled = alarmJson.optBoolean("enabled");
//        label = alarmJson.optString("label");
//        recurring = alarmJson.optBoolean("recurring");
//        snoozeCount = alarmJson.optString("snoozeCount");
//        snoozeLength = alarmJson.optString("snoozeLength");
//        syncedToDevice = alarmJson.optBoolean("syncedToDevice");
//        time = alarmJson.optString("time");
//        vibe = alarmJson.optString("vibe");
//        weekDays = jsonArrayToAlarmArray(jsonObject.getJSONArray("weekDays"));
//    }

    public static List<Alarm> alarmArray(JSONArray array) throws JSONException {
        List<Alarm> AlarmList = new ArrayList<Alarm>(array.length());
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonAlarm = array.getJSONObject(i);
            AlarmList.add(new Alarm(jsonAlarm));
        }
        return AlarmList;
    }

    static String[] jsonArrayToAlarmArray(JSONArray array) throws JSONException {
    	String[] weekDays = new String[array.length()];
        for (int i = 0; i < array.length(); i++) {
            weekDays[i] = array.getString(i);
        }
        return weekDays;
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
	public String[] getWeekDays() {
		return weekDays;
	}
	
	
}
