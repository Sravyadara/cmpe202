package com.cmpe202.member;

import java.sql.SQLException;

public class Executive extends Organizer {

	public Executive(String aName) {
		super();
		mname = aName;
		mtitle = "Manager";
	}

	public void stateName() throws SQLException {
		// do processing special to manager naming

		super.stateName();
	}

}
