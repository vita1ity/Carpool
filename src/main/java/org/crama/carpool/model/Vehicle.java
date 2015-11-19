package org.crama.carpool.model;

public class Vehicle {
	
	private int vehicleId;
	private String model;
	private String country;
	private int year;
	
	private boolean isApproved;
	
	public Vehicle(int vehicleId, String model, String country, int year) {
		super();
		this.vehicleId = vehicleId;
		this.model = model;
		this.country = country;
		this.year = year;
	}

	public Vehicle(String model, String country, int year) {
		super();
		this.vehicleId = -1;
		this.model = model;
		this.country = country;
		this.year = year;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
	
}
