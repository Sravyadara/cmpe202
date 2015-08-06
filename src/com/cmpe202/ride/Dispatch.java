package com.cmpe202.ride;

import java.util.Date;

import com.cmpe202.member.Member;
import com.cmpe202.payment.Payment;
import com.cmpe202.request.Request;

public abstract class Dispatch {

	protected Payment p;
	private Request request;
    private Payment payment;
    private RideStateInterface rideState;
    
    private DispatchStrategyInterface ds;
	private int startTime;
	private int endTime;
	
	//  do we need to have a constructor here for Ride ?
	
	public Dispatch(){
    	rideState = new WaitingState(this);
    }
	
	/*public Dispatch(Payment paymentVariable) {
		this.p = paymentVariable;
	}*/
	
	public abstract void pay(int amount);
	
	public abstract long calculateAmount();
	
	protected void payByCC(Payment creditCard, int amount, int creditCardNumber, int CVV , String NameOnCard, String expirydate){
		// It has to receive payment object. Check constructor above.
		// Call paymnetObject.pay();
		creditCard.pay(amount, "");
		
		
		
	}
	protected void payByDC(Payment debitCard, int amount, int debitCardNumber, int CVV , String NameOnCard, String expirydate){
		// It has to receive payment object.
		// Call paymnetObject.pay();
		debitCard.pay(amount, "");
	}
	protected void redeemCoupon(Payment coupon, int amount, int couponNo){
		// It has to receive payment object.
		// Call paymnetObject.pay();
		coupon.pay(amount, "");
	}
	protected void payByCash(Payment cash, int amount){
		// It has to receive payment object.
		// Call paymnetObject.pay();
		cash.pay(amount, "");
	}
	protected void paywithPaypal(Payment paypal, int amount, String paypalAccountId){
		// It has to receive payment object.
		// Call paymnetObject.pay();
		paypal.pay(amount, "");
	}
	
	protected void payByPass(Payment pass, int amount, String passId) {
		pass.pay(amount, "");
	}
	
	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public void notifyDriver(){
		
	}
	public Member searchDriver(){
		ds = setStrategy("taxi");
		return ds.searchDriver("taxi");
	}
	public void notifyCustomer(){
		
	}
	
	public DispatchStrategyInterface setStrategy(String requesttype){
		if(requesttype.equals("Taxi")){
			ds = new TaxiDispatch();
			
		}else if(requesttype.equals("RideShare")){
			ds = new RideShareDispatch();
		}
		return ds;
		
	}
	
	public RideStateInterface getRideState() {
		return rideState;
	}
	public void setRideState(RideStateInterface rideState) {
		this.rideState = rideState;
	}	
    
	public void initiateRide(){
		this.rideState.initiateRide();
	}
	public void RideInTransit(){
		this.rideState.RideInTransit();
		
	}
	public void ConcludeRide(){
		this.rideState.concludeRide();
		
	}
	
	public long calcTimeTaken(){
		//timeTaken in minutes
		long timeTaken = ((startTime - endTime)/1000)/60;
		return timeTaken;
		
	}
		
}
