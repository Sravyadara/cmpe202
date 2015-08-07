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
	
	
	public String notifyCustomer() {
		notifyDispatcher();
		return "Payment accepted. Hope you enjoyed our service. Have a good one.";
	}

	public String notifyDispatcher() {
		return "Notifying Dispatcher on Successfull Payment"; 
	}
	
	public boolean fwdToPaymentGateway() {
		return true;
	}
	
	public void callSleep(int mseconds) {
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	}
