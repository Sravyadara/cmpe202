package com.cmpe202.ride;

import java.util.Scanner;

import com.cmpe202.member.Member;

public class Ride extends Dispatch { 
	
	@Override
	public void pay(int amount) {
		// TODO Auto-generated method stub
		// Display payment options menu here to customer. 
		System.out.println("Please choose from the below payment methods.");
		System.out.print("1. Credit Card" + "\n" + "2. Debit Card" + "\n" + "3. Paypal Account" + "\n" + "4. Pass" + "\n" + "5. Coupons" + "\n" + "6. Cash");
		System.out.println("Choose menu item : ");
		Scanner in = new Scanner(System.in);
		int menuItem = in.nextInt();
		int cardNumber, cvv, couponNumber;
		String cardHolderName, expiry, paypalAccountId, passId;
		
		switch(menuItem) {
			case 1:
				System.out.print("Please enter credit card number : ");
				cardNumber = in.nextInt();
				System.out.print("\n" + "Enter cardholder name as shown on the card : ");
				cardHolderName = in.nextLine();
				System.out.print("\n" + "Enter expiry date : ");
				expiry = in.nextLine();
				System.out.println("\n" + "Enter CVV number : ");
				cvv = in.nextInt();
				payByCC(amount, 0, cardNumber, cvv, cardHolderName, expiry );
				break;
			
			case 2:
				System.out.print("Please enter debit card number : ");
				cardNumber = in.nextInt();
				System.out.print("\n" + "Enter cardholder name as shown on the card : ");
				cardHolderName = in.nextLine();
				System.out.print("\n" + "Enter expiry date : ");
				expiry = in.nextLine();
				System.out.println("\n" + "Enter CVV number : ");
				cvv = in.nextInt();
				payByDC(amount, 0, cardNumber, cvv, cardHolderName, expiry );
				break;
				
			case 3:
				System.out.println("Redirecting to Paypal website ");
				System.out.print("Enter paypal account id : ");
				paypalAccountId = in.nextLine();
				paywithPaypal(amount, 0, paypalAccountId);
				break;
				
			case 4:
				System.out.print("Please enter weekly/monthly pass id : ");
				passId = in.nextLine();
				payByPass(amount, 0, passId);
				break;
			
			case 5:
				System.out.print("Please enter coupon number : ");
				couponNumber = in.nextInt();
				redeemCoupon(amount, 0, couponNumber);
				break;
				
			case 6:
				payByCash(amount, 0);
				break;
				
				
		}
		
		// Depending up on User selection , create that type of payment object using switch case and send it via payBycalls
				// TODO Auto-generated method stub
				// if paymentOption == "creditCard", call payByCC()
				// if paymentOption == "debitCard", call payByDC()
				// if paymentOption == "cash", call paywithPaypal()
				// if paymentOption == "coupon", call redeemCoupon()
		
		// Please not that all payByCalls will take different arguments like card details + AMOUNT to cross check against DB.
		
	}

	@Override
	public long calculateDistance() {
		// get time from calcutateTime() method and calculate distance here.
		// After that call pay method which is above this one by sending amount as argument to it.
		// That pay internally calls payByCalls().
		return 0;
	}	

}
