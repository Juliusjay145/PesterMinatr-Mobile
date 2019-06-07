package com.example.pesterminatr;

public class CommentList {

	String id,comment,user,rating;

	public CommentList(String id, String comment, String user, String rating) {
		super();
		this.id = id;
		this.comment = comment;
		this.user = user;
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	
	
	
	
}
