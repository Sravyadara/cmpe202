package com.cmpe202.member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cmpe202.request.ConnectionFactory;
import com.cmpe202.request.DbUtil;

public abstract class Organizer extends Members {

	public void displayName() throws SQLException {
		
		// fetch data from db and run for loop
		//super.stateName();
		Connection connection = null;
		Statement statement = null;
		String query = "SELECT name FROM member where manager=\"" + mname + "\"";
		ResultSet rs = null;
		ArrayList<String> namesList = new ArrayList<String>();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					namesList.add(rs.getString("name"));
				}
			}else {
				System.out.println("Sorry, you do not have any reportess");
				
			}
			
		} finally {
			DbUtil.close(rs);
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		
		if (namesList.size() > 0) {
			System.out.println("\nBelow are your Reportes : ");
			for (String n : namesList) {
				System.out.println(n);
			}
		}
		
		
	}

}
