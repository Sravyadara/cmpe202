package com.cmpe202.request;

import com.cmpe202.payment.Payment;

public class WeeklyPass extends IssuePass{

	private int amount;
	private int noOfDays;
	
	public WeeklyPass(Payment p, int amount, int noOfDays){
		super(p);
		this.amount = amount;
		this.noOfDays = noOfDays;
	}
	
	public void pay(){
		
	}
}
