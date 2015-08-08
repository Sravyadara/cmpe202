package com.cmpe202.member;

import java.sql.SQLException;

public class MembersClient {
	public static Members m;
	
	public static void doClientTasks() throws SQLException {
		m.displayName();
	}

}
