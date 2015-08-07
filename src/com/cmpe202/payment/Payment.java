package com.cmpe202.payment;

import java.util.HashMap;

public abstract class Payment {

	abstract public int pay(int amount, HashMap<String, String> details);
	
	public void calculateBill(){
		if (isPeakHour()){
			notifyUserAboutAdditionalCharges();
		}
		calculateTotalAmount();
	}
	public abstract int calculateTotalAmount();
	public boolean isPeakHour(){
		return true;
	}
	public abstract void notifyUserAboutAdditionalCharges();	
	}
