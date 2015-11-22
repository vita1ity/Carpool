package org.crama.carpool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.crama.carpool.model.Account;
import org.crama.carpool.model.Notification;
import org.crama.carpool.service.AccountService;

public class NotificationDAO {
	private static NotificationDAO instance = null;
	private static Connection connection = null;
	private NotificationDAO() {
		//connection = ConnectionManager.getMySqlConnection();
	}
	public static synchronized NotificationDAO getInstance() {
		if (instance == null) {
			instance = new NotificationDAO();			
		}
		return instance;
	}
	public void saveNotification(Notification notif) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "INSERT INTO notification (FROM_ID, TO_ID, DATE, MESSAGE, SEEN) "
						+ "VALUES (?,?,?,?,?)";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setInt(1, notif.getFrom().getUserId());
			stmt.setInt(2, notif.getTo().getUserId());
			stmt.setTimestamp(3, Timestamp.valueOf(notif.getDate()));
			stmt.setString(4, notif.getMessage());
			stmt.setBoolean(5, notif.getSeen());
		
			stmt.executeUpdate();
			System.out.println("Notification saved in the database");
			
			
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
	public List<Notification> getUserNotifications(int userId) {
		List<Notification> notificationList = new ArrayList<Notification>();
		AccountService accountService = AccountService.getInstance();
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		System.out.println("User notification. userId: " + userId);
		String query = "SELECT * FROM notification WHERE TO_ID = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			int id = 0;
			int toId = 0;
			int fromId = 0;
			String message = null;
			LocalDateTime date = null;
			boolean seen = false;
			
			while (rs.next()) {
				
				id = rs.getInt("ID");
				fromId = rs.getInt("FROM_ID");
				toId = rs.getInt("TO_ID");
				date = rs.getTimestamp("DATE").toLocalDateTime();
				message = rs.getString("MESSAGE");
				seen = rs.getBoolean("SEEN");
				if (id != 0) {
					Account from = accountService.getAccountById(fromId);
					Account to = accountService.getAccountById(toId);
					
					Notification notification = new Notification(id, from, to , date, message, seen);
					System.out.println("notification: " + notification);
					notificationList.add(notification);
				}
			}
			
			return notificationList;
			
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
	public void markAsSeen(Notification n) {
		connection = ConnectionManager.getMySqlConnection();
		PreparedStatement stmt = null;
		String saveQuery = "UPDATE notification SET SEEN = ? "
				+ "WHERE ID = ?";
		try {
			stmt = connection.prepareStatement(saveQuery);
			
			stmt.setBoolean(1, true);
			stmt.setInt(2, n.getId());
			
			stmt.executeUpdate();
			System.out.println("Notification updated in the database");
			
			
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
