package com.example.pesterminatr;

public class ServiceProfileList {
	
	private String image,id,name,description,price,pestId;

	public ServiceProfileList(String image, String id, String name,
			String description, String price, String pestId) {
		super();
		this.image = image;
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.pestId = pestId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPestId() {
		return pestId;
	}

	public void setPestId(String pestId) {
		this.pestId = pestId;
	}

	

	

	
	
	
	
}
