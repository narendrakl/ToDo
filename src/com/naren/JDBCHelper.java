package com.naren;

import java.sql.*;



public class JDBCHelper {

	public static Connection getConnection() throws SQLException
	{
		Connection con = null;
		
		try 
		{
			Class.forName(Constants.DRIVER);
			String url = Constants.URL;
			String user = Constants.USER;
			String pwd = Constants.PASSWORD;
			con = DriverManager.getConnection(url, user, pwd);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	public static void close(ResultSet rs)
	{
		if(rs!=null)
		{
			try 
			{
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement ps)
	{
		if(ps!=null)
		{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
