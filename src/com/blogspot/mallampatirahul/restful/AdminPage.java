/**
 * 
 */
package com.blogspot.mallampatirahul.restful;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.blogspot.mallampatirahul.beans.User;
import com.blogspot.mallampatirahul.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.Connection;

/**
 * @author rahulmallampati
 *
 */
@Path(value="/admin")
public class AdminPage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AdminPage admin=new AdminPage();
		List<User> list=admin.getAllUsers();
		admin.deleteUser(list.get(0).getUserId());
	}
	
	public boolean deleteUser(String userId) {
		boolean returnValue=false;
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection=Utility.getConnection();
			statement=connection.prepareStatement("delete from user_details where user_id='"+userId+"'");
			int i=statement.executeUpdate();
			if(i==1) {
				returnValue=true;
				System.out.println("User with User ID "+userId+" is deleted.");
			}
			connection.commit();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}
	
	@GET
	@Path(value="getAllUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUsersToString() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson=gsonBuilder.create();
		String returnValue=gson.toJson(getAllUsers());
		System.out.println(returnValue);
		return returnValue;
	}
	
	public List<User> getAllUsers() {
		ResultSet rs=null;
		Connection connection=null;
		Statement statement=null;
		ArrayList<User> l=new ArrayList<User>();
		User user;
		try {
			connection=Utility.getConnection();
			statement=connection.createStatement();
			rs=statement.executeQuery("select user_id,password,user_name,user_email, is_Admin from user_details");
			while(rs.next()) {
				user=new User();
				user.setUserId(rs.getString("user_id"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("user_name"));
				user.setUserEmail(rs.getString("user_email"));
				user.setAdmin(rs.getInt("is_Admin")>0);
				System.out.println(user.toString());
				l.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return l;
	}
	//TODO need to be looked into tomorrow
	public boolean addUser(String userId, String userEmail, String password, boolean isAdmin) {
		boolean returnValue=false;
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection=Utility.getConnection();
			statement=connection.prepareStatement("insert into user_details(user_id, password, user_name,user_email, is_Admin) "
					+ "values('"+userId+"','"+password+"','"+userEmail+"',"+isAdmin+")");
			int i=statement.executeUpdate();
			if(i==1) {
				returnValue=true;
				System.out.println("User with User ID "+userId+" is deleted.");
			}
			connection.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}
}
