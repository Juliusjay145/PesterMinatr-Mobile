package com.example.pesterminatr;

public class ServiceList {
	String image,id,name,price,pestid;

	

	public ServiceList(String image, String id, String name, String price,
			String pestid) {
		super();
		this.image = image;
		this.id = id;
		this.name = name;
		this.price = price;
		this.pestid = pestid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPestid() {
		return pestid;
	}

	public void setPestid(String pestid) {
		this.pestid = pestid;
	}
	
	
	
	

}
