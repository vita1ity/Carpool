package org.crama.carpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.crama.carpool.model.Account;
import org.crama.carpool.model.Address;
import org.crama.carpool.model.Vehicle;

public class AccountDAO {
	private static AccountDAO instance = null;
	private static Connection connection = null;
	private AccountDAO() {
		//connection = ConnectionManager.getMySqlConnection();
	}
	public static synchronized AccountDAO getInstance() {
		if (instance == null) {
			instance = new AccountDAO();			
		}
		return instance;
	}
	public Account getAccount(String username) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM user WHERE USERNAME = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			int userIdRS = 0;
			String usernameRS = null;
			String passwordRS = null;
			String emailRS = null;
			String fullNameRS = null;
			String phoneRS = null;
			String roleRS = null;
			
			while (rs.next()) {
				
				userIdRS = Integer.parseInt(rs.getString("USER_ID"));
				usernameRS = rs.getString("USERNAME");
				passwordRS = rs.getString("PASSWORD");
				emailRS = rs.getString("EMAIL");
				fullNameRS = rs.getString("FULL_NAME");
				phoneRS = rs.getString("PHONE");
				roleRS = rs.getString("ROLE");
				
			}
			if (usernameRS != null) {
				Account resultUser = new Account(userIdRS, usernameRS, fullNameRS, emailRS, passwordRS, phoneRS, roleRS);
				
				return resultUser;
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
	public void saveAccount(Account newUser) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "INSERT INTO user (USERNAME, FULL_NAME, EMAIL, PASSWORD, PHONE, ROLE) "
						+ "VALUES (?,?,?,?,?,?)";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setString(1, newUser.getUsername());
			stmt.setString(2, newUser.getFullName());
			stmt.setString(3, newUser.getEmail());
			stmt.setString(4, newUser.getPassword());
			stmt.setString(5, newUser.getPhone());
			stmt.setString(6, newUser.getRole());
		
			int row = stmt.executeUpdate();
			System.out.println("User saved in the database");
			
			
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
	public Account getFullAccount(int userId) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM user WHERE USER_ID = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			int userIdRS = 0;
			String usernameRS = null;
			String passwordRS = null;
			String emailRS = null;
			String fullNameRS = null;
			String phoneRS = null;
			String roleRS = null;
			
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
				
				addressId = rs.getInt("ADDRESS_ID");
				vehicleId = rs.getInt("VEHICLE_ID");
				
			}
			if (usernameRS != null) {
				Account resultUser = new Account(userIdRS, usernameRS, fullNameRS, emailRS, passwordRS, phoneRS, roleRS);
				resultUser.setIsApproved(isApproved);
				
				if (addressId != null) {
					Address address = getAddress(addressId);
					resultUser.setAddress(address);
				}
				if (vehicleId != null) {
					Vehicle vehicle = getVehicle(vehicleId);
					resultUser.setVehicle(vehicle);
				}
				
				return resultUser;
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
	private Vehicle getVehicle(Integer vehicleId) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM vehicle WHERE VEHICLE_ID = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, vehicleId);
			
			ResultSet rs = stmt.executeQuery();
			
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
				
			}
			if (model != null) {
				Vehicle vehicle = new Vehicle(vehicleId, model, country, year);
				vehicle.setIsApproved(isApproved);
				return vehicle;
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
	
	public int getMaxAddressId() {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT MAX(ADDRESS_ID) FROM address";
		
		try {
			stmt = connection.prepareStatement(query);
			
			ResultSet rs = stmt.executeQuery();
			
			//System.out.println(rs);
			rs.next();
			int id = rs.getInt(1);
			System.out.println("Max id: " + id);
			return id;
			
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
		//no records
		return -1;
	}
	
	private Address getAddress(Integer addressId) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT * FROM address WHERE ADDRESS_ID = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, addressId);
			
			ResultSet rs = stmt.executeQuery();
			
			String country = null;
			String town = null;
			String street = null;
			String houseNum = null;
			
			while (rs.next()) {
				
				country = rs.getString("COUNTRY");
				town = rs.getString("TOWN");
				street = rs.getString("STREET");
				houseNum = rs.getString("HOUSE_NUMBER");
				
			}
			if (country != null) {
				Address address = new Address(addressId, country, town, street, houseNum);
				
				return address;
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
	public void saveAddress(Address address) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "INSERT INTO address (COUNTRY, TOWN, STREET, HOUSE_NUMBER) "
						+ "VALUES (?,?,?,?)";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setString(1, address.getCountry());
			stmt.setString(2, address.getTown());
			stmt.setString(3, address.getStreet());
			stmt.setString(4, address.getHouseNumber());
			
			stmt.executeUpdate();
			System.out.println("Address saved in the database");
			
			
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
	public void editAddress(Address address) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "UPDATE address SET COUNTRY = ?, TOWN = ?, STREET = ?, HOUSE_NUMBER = ? "
						+ "WHERE ADDRESS_ID = ?";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setString(1, address.getCountry());
			stmt.setString(2, address.getTown());
			stmt.setString(3, address.getStreet());
			stmt.setString(4, address.getHouseNumber());
			stmt.setInt(5, address.getAddressId());
			
			stmt.executeUpdate();
			System.out.println("Address saved in the database");
			
			
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
	public void editAccount(Account account) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "UPDATE user SET USERNAME = ?, FULL_NAME = ?, EMAIL = ?, PASSWORD = ?, PHONE = ?, ROLE = ?, IS_APPROVED = ?,"
				+ " ADDRESS_ID = ? "
				+ "WHERE USER_ID = ?";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			System.out.println(account.getAddress().getAddressId());
			System.out.println(account.getFullName());
			
			stmt.setString(1, account.getUsername());
			stmt.setString(2, account.getFullName());
			stmt.setString(3, account.getEmail());
			stmt.setString(4, account.getPassword());
			stmt.setString(5, account.getPhone());
			stmt.setString(6, account.getRole());
			stmt.setBoolean(7, account.getIsApproved());
			stmt.setInt(8, account.getAddress().getAddressId());
			stmt.setInt(9, account.getUserId());
			
			stmt.executeUpdate();
			System.out.println("Account updated in the database");
			
			
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
	public void saveVehicle(Vehicle vehicle) {
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "INSERT INTO vehicle (MODEL, COUNTRY, YEAR, IS_APPROVED) "
						+ "VALUES (?,?,?,?)";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setString(1, vehicle.getModel());
			stmt.setString(2, vehicle.getCountry());
			stmt.setInt(3, vehicle.getYear());
			stmt.setBoolean(4, vehicle.getIsApproved());
			
			stmt.executeUpdate();
			System.out.println("Vehicle saved in the database");
			
			
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
	public int getMaxVehicleId() {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String query = "SELECT MAX(VEHICLE_ID) FROM vehicle";
		
		try {
			stmt = connection.prepareStatement(query);
			
			ResultSet rs = stmt.executeQuery();
			
			//System.out.println(rs);
			rs.next();
			int id = rs.getInt(1);
			System.out.println("Max id: " + id);
			return id;
			
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
		//no records
		return -1;
	}
	public void setVehicleId(int userId, int vehicleId) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "UPDATE user SET VEHICLE_ID = ? "
				+ "WHERE USER_ID = ?";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setInt(1, vehicleId);
			stmt.setInt(2, userId);
			
			stmt.executeUpdate();
			System.out.println("Vehicle id " + vehicleId + " was set in the database");
			
			
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
	public void editVehicle(Vehicle vehicle) {
		
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "UPDATE vehicle SET MODEL = ?, COUNTRY = ?, YEAR = ?, IS_APPROVED = ? "
				+ "WHERE VEHICLE_ID = ?";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setString(1, vehicle.getModel());
			stmt.setString(2, vehicle.getCountry());
			stmt.setInt(3, vehicle.getYear());
			stmt.setBoolean(4, vehicle.getIsApproved());
		
			stmt.setInt(5, vehicle.getVehicleId());
			
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
