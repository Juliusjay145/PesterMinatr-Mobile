package com.example.pesterminatr;

public class bookresidentialList {
	
	String name,address,date,time,price,cstatus,problem,id,pestId;

	public bookresidentialList(String name, String address, String date,
			String time, String price, String cstatus, String problem,
			String id, String pestId) {
		super();
		this.name = name;
		this.address = address;
		this.date = date;
		this.time = time;
		this.price = price;
		this.cstatus = cstatus;
		this.problem = problem;
		this.id = id;
		this.pestId = pestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPestId() {
		return pestId;
	}

	public void setPestId(String pestId) {
		this.pestId = pestId;
	}

	

	

	
	
	

}
