package org.crama.carpool.model;

import java.time.LocalTime;

public class Passenger {
	
	private int passengerId;
	private Account user;
	private int routeId;
	private LocalTime pickTime;
	private boolean isApproved;
	
	public Passenger(Account user, int routeId, LocalTime pickTime, boolean isApproved) {
		super();
		this.user = user;
		this.routeId = routeId;
		this.pickTime = pickTime;
		this.isApproved = isApproved;
	}
	public Passenger(int passengerId, Account user, int routeId, LocalTime pickTime, boolean isApproved) {
		super();
		this.passengerId = passengerId;
		this.user = user;
		this.routeId = routeId;
		this.pickTime = pickTime;
		this.isApproved = isApproved;
	}
	public boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public Account getUser() {
		return user;
	}
	public void setUser(Account user) {
		this.user = user;
	}
	
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public LocalTime getPickTime() {
		return pickTime;
	}
	public void setPickTime(LocalTime pickTime) {
		this.pickTime = pickTime;
	}
	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", user=" + user + ", routeId=" + routeId + ", pickTime="
				+ pickTime + ", isApproved=" + isApproved + "]";
	}
	
	
	
}
