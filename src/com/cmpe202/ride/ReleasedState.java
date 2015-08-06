package com.cmpe202.ride;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.cmpe202.payment.Cash;
import com.cmpe202.payment.Coupons;
import com.cmpe202.payment.CreditCard;
import com.cmpe202.payment.DebitCard;
import com.cmpe202.payment.Pass;
import com.cmpe202.payment.PayPal;
import com.cmpe202.payment.Payment;




public class ReleasedState implements RideStateInterface{
	
	private Dispatch dispatchStateContext;
	Dispatch d1;
	Payment p;
	String cardNumber, cvv;
	String cardHolderName, expiry, paypalAccountId, passId, couponId;
	Scanner in;
	HashMap<String, String> paymentDetails = new HashMap<String, String>();
	
	
	public ReleasedState(Dispatch d){
		dispatchStateContext = d;
	}

	@Override
	public void initiateRide() {
		// TODO Auto-generated method stub
		System.out.println("Ride is already initiated");
		
	}

	@Override
	public int RideInTransit() {
		// TODO Auto-generated method stub
		System.out.println("Ride is already in Transit");
		return 0;
		
	}

	@Override
	public int concludeRide() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		 int eTime = (int) cal.getTimeInMillis();
		 dispatchStateContext.setEndTime(eTime);
		return eTime;
        
		
			
	}
	
	public void showPaymentOptions(String memberId, int amount) {
		System.out.println("Please choose from the below payment methods.");
		System.out.print("1. Pay Using Registered Card " + "\n" + "2. Use Another Credit Card" + "\n" + "3. Debit Card" + "\n" + "4. Paypal Account" + "\n" + "5. Pass" + "\n" + "6. Coupons" + "\n" + "7. Cash");
		System.out.println("Choose menu item : ");
		in = new Scanner(System.in);
		int menuItem = in.nextInt();		
		
		switch(menuItem) {
			case 1:
				p = new CreditCard();
				d1 = new Ride(p);
				// fetch details from DB and construct a hashmap.
				//payUsingRegisteredCard(amount);
				d1.pay(amount, paymentDetails);
				break;
				
			case 2:
				System.out.print("Please enter credit card number : ");
				cardNumber = in.nextLine();
				paymentDetails.put("cardNumber", cardNumber);
				System.out.print("\n" + "Enter cardholder name as shown on the card : ");
				cardHolderName = in.nextLine();
				paymentDetails.put("cardHolderName", cardHolderName);
				System.out.print("\n" + "Enter expiry date : ");
				expiry = in.nextLine();
				paymentDetails.put("expiry", expiry);
				System.out.println("\n" + "Enter CVV number : ");
				cvv = in.nextLine();
				paymentDetails.put("cvv", cvv);
				p = new CreditCard();
				//payByCC(amount, cardNumber, cvv, cardHolderName, expiry );
				d1 = new Ride(p);
				d1.pay(amount, paymentDetails);
				
				break;
			
			case 3:
				System.out.print("Please enter debit card number : ");
				cardNumber = in.nextLine();
				paymentDetails.put("cardNumber", cardNumber);
				System.out.print("\n" + "Enter cardholder name as shown on the card : ");
				cardHolderName = in.nextLine();
				paymentDetails.put("cardHolderName", cardHolderName);
				System.out.print("\n" + "Enter expiry date : ");
				expiry = in.nextLine();
				paymentDetails.put("expiry", expiry);
				System.out.println("\n" + "Enter CVV number : ");
				cvv = in.nextLine();
				paymentDetails.put("cvv", cvv);
				p = new DebitCard();
				//payByDC(amount, cardNumber, cvv, cardHolderName, expiry );
				d1 = new Ride(p);
				d1.pay(amount, paymentDetails);
				
				break;
				
			case 4:
				System.out.println("Redirecting to Paypal website ");
				System.out.print("Enter paypal account id : ");
				paypalAccountId = in.nextLine();
				paymentDetails.put("paypalAccountId", paypalAccountId);
				p = new PayPal();
				//paywithPaypal(amount, paypalAccountId);
				d1 = new Ride(p);
				d1.pay(amount, paymentDetails);
				break;
				
			case 5:
				System.out.print("Please enter weekly/monthly pass id : ");
				passId = in.nextLine();
				paymentDetails.put("passId", passId);
				p = new Pass();
				//payByPass(amount, passId);
				d1 = new Ride(p);
				d1.pay(amount, paymentDetails);
				break;
			
			case 6:
				System.out.print("Please enter coupon number : ");
				couponId = in.nextLine();
				paymentDetails.put("couponId", couponId);
				p = new Coupons();
				//redeemCoupon(amount, couponId);
				d1 = new Ride(p);
				d1.pay(amount, paymentDetails);
				break;
				
			case 7:
				p = new Cash();
				//payByCash(amount);
				d1 = new Ride(p);
				d1.pay(amount, paymentDetails);
				break;
				
		}
		
	}

}
