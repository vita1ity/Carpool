package org.crama.carpool.model;

public class Account {
	
	private int userId;
	private String username;
	private String fullName;
	private String email;
	private String password;
	private String phone;
	private String role;
	
	private String imageUrl;
	
	private boolean isApproved;
	
	private Address address;
	private Vehicle vehicle;
	
	public Account(int userId, String username, String fullName, String email, String password, String phone, String role, String imageUrl) {
		super();
		this.userId = userId;
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.imageUrl = imageUrl;
	}
	public Account(String username, String email, String password, String phone, String role, String imageUrl) {
		super();
		
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.imageUrl = imageUrl;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
}
