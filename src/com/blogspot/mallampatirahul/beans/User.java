package com.blogspot.mallampatirahul.beans;

public class User {
	private String userId;
	private String password;
	private String userName;
	private String userEmail;
	private boolean isAdmin;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String toString() {
		StringBuilder s= new StringBuilder("");
		s.append("User Name:"+this.getUserName());
		s.append(" User ID:"+this.getUserId());
		s.append(" Password:"+this.getPassword());
		s.append(" User Email:"+this.getUserEmail());
		System.out.println(s.toString());
		return s.toString();
	}
}
