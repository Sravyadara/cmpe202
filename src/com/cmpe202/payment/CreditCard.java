package com.cmpe202.payment;

import java.util.HashMap;

import com.cmpe202.rules.PaymentRules;
import com.cmpe202.rules.Rules;

public class CreditCard extends Payment {

	@Override
	public int pay(int amount, HashMap<String, String> details) {
		String paymentMessage = "";
		int paymentStatusCode = 0;
		// String cardNumber, cvv, expiryDate;
		// System.out.println("Paying through Credit Card");
		if (verifyCard(details.get("cardNumber"), details.get("cvv"),
				details.get("expiry"))) {
			System.out
					.println("Credit Card Verification Done... Forwading to Payment Gateway.");
			System.out.println("***** Please do not press any key ******");
			callSleep(5000);
			if (fwdToPaymentGateway()) {

				notifyCustomer();
				paymentStatusCode = 1;
				
			} else {
				paymentMessage = "Transaction is incomplete. Please try again.";
				paymentStatusCode = 2;
				System.out
						.println("Transaction is incomplete. Please try again.");
			}
		} else {
			callSleep(2000);
			paymentMessage = "Invalid credit card. Please check and try again";
			paymentStatusCode = 3;
			System.out
					.println("Invalid credit card. Please check and try again");
		}
		// TODO Auto-generated method stub
		return paymentStatusCode;
	}

	public boolean verifyCard(String cardNumber, String cvv, String expiry) {
		Rules paymentRules = new PaymentRules(cardNumber, cvv, expiry);
		return paymentRules.validate();
	}

	@Override
	public int calculateTotalAmount(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notifyUserAboutAdditionalCharges() {
		// TODO Auto-generated method stub

	}

}
