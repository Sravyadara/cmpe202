package com.cmpe202.member;

import java.sql.SQLException;

public class Drivers extends Members{

	public Drivers() {
		mtitle = "Driver";
	}

	public Drivers(String aName) {
		this();
		mname = aName;
	}

	public void stateName() throws SQLException {
		super.stateName();
	}
}
