package com.examples.designpatterns.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * One Dao class per table or view
 * CRUD - Create, Retrieve, Update, Delete
 * @author PHaridass
 *
 */
public class UserDAO {

	public int addUser(UserDetails user) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("insert into login(username, password) values (?, ?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			int updated = ps.executeUpdate();
			
			ps.close();
			
			return updated;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getUser(int id) {
		Connection con = Database.getInstance().getConnection();
		
		String sql = "select id, username, password from login where id=? order by id";
		try {
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.close();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public List<UserDetails> getUserList(){
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		Connection con = Database.getInstance().getConnection();
		
		String sql = "select id, username, password from login order by id";
		try {
			Statement selectStatement = con.createStatement();
			ResultSet resultSet = selectStatement.executeQuery(sql);
			
			while(resultSet.next()) {
				//int id = resultSet.getInt("id");
				String name = resultSet.getString("username");
				String password = resultSet.getString("password");
				UserDetails userDetails = new UserDetails(name, password);
				userDetailsList.add(userDetails);
			}
			
			resultSet.close();
			selectStatement.close();
			
			return userDetailsList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		return new ArrayList<UserDetails>();
	}
	
	public int updateUser(UserDetails user) {
		Connection con = Database.getInstance().getConnection();
		
		String sql = "update login set username=?, password=? where id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getId());
			
			int updated = ps.executeUpdate();
			
			//con.close();
			ps.close();
			
			return updated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteUser(int id) {
		Connection con = Database.getInstance().getConnection();
		
		String sql = "delete from login where id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			int deleted = ps.executeUpdate();
			
			ps.close();
			
			return deleted;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
