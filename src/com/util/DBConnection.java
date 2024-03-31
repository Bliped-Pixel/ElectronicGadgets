package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection conn;
	
	public static Connection getDBConn() throws ClassNotFoundException, SQLException
	{
		String usernameDb = "root";
		String password = "";
		String urlDb = "jdbc:mysql://localhost:3306/TechShop";
		String driveName = "com.mysql.jdbc.Driver";
		
		Class.forName(driveName);
		
		conn = DriverManager.getConnection(urlDb,usernameDb,password);
		return conn;
	}
	
	public static void dbclose()throws SQLException{
		conn.close();
	}
	
}
