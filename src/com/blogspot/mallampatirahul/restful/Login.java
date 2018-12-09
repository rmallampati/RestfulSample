/**
 * 
 */
package com.blogspot.mallampatirahul.restful;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.blogspot.mallampatirahul.beans.LoginResult;
import com.blogspot.mallampatirahul.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.Connection;
/**
 * @author rahulmallampati
 *
 */

@Path(value="/login")
public class Login {
	@GET
	@Path(value="userID/{userID}/password/{password}")
	@Produces(value=MediaType.TEXT_PLAIN)
	public String login(@PathParam("userID") String userID, @PathParam("password")String password) {
		String returnValue="";
		ResultSet rs=null;
		Connection connection=null;
		Statement statement=null;
		LoginResult loginResult=new LoginResult();
		try {
			connection=Utility.getConnection();
			statement=connection.createStatement();
			rs=statement.executeQuery("select user_id, password, is_Admin from user_details where user_id='"+userID+"' and password='"+password+"'");
			loginResult.setLoginResult(String.valueOf(false));
			while(rs.next()) {
				System.out.println(rs.getString("user_id"));
				System.out.println(rs.getString("password"));
				loginResult.setAdmin(rs.getInt("is_Admin"));
				System.out.println(" isAdmin="+loginResult.isAdmin());
				loginResult.setLoginResult(String.valueOf(true));
			}
			GsonBuilder gsonBuilder=new GsonBuilder();
			Gson gson=gsonBuilder.create();
			returnValue=gson.toJson(loginResult);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		finally {
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
		
		return returnValue;
	}
}
