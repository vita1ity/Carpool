package org.crama.carpool.model;

import java.time.LocalTime;
import java.util.List;

public class Route {
	
	private int routeId;
	private String name;
	private String sourceAddress;
	private String destinationAddress;
	private LocalTime startTime;
	private LocalTime endTime;
	private String comment;
	private int numOfPassengers;
	private int freePlaces;
	private List<Passenger> passengers;
	private Account owner;
	
	private String mapUrl;
	
	private boolean isApproved;
	
	
	public Route(String name, String sourceAddress, String destinationAddress, LocalTime startTime, LocalTime endTime, String comment,
			int numOfPassengers, int freePlaces, String mapUrl, List<Passenger> passengers, Account owner, boolean isApproved) {
		super();
		this.name = name;
		this.sourceAddress = sourceAddress;
		this.destinationAddress = destinationAddress;
		this.startTime = startTime;
		this.endTime = endTime;
		this.comment = comment;
		this.numOfPassengers = numOfPassengers;
		this.freePlaces = freePlaces;
		this.mapUrl = mapUrl;
		this.passengers = passengers;
		this.owner = owner;
		this.isApproved = isApproved;
	}
	
	
	public Route(int routeId, String name, String sourceAddress, String destinationAddress, LocalTime startTime,
			LocalTime endTime, String comment, int numOfPassengers, int freePlaces, String mapUrl, List<Passenger> passengers,
			Account owner, boolean isApproved) {
		super();
		this.routeId = routeId;
		this.name = name;
		this.sourceAddress = sourceAddress;
		this.destinationAddress = destinationAddress;
		this.startTime = startTime;
		this.endTime = endTime;
		this.comment = comment;
		this.numOfPassengers = numOfPassengers;
		this.freePlaces = freePlaces;
		this.mapUrl = mapUrl;
		this.passengers = passengers;
		this.owner = owner;
		this.isApproved = isApproved;
	}




	public String getMapUrl() {
		return mapUrl;
	}


	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}


	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}
	
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public Account getOwner() {
		return owner;
	}
	public void setOwner(Account owner) {
		this.owner = owner;
	}
	public boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public int getFreePlaces() {
		return freePlaces;
	}
	public void setFreePlaces(int freePlaces) {
		this.freePlaces = freePlaces;
	}
	
	
}
