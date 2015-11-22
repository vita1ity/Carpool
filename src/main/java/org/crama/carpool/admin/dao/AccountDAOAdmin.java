package org.crama.carpool.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.crama.carpool.dao.AccountDAO;
import org.crama.carpool.dao.ConnectionManager;
import org.crama.carpool.model.Account;
import org.crama.carpool.model.Address;
import org.crama.carpool.model.Vehicle;

public class AccountDAOAdmin {
	private static AccountDAOAdmin instance = null;
	private static Connection connection = null;
	private AccountDAO accountDAO = null;
	private AccountDAOAdmin() {
		//connection = ConnectionManager.getMySqlConnection();
		accountDAO = AccountDAO.getInstance();
	}
	public static synchronized AccountDAOAdmin getInstance() {
		if (instance == null) {
			instance = new AccountDAOAdmin();			
		}
		return instance;
	}
	public List<Account> getUnverifiedAccounts() {
		List<Account> unverifiedUsers = new ArrayList<Account>();
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM user WHERE IS_APPROVED = ? AND ROLE = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setBoolean(1, false);
			stmt.setString(2, "user");
			
			ResultSet rs = stmt.executeQuery();
			
			int userIdRS = 0;
			String usernameRS = null;
			String passwordRS = null;
			String emailRS = null;
			String fullNameRS = null;
			String phoneRS = null;
			String roleRS = null;
			String imageUrl = null;
			
			boolean isApproved = false;
			Integer addressId = null;
			Integer vehicleId = null;
			
			while (rs.next()) {
				
				userIdRS = Integer.parseInt(rs.getString("USER_ID"));
				usernameRS = rs.getString("USERNAME");
				passwordRS = rs.getString("PASSWORD");
				emailRS = rs.getString("EMAIL");
				fullNameRS = rs.getString("FULL_NAME");
				phoneRS = rs.getString("PHONE");
				roleRS = rs.getString("ROLE");
				isApproved = rs.getBoolean("IS_APPROVED");
				imageUrl = rs.getString("IMAGE_URL");
				
				addressId = rs.getInt("ADDRESS_ID");
				vehicleId = rs.getInt("VEHICLE_ID");
				
				if (usernameRS != null) {
					Account resultUser = new Account(userIdRS, usernameRS, fullNameRS, emailRS, passwordRS, phoneRS, roleRS, imageUrl);
					resultUser.setIsApproved(isApproved);
					
					if (addressId != null) {
						Address address = accountDAO.getAddress(addressId);
						resultUser.setAddress(address);
					}
					if (vehicleId != null) {
						Vehicle vehicle = accountDAO.getVehicle(vehicleId);
						resultUser.setVehicle(vehicle);
					}
					
					unverifiedUsers.add(resultUser);
				}
				
			}
			return unverifiedUsers;
			
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
	public List<Vehicle> getUnverifiedVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM vehicle WHERE IS_APPROVED = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setBoolean(1, false);
			
			ResultSet rs = stmt.executeQuery();
			int vehicleId = 0;
			String model = null;
			String country = null;
			Integer year = null;
			
			boolean isApproved = false;
			
			while (rs.next()) {
				
				vehicleId = rs.getInt("VEHICLE_ID");
				model = rs.getString("MODEL");
				country = rs.getString("COUNTRY");
				year = rs.getInt("YEAR");
				isApproved = rs.getBoolean("IS_APPROVED");
			
				if (model != null) {
					Vehicle vehicle = new Vehicle(vehicleId, model, country, year);
					vehicle.setIsApproved(isApproved);
					vehicles.add(vehicle);
				}
			}
			return vehicles;
			
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
	public void updateVehicle(Vehicle vehicle) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "UPDATE vehicle SET IS_APPROVED = ? "
				+ "WHERE VEHICLE_ID = ?";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setBoolean(1, true);
			stmt.setInt(2, vehicle.getVehicleId());
			
			stmt.executeUpdate();
			System.out.println("Vehicle updated in the database");
			
			
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
