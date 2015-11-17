package org.crama.carpool.model;

public class Address {
	
	private int addressId;
	private String country;
	private String town;
	private String street;
	private String houseNumber;
	
	public Address(int addressId, String country, String town, String street, String houseNumber) {
		super();
		this.addressId = addressId;
		this.country = country;
		this.town = town;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public Address(String country, String town, String street, String houseNumber) {
		super();
		this.country = country;
		this.town = town;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	
}
