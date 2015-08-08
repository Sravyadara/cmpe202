package com.cmpe202.member;

public class Drivers extends Members{

	public Drivers() {
		title = "Driver";
	}

	public Drivers(String aName) {
		this();
		name = aName;
	}

	public void stateName() {
		super.stateName();
	}
}
