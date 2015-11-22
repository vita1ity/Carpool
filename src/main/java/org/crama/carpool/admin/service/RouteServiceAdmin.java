package org.crama.carpool.admin.service;

import java.util.List;

import org.crama.carpool.admin.dao.RouteDAOAdmin;
import org.crama.carpool.model.Passenger;
import org.crama.carpool.model.Route;

public class RouteServiceAdmin {
	private RouteDAOAdmin routeDAO;
	private static RouteServiceAdmin instance = null;
	private RouteServiceAdmin() {
		
		routeDAO = RouteDAOAdmin.getInstance();
	}
	public static synchronized RouteServiceAdmin getInstance() {
		if (instance == null) {
			instance = new RouteServiceAdmin();			
		}
		return instance;
	}
	public List<Route> getUnverifiedRoutes() {
		
		return routeDAO.getUnverifiedRoutes();
	}
	public Route getRoute(int routeId) {
		
		return routeDAO.getRoute(routeId);
	}
	public void editRoute(Route route) {
		routeDAO.updateRoute(route);
		
	}
	public List<Route> getUnverifiedPassengers() {
		
		return routeDAO.getUnverifiedPassengers();
	}
	public Passenger getPassenger(int passId) {
		
		return routeDAO.getPassenger(passId);
	}
	public void editPassenger(Passenger pass) {
		routeDAO.updatePassenger(pass);
		
	}
}
