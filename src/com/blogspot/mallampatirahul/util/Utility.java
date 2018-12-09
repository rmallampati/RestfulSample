/**
 * 
 */
package com.blogspot.mallampatirahul.util;

import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

/**
 * @author rahulmallampati
 *
 */
public class Utility {
	public static Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Class<?> clazz=Class.forName("com.mysql.jdbc.Driver");
		Driver driver=(Driver) clazz.newInstance();
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "VenkuSyama16");
		Connection connection=(Connection) driver.connect("jdbc:mysql://localhost:3306/Restful_Angular_JS_DB", prop);
		return connection;
	}
}
