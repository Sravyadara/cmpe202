package com.cmpe202.ride;

import java.util.Scanner;

import com.cmpe202.member.Member;
import com.cmpe202.payment.Cash;
import com.cmpe202.payment.Coupons;
import com.cmpe202.payment.CreditCard;
import com.cmpe202.payment.DebitCard;
import com.cmpe202.payment.Pass;
import com.cmpe202.payment.PayPal;

public class Ride extends Dispatch { 
	
	@Override
	public void pay(int amount) {
		// TODO Auto-generated method stub
		// Display payment options menu here to customer. 
		System.out.println("Please choose from the below payment methods.");
		System.out.print("1. Pay Using Registered Card " + "\n" + "2. Use Another Credit Card" + "\n" + "3. Debit Card" + "\n" + "4. Paypal Account" + "\n" + "5. Pass" + "\n" + "6. Coupons" + "\n" + "7. Cash");
		System.out.println("Choose menu item : ");
		Scanner in = new Scanner(System.in);
		int menuItem = in.nextInt();
		int cardNumber, cvv, couponNumber;
		String cardHolderName, expiry, paypalAccountId, passId;
		
		switch(menuItem) {
			case 1:
				break;
			case 2:
				System.out.print("Please enter credit card number : ");
				cardNumber = in.nextInt();
				System.out.print("\n" + "Enter cardholder name as shown on the card : ");
				cardHolderName = in.nextLine();
				System.out.print("\n" + "Enter expiry date : ");
				expiry = in.nextLine();
				System.out.println("\n" + "Enter CVV number : ");
				cvv = in.nextInt();
				CreditCard creditCard = new CreditCard();
				payByCC(creditCard, amount, cardNumber, cvv, cardHolderName, expiry );
				break;
			
			case 3:
				System.out.print("Please enter debit card number : ");
				cardNumber = in.nextInt();
				System.out.print("\n" + "Enter cardholder name as shown on the card : ");
				cardHolderName = in.nextLine();
				System.out.print("\n" + "Enter expiry date : ");
				expiry = in.nextLine();
				System.out.println("\n" + "Enter CVV number : ");
				cvv = in.nextInt();
				DebitCard debitCard = new DebitCard();
				payByDC(debitCard, amount, cardNumber, cvv, cardHolderName, expiry );
				break;
				
			case 4:
				System.out.println("Redirecting to Paypal website ");
				System.out.print("Enter paypal account id : ");
				paypalAccountId = in.nextLine();
				PayPal paypal = new PayPal();
				paywithPaypal(paypal, amount, paypalAccountId);
				break;
				
			case 5:
				System.out.print("Please enter weekly/monthly pass id : ");
				passId = in.nextLine();
				Pass pass = new Pass();
				payByPass(pass, amount, passId);
				break;
			
			case 6:
				System.out.print("Please enter coupon number : ");
				couponNumber = in.nextInt();
				Coupons coupon = new Coupons();
				redeemCoupon(coupon, amount, couponNumber);
				break;
				
			case 7:
				Cash cash = new Cash();
				payByCash(cash, amount);
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
	public long calculateAmount() {
		// get time from calcutateTime() method and calculate distance here.
		// After that call pay method which is above this one by sending amount as argument to it.
		// That pay internally calls payByCalls().
		return 0;
	}	
	
	
}
