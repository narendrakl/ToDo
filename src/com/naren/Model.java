package com.naren;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Model {
	PreparedStatement ps=null;
	ResultSet rs=null;
	String sql ="";
	String msg ="";
	public String save_task(ToDoBean td) {
		String Date = td.getDate().toString();
		String Task = td.getTask();
		try
		{
			Connection con = (Connection)JDBCHelper.getConnection();
			if(con == null)
			{
				msg = "Database is not working!!";
			}
			else
			{
				con.setAutoCommit(false);
				sql = "insert into tasks(date, title) values(?,?) ";
				ps = con.prepareStatement(sql);
				ps.setString(1, Date);
				ps.setString(2, Task);
				ps.execute();
				con.commit();
				//msg = Date+" "+Task;
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		finally{
			try {
				ps.close();
				//rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return msg;
	}
	public String dispTask() {
		
		Connection con;
		try {
			con = (Connection)JDBCHelper.getConnection();
			if(con == null)
			{
				msg = "Database is not working!!";
			}
			else
			{
				sql = "select * from tasks order by id desc";
				ps = con.prepareStatement(sql);
				ps.execute();
				rs = ps.getResultSet();
				while(rs.next())
				{
					msg = msg + rs.getString("date")+"@"+rs.getString("title")+"/";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}

}
