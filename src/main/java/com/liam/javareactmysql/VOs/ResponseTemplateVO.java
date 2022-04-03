package com.liam.javareactmysql.VOs;

import com.liam.javareactmysql.controllers.Photo;
import com.liam.javareactmysql.models.User;

public class ResponseTemplateVO {
	
	private Photo photo;
	
	private User user;

	public ResponseTemplateVO() {

	}

	public ResponseTemplateVO(User user, Photo photo) {

		this.user = user;
		this.photo = photo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	

}
