package com.cmpe202.member;

public class Customers extends Members {

	public Customers() {
		title = "Customer";
	}

	public Customers(String aName) {
		this();
		name = aName;
	}

	public void stateName() {
		super.stateName();
	}

}
