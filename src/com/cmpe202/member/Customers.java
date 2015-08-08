package com.cmpe202.member;

import java.sql.SQLException;

public class Customers extends Members {

	public Customers() {
		mtitle = "Customer";
	}

	public Customers(String aName) {
		this();
		mname = aName;
	}

	public void stateName() throws SQLException {
		super.stateName();
	}

}
