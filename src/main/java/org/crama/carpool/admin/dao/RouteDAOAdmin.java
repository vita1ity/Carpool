package org.crama.carpool.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.crama.carpool.dao.AccountDAO;
import org.crama.carpool.dao.ConnectionManager;
import org.crama.carpool.dao.RouteDAO;
import org.crama.carpool.model.Account;
import org.crama.carpool.model.Passenger;
import org.crama.carpool.model.Route;
import org.crama.carpool.util.Util;

public class RouteDAOAdmin {
	private static RouteDAOAdmin instance = null;
	private static Connection connection = null;
	private RouteDAO routeDAO = null;
	private RouteDAOAdmin() {
		routeDAO = RouteDAO.getInstance();
	}
	public static synchronized RouteDAOAdmin getInstance() {
		if (instance == null) {
			instance = new RouteDAOAdmin();			
		}
		return instance;
	}
	public List<Route> getUnverifiedRoutes() {
		List<Route> unverifiedRoutes = new ArrayList<Route>();
		AccountDAO accountDAO = AccountDAO.getInstance();
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM route WHERE IS_APPROVED = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setBoolean(1, false);
			
			ResultSet rs = stmt.executeQuery();
			
			int routeId = 0;
			int ownerId = 0;
			String name = null;
			String sourceAddress = null;
			String destAddress = null;
			String startTime = null;
			String endTime = null;
			String comment = null;
			int numOfPassengers = 0;
			String mapUrl = null;
			boolean isApproved = false;
			
			while (rs.next()) {
				
				routeId = rs.getInt("ROUTE_ID");
				ownerId = rs.getInt("OWNER_ID");
				name = rs.getString("NAME");
				sourceAddress = rs.getString("SOURCE_ADDRESS");
				destAddress = rs.getString("DEST_ADDRESS");
				startTime = rs.getString("START_TIME");
				endTime = rs.getString("END_TIME");
				comment = rs.getString("COMMENT");
				numOfPassengers = rs.getInt("NUM_OF_PASS");
				mapUrl = rs.getString("MAP_URL");
				isApproved = rs.getBoolean("IS_APPROVED");
				
				if (routeId != 0) {
					
					//get all passengers in the route
					List<Passenger> passengerList = routeDAO.getApprovedPassengers(routeId, 0);
					Account owner = accountDAO.getAccountById(ownerId);
					LocalTime start = Util.createTime(startTime);
					LocalTime end = Util.createTime(endTime);
					int freePlaces = numOfPassengers - passengerList.size();
					Route route = new Route(routeId, name, sourceAddress, destAddress, start, end, comment, numOfPassengers,
							freePlaces, mapUrl, passengerList, owner, isApproved);
					
					unverifiedRoutes.add(route);
				}
			}
			return unverifiedRoutes;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					stmt = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					connection = null;
				}
			}
		}
		return null;
	}
	public Route getRoute(int routeId) {
		
		AccountDAO accountDAO = AccountDAO.getInstance();
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM route WHERE ROUTE_ID = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, routeId);
			
			ResultSet rs = stmt.executeQuery();
			
			
			int ownerId = 0;
			String name = null;
			String sourceAddress = null;
			String destAddress = null;
			String startTime = null;
			String endTime = null;
			String comment = null;
			int numOfPassengers = 0;
			String mapUrl = null;
			boolean isApproved = false;
			
			while (rs.next()) {
				
				routeId = rs.getInt("ROUTE_ID");
				ownerId = rs.getInt("OWNER_ID");
				name = rs.getString("NAME");
				sourceAddress = rs.getString("SOURCE_ADDRESS");
				destAddress = rs.getString("DEST_ADDRESS");
				startTime = rs.getString("START_TIME");
				endTime = rs.getString("END_TIME");
				comment = rs.getString("COMMENT");
				numOfPassengers = rs.getInt("NUM_OF_PASS");
				mapUrl = rs.getString("MAP_URL");
				isApproved = rs.getBoolean("IS_APPROVED");
				
				if (routeId != 0) {
					
					//get all passengers in the route
					List<Passenger> passengerList = routeDAO.getApprovedPassengers(routeId, 0);
					Account owner = accountDAO.getAccountById(ownerId);
					LocalTime start = Util.createTime(startTime);
					LocalTime end = Util.createTime(endTime);
					int freePlaces = numOfPassengers - passengerList.size();
					Route route = new Route(routeId, name, sourceAddress, destAddress, start, end, comment, numOfPassengers,
							freePlaces, mapUrl, passengerList, owner, isApproved);
					return route;
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					stmt = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					connection = null;
				}
			}
		}
		return null;
	}
	public void updateRoute(Route route) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "UPDATE route SET IS_APPROVED = ?, MAP_URL = ? "
				+ "WHERE ROUTE_ID = ?";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setBoolean(1, route.getIsApproved());
			stmt.setString(2, route.getMapUrl());
			stmt.setInt(3, route.getRouteId());
			
			
			stmt.executeUpdate();
			System.out.println("Route updated in the database");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					stmt = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					connection = null;
				}
			}
		}
		
	}
	public List<Route> getUnverifiedPassengers() {
		List<Route> routePassengers = new ArrayList<Route>();
		AccountDAO accountDAO = AccountDAO.getInstance();
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM passenger WHERE IS_APPROVED = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setBoolean(1, false);
			
			ResultSet rs = stmt.executeQuery();
			
			int id = 0;
			int userIdRS = 0;
			int routeIdRS = 0;
			String pickTime = null;
			boolean isApproved = false;
			
			while (rs.next()) {
				
				id = rs.getInt("ID");
				userIdRS = rs.getInt("USER_ID");
				routeIdRS = rs.getInt("ROUTE_ID");
				pickTime = rs.getString("PICK_TIME");
				isApproved = rs.getBoolean("IS_APPROVED");
				
				System.out.println("getUnapprovedPassengers");
				if (id != 0 ) {
					Account user = accountDAO.getFullAccount(userIdRS);
					
					LocalTime time = Util.createTime(pickTime);
					
					Route route = getRoute(routeIdRS);
					
					Passenger passenger = new Passenger(id, user, routeIdRS, time, isApproved);
					
					List<Passenger> pass = new ArrayList<Passenger>();pass.add(passenger);
					route.setPassengers(pass);
					System.out.println("Passenger added: " + passenger);
					routePassengers.add(route);
				
				}
			}
			return routePassengers;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					stmt = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					connection = null;
				}
			}
		}
		return null;
	}
	public Passenger getPassenger(int passId) {
		
		AccountDAO accountDAO = AccountDAO.getInstance();
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM passenger WHERE ID = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, passId);
			
			ResultSet rs = stmt.executeQuery();
			
			int id = 0;
			int userIdRS = 0;
			int routeIdRS = 0;
			String pickTime = null;
			boolean isApproved = false;
			
			while (rs.next()) {
				
				id = rs.getInt("ID");
				userIdRS = rs.getInt("USER_ID");
				routeIdRS = rs.getInt("ROUTE_ID");
				pickTime = rs.getString("PICK_TIME");
				isApproved = rs.getBoolean("IS_APPROVED");
				
				System.out.println("getUnapprovedPassengers");
				if (id != 0 ) {
					Account user = accountDAO.getAccountById(userIdRS);
					
					LocalTime time = Util.createTime(pickTime);
					
					Passenger passenger = new Passenger(id, user, routeIdRS, time, isApproved);
					
					return passenger;
				
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					stmt = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					connection = null;
				}
			}
		}
		return null;
	}
	public void updatePassenger(Passenger pass) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "UPDATE passenger SET PICK_TIME = ?, IS_APPROVED = ? "
				+ "WHERE ID = ?";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			
			stmt.setString(1, pass.getPickTime().format(formatter));
			stmt.setBoolean(2, pass.getIsApproved());
			stmt.setInt(3, pass.getPassengerId());
			
			stmt.executeUpdate();
			System.out.println("Passenger updated in the database");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					stmt = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					connection = null;
				}
			}
		}
	}
}
