package com.cmpe202.payment;

import java.util.HashMap;

public class Pass extends Payment{

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		// TODO Auto-generated method stub
		//return "Paying through Weekly/Monthly Pass";
		return 0;
	}

	@Override
	public int calculateTotalAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notifyUserAboutAdditionalCharges() {
		// TODO Auto-generated method stub
		
	}

}
