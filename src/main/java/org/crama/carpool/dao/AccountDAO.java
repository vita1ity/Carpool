package org.crama.carpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.crama.carpool.model.Account;

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
		String query = "SELECT * FROM user WHERE username = ?";
		
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
}
