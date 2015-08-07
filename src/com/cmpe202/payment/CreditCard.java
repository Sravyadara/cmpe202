package com.cmpe202.payment;

import java.util.HashMap;

public class CreditCard extends Payment{

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		String paymentMessage = "";
		int paymentStatusCode = 0;
		//System.out.println("Paying through Credit Card");
		if(verifyCard()){
			System.out.println("Credit Card Verification Done... Forwading to Payment Gateway.");
			System.out.println("***** Please do not press any key ******");
			callSleep(5000);
			if(fwdToPaymentGateway()) {
				
				paymentMessage = notifyCustomer();
				paymentStatusCode = 1;
				System.out.println(notifyCustomer());
			}
			else {
				paymentMessage = "Transaction is incomplete. Please try again.";
				paymentStatusCode = 2;
				System.out.println("Transaction is incomplete. Please try again.");
			}
		}
		else {
			callSleep(2000);
			paymentMessage = "Invalid credit card. Please check and try again";
			paymentStatusCode = 3;
			System.out.println("Invalid credit card. Please check and try again");
		}
		// TODO Auto-generated method stub
		return paymentStatusCode;
	}
	
	public void callSleep(int mseconds) {
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	public String pay(){
		return null;
	}
	
	public boolean fwdToPaymentGateway() {
		return true;
	}
	public boolean verifyCard() {
		//Cross verify the card details with the values stored in database and send corrsponding boolean value.
		return false;
	}
	public String notifyCustomer() {
		notifyDispatcher();
		return "Payment accepted. Hope you enjoyed our service. Have a good one.";
	}

	public String notifyDispatcher() {
		return "Notifying Dispatcher on Successfull Payment"; 
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
