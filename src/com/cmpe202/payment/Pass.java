package com.cmpe202.payment;

public class Pass extends Payment{

	@Override
	public String pay(int amount, String details) {
		// TODO Auto-generated method stub
		return "Paying through Weekly/Monthly Pass";
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
