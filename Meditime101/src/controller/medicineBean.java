package controller;

import java.util.ArrayList;
import java.util.List;

public class medicineBean {
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	private String name;
	private int tablet;
	private String date;
	private String duration;
	private String endDate;
	
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTablet() {
		return tablet;
	}
	public void setTablet(int tablet) {
		this.tablet = tablet;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
