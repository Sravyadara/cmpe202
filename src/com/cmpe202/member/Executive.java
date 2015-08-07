package com.cmpe202.member;

public class Executive extends Organizer {
	
	public Executive( String aName ) {
		super();
		name = aName;
		title = "Manager";		
		}
	
	public void stateName() {
		// do processing special to manager naming
		
		super.stateName();
		}

}
