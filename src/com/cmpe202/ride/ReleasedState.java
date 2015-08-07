package com.cmpe202.ride;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.cmpe202.payment.PeakHourBill;
import com.cmpe202.payment.RegularBill;
import com.cmpe202.request.DBQuery;




public class ReleasedState implements RideStateInterface{
	
	private Dispatch dispatchStateContext;
	Dispatch d1;
	Payment p;
	String cardNumber, cvv;
	String cardHolderName, expiry, paypalAccountId, passId, couponId;
	Scanner in;
	HashMap<String, String> paymentDetails = new HashMap<String, String>();
	private int endTime;
	private int totalRideAmount;
	
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
		//Display Start Button
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the option to start the ride");
		System.out.println("1.Stop the Ride");
		int menuItem = in.nextInt();
		if(menuItem == 1){
		Calendar cal = Calendar.getInstance();
		
		//time in milli seconds
		  endTime = (int) cal.getTimeInMillis();
		  dispatchStateContext.setRideEndTime(endTime);
		  
		  //time in hh:mm
		  final String eTime =
		    	    new SimpleDateFormat("HH:mm").format(cal.getTime());
		 DBQuery rideDAO = new DBQuery();
		 try {
			rideDAO.updateEndTime(eTime, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try {
			rideDAO.updateRideStatus(dispatchStateContext, 1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		}
	
		return endTime;
        
		
			
	}
	
	public void calculateTotalTime(){
		
		int totalTime = (int) (dispatchStateContext.getRideEndTime() - dispatchStateContext.getRideEndTime());
		dispatchStateContext.setRideTimeTaken(totalTime);
	}
	
	public int calculateAmount(){
		
		int amount = (int) (((dispatchStateContext.getRideTimeTaken()/1000)/60)%60);
		amount = amount * 2;
		dispatchStateContext.setBillGenerated(amount);
		return amount;
	
		
		
	}
	
    public void calculateRideAmount(int amount) {
		
		Payment p2 = new RegularBill();
		
		 try {
			
			if(p2.isPeakHour() == true ){
				  p2 = new PeakHourBill();
				   totalRideAmount = p2.calculateTotalAmount(amount);
			 }else
				   totalRideAmount = p2.calculateTotalAmount(amount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 showPaymentOptions(1,totalRideAmount);
		/* * Payment p2 = new PeakHorBill()
		 * total = p2.calculateBill(amount)
		 * if false the create RegularBill
		 * 
		showPaymentOptions(reid/ memid , total)*/
	
	}
	 
		 
	
	public void showPaymentOptions(int memberId, int amount) {
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
