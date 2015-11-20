package org.crama.carpool.service;

import java.util.List;

import org.crama.carpool.dao.RouteDAO;
import org.crama.carpool.model.Passenger;
import org.crama.carpool.model.Route;

public class RouteService {
	private RouteDAO routeDAO;
	private static RouteService instance = null;
	private RouteService() {
		
		routeDAO = RouteDAO.getInstance();
	}
	public static synchronized RouteService getInstance() {
		if (instance == null) {
			instance = new RouteService();			
		}
		return instance;
	}
	public void addRoute(Route route) {
		//TODO
		//send admin route added notification
		routeDAO.saveRoute(route);
		
	}
	public List<Route> getUserRoutes(int userId) {
		
		return routeDAO.getUserRoutes(userId);
	}
	public List<Route> getJoinedRoutes(int userId) {
		
		return routeDAO.getJoinedRoutes(userId);
	}
	public List<Route> getApprovedRoutes(int userId) {
		
		return routeDAO.getApprovedRoutes(userId);
	}
	public void addPassenger(Passenger pass) {
		//TODO send add passenger notification for admin. after
		//admin added pick up time send to carpool owner for approval
		//only then passenger is approved
		routeDAO.savePassenger(pass);
		
		
	}
	public void removePassenger(int userId, int routeId) {
		routeDAO.removePassenger(userId, routeId);
		
	}
	public void removeRoute(int routeId) {
		routeDAO.removeRoute(routeId);
		
	}
	
}
