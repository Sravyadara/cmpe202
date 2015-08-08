package com.cmpe202.member;

public class Customer extends Member {

	public Customer() {
		title = "Customer";
	}

	public Customer(String aName) {
		this();
		name = aName;
	}

	public void stateName() {
		super.stateName();
	}

}
