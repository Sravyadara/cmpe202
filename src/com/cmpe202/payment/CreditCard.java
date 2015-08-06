package com.cmpe202.payment;

import java.util.HashMap;

public class CreditCard extends Payment{

	@Override
	public String pay(int amount, HashMap<String, String> details) {
		String paymentMessage = "";
		System.out.println("Paying through Credit Card");
		if(verifyCard()){
			System.out.println("Credit Card Verification Done... Forwading to Payment Gateway.");
			System.out.println("***** Please do not press any key ******");
			if(fwdToPaymentGateway()) {
				paymentMessage = notifyCustomer();
				//System.out.println(notifyCustomer());
			}
			else {
				paymentMessage = "Transaction is incomplete. Please try again.";
				//System.out.println("Transaction is incomplete. Please try again.");
			}
		}
		else {
			paymentMessage = "Invalid credit card. Please check and try again";
			//System.out.println("Invalid credit card. Please check and try again");
		}
		// TODO Auto-generated method stub
		return paymentMessage;
	}
	
	public String pay(){
		return null;
	}
	
	public boolean fwdToPaymentGateway() {
		return true;
	}
	public boolean verifyCard() {
		//Cross verify the card details with the values stored in database and send corrsponding boolean value.
		return true;
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
