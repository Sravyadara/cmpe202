package com.cmpe202.payment;

public class PayPal extends Payment{

	@Override
	public String pay(int amount, String details) {
		String paymentMessage = "";
		System.out.println("Paying through Paypal Account");
		if(verifyAccount()){
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
			paymentMessage = "Invalid Credentials. Please check and try again";
			//System.out.println("Invalid credit card. Please check and try again");
		}
		// TODO Auto-generated method stub
		return paymentMessage;
	}
	
	public boolean fwdToPaymentGateway() {
		return true;
	}
	public boolean verifyAccount() {
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
