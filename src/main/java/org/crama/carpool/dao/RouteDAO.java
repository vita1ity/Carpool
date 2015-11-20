package org.crama.carpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.crama.carpool.model.Account;
import org.crama.carpool.model.Passenger;
import org.crama.carpool.model.Route;
import org.crama.carpool.util.Util;

public class RouteDAO {
	private static RouteDAO instance = null;
	private static Connection connection = null;
	private RouteDAO() {
		//connection = ConnectionManager.getMySqlConnection();
	}
	public static synchronized RouteDAO getInstance() {
		if (instance == null) {
			instance = new RouteDAO();			
		}
		return instance;
	}
	public void saveRoute(Route route) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "INSERT INTO route (OWNER_ID, SOURCE_ADDRESS, DEST_ADDRESS, START_TIME, END_TIME, COMMENT, NUM_OF_PASS, IS_APPROVED, NAME) "
						+ "VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			
			stmt.setInt(1, route.getOwner().getUserId());
			stmt.setString(2, route.getSourceAddress());
			stmt.setString(3, route.getDestinationAddress());
			stmt.setString(4, route.getStartTime().format(formatter));
			stmt.setString(5, route.getEndTime().format(formatter));
			stmt.setString(6, route.getComment());
			stmt.setInt(7, route.getNumOfPassengers());
			stmt.setBoolean(8, route.getIsApproved());
			stmt.setString(9, route.getName());
		
			int row = stmt.executeUpdate();
			System.out.println("Route saved in the database");
			
			
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
	public List<Route> getUserRoutes(int userId) {
		List<Route> userRouteList = new ArrayList<Route>();
		AccountDAO accountDAO = AccountDAO.getInstance();
		Account owner = accountDAO.getAccountById(userId);
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM route WHERE OWNER_ID = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, userId);
			
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
				isApproved = rs.getBoolean("IS_APPROVED");
				
				if (routeId != 0) {
					
					//get all passengers in the route
					List<Passenger> passengerList = getApprovedPassengers(routeId, userId);
					
					LocalTime start = Util.createTime(startTime);
					LocalTime end = Util.createTime(endTime);
					int freePlaces = numOfPassengers - passengerList.size();
					Route route = new Route(routeId, name, sourceAddress, destAddress, start, end, comment, numOfPassengers,
							freePlaces, passengerList, owner, isApproved);
					
					userRouteList.add(route);
				}
			}
			return userRouteList;
			
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
	private List<Passenger> getApprovedPassengers(int routeId, int userId) {
		List<Passenger> routePassengers = new ArrayList<Passenger>();
		AccountDAO accountDAO = AccountDAO.getInstance();
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM passenger WHERE ROUTE_ID = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, routeId);
			
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
				
				System.out.println("getApprovedPassengers. userId: " + userId + ", userIdRS: " + userIdRS);
				if (id != 0 ) {
					if (userId == userIdRS || isApproved) {
						Account user = accountDAO.getAccountById(userIdRS);
						
						LocalTime time = Util.createTime(pickTime);
						
						Passenger passenger = new Passenger(id, user, routeIdRS, time, isApproved);
						System.out.println("Passenger added: " + passenger);
						routePassengers.add(passenger);
					}
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
	public List<Route> getJoinedRoutes(int userId) {
		List<Route> userRouteList = new ArrayList<Route>();
		AccountDAO accountDAO = AccountDAO.getInstance();
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM route "
				+ "WHERE ROUTE_ID IN ( "
					+ "SELECT ROUTE_ID from passenger "
					+ "WHERE USER_ID = ?);";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, userId);
			
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
				isApproved = rs.getBoolean("IS_APPROVED");
				System.out.println(routeId + ", " + userId + ", " + ownerId);
				if (routeId != 0 && ownerId != userId) {
		
					//get all passengers in the route
					List<Passenger> passengerList = getApprovedPassengers(routeId, userId);
					Account owner = accountDAO.getAccountById(ownerId);
					LocalTime start = Util.createTime(startTime);
					LocalTime end = Util.createTime(endTime);
					int freePlaces =  numOfPassengers - passengerList.size();
					Route route = new Route(routeId, name, sourceAddress, destAddress, start, end, comment, numOfPassengers,
							freePlaces, passengerList, owner, isApproved);
					
					
					userRouteList.add(route);
				}
			}
			return userRouteList;
			
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
	public List<Route> getApprovedRoutes(int userId) {
		List<Route> userRouteList = new ArrayList<Route>();
		AccountDAO accountDAO = AccountDAO.getInstance();
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM route WHERE IS_APPROVED = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setBoolean(1, true);
			
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
				isApproved = rs.getBoolean("IS_APPROVED");
				
				if (routeId != 0 && ownerId != userId) {
					
					//get all passengers in the route
					List<Passenger> passengerList = getApprovedPassengers(routeId, userId);
					
					boolean isPassenger = false;
					for (Passenger pass: passengerList) {
						if (pass.getUser().getUserId() == userId) {
							isPassenger = true;
							break;
						}
					}
					if (!isPassenger) {
						Account owner = accountDAO.getAccountById(ownerId);
						LocalTime start = Util.createTime(startTime);
						LocalTime end = Util.createTime(endTime);
						int freePlaces = numOfPassengers - passengerList.size();
						Route route = new Route(routeId, name, sourceAddress, destAddress, start, end, comment, numOfPassengers,
								freePlaces, passengerList, owner, isApproved);
						
						userRouteList.add(route);
					}
				}
			}
			return userRouteList;
			
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
	public void savePassenger(Passenger pass) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "INSERT INTO passenger (USER_ID, ROUTE_ID, PICK_TIME, IS_APPROVED) "
						+ "VALUES (?,?,?,?)";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime pickTime = pass.getPickTime();
			String time = null;
			if (pickTime != null) {
				time = pickTime.format(formatter);
			}
			
			stmt.setInt(1, pass.getUser().getUserId());
			stmt.setInt(2, pass.getRouteId());
			stmt.setString(3, time);
			stmt.setBoolean(4, pass.getIsApproved());
		
			int row = stmt.executeUpdate();
			System.out.println("Passenger saved in the database");
			
			
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
	public void removePassenger(int userId, int routeId) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String deleteQuery = " DELETE FROM passenger WHERE USER_ID = ? AND ROUTE_ID = ?";
		try {
			stmt = connection.prepareStatement(deleteQuery);
			stmt.setInt(1, userId);
			stmt.setInt(2, routeId);
			
			boolean res = stmt.execute();
			if (res) {
				System.out.println("Passenger removed");
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
	
	}
	public void removeRoute(int routeId) {
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String deleteQuery = " DELETE FROM route WHERE ROUTE_ID = ?";
		try {
			stmt = connection.prepareStatement(deleteQuery);
			
			stmt.setInt(1, routeId);
			
			boolean res = stmt.execute();
			if (res) {
				System.out.println("Route removed");
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
	}
	
}
