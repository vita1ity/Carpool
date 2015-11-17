package org.crama.carpool.dao;

import java.sql.*; 

public class ConnectionManager {

	private static Connection con; 
	private static String url;
	private static String username;
	private static String password;
	public static Connection getMySqlConnection() { 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			url = "jdbc:mysql://localhost:3306/carpool";
			username = "root";
			password = "root";
			con = DriverManager.getConnection(url, username, password);			 
			
		} catch(SQLException e) {
			e.printStackTrace();			 
		} catch(ClassNotFoundException e){
			System.out.println(e);
		} catch(InstantiationException e){
			System.out.println(e);
		} catch(IllegalAccessException e){
			System.out.println(e);
		}
		
		return con; 
	}
}