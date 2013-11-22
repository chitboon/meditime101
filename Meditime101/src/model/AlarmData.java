package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class AlarmData {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;		
	 String alarmId;
	 boolean deleted;
	 boolean enabled;
	 String label;
	 boolean recurring;
	 int snoozeCount;
	 int snoozeLength;
		boolean syncedToDevice;
		 String time;
		 String vibe;
		 String[] weekDays;
	 
	 public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isRecurring() {
		return recurring;
	}
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}
	public int getSnoozeCount() {
		return snoozeCount;
	}
	public void setSnoozeCount(int snoozeCount) {
		this.snoozeCount = snoozeCount;
	}
	public int getSnoozeLength() {
		return snoozeLength;
	}
	public void setSnoozeLength(int snoozeLength) {
		this.snoozeLength = snoozeLength;
	}
	public boolean isSyncedToDevice() {
		return syncedToDevice;
	}
	public void setSyncedToDevice(boolean syncedToDevice) {
		this.syncedToDevice = syncedToDevice;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVibe() {
		return vibe;
	}
	public void setVibe(String vibe) {
		this.vibe = vibe;
	}
	public String[] getWeekDays() {
		return weekDays;
	}
	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}

}
