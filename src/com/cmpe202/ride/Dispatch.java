package com.cmpe202.ride;

import com.cmpe202.member.Member;

public class Dispatch extends Ride {
	
	private DispatchStrategyInterface ds;
	
	public void notifyDriver(){
		
	}
	public Member searchDriver(){
		ds = setStrategy("taxi");
		return ds.searchDriver("taxi");
	}
	public void notifyCustomer(){
		
	}
	@Override
	public void pay(int amount) {
		// TODO Auto-generated method stub
		// Display payment options menu here to customer. 
		// Depending up on User selection , create that type of payment object using switch case and send it via payBycalls
				// TODO Auto-generated method stub
				// if paymentOption == "creditCard", call payByCC()
				// if paymentOption == "debitCard", call payByDC()
				// if paymentOption == "cash", call paywithPaypal()
				// if paymentOption == "coupon", call redeemCoupon()
		
		// Please not that all payByCalls will take different arguments like card details + AMOUNT to cross check against DB.
		
	}
	
	public DispatchStrategyInterface setStrategy(String requesttype){
		if(requesttype.equals("Taxi")){
			ds = new TaxiDispatch();
			
		}else if(requesttype.equals("RideShare")){
			ds = new RideShareDispatch();
		}
		return ds;
		
	}

}
