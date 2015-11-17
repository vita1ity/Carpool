package org.crama.carpool.model;

public class Vehicle {
	
	private int vehicleID;
	private String model;
	private String coutry;
	private int year;
	
	public Vehicle(int vehicleID, String model, String coutry, int year) {
		super();
		this.vehicleID = vehicleID;
		this.model = model;
		this.coutry = coutry;
		this.year = year;
	}

	public Vehicle(String model, String coutry, int year) {
		super();
		this.model = model;
		this.coutry = coutry;
		this.year = year;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCoutry() {
		return coutry;
	}

	public void setCoutry(String coutry) {
		this.coutry = coutry;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
