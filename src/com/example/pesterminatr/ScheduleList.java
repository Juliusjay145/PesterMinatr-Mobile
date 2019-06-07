package com.example.pesterminatr;

public class ScheduleList {
	
	String id,date,time;

	public ScheduleList(String id, String date, String time) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
