package com.example.userportal.domain;

public class User {
	
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String esId;
	
	
	
	
	public String getEsId() {
		return esId;
	}
	public void setEsId(String esId) {
		this.esId = esId;
	}
	public User() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", userName=" + userName
				+ ", esId=" + esId + "]";
	}

	
	

	
	

}
