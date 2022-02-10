package com.examples.designpatterns.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	public Connection connection;
	
	public static Database instance = new Database();
	
	private Database() {
		
	}
	
	public static Database getInstance() {
		return instance;
	}
	
	/*
	public static Database instanceOld;

	public static Database getInstanceOld() {
		if (instanceOld == null) {
			instanceOld = new Database();
		}
		return instanceOld;
	} */
	
	public Connection getConnection() {
		return connection;
	}
	
	public void connect() throws Exception {

		if (connection != null)
			return;

		/*
		 * try { Class.forName("com.mysql.cj.jdbc.Driver");
		 * 
		 * } catch (Exception e) { throw new Exception("Driver not found"); }
		 */

		String url = String.format("jdbc:mysql://localhost:%d/workout", 3306);

		connection = DriverManager.getConnection(url, "root", "welcome");
	}
	
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println("Can't close connection");
			}
		}
	}
	 
}
