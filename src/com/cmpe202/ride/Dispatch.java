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
	
	public abstract long calculateDistance();
	
	protected void payByCC(int amount, int noOfDays, int creditCardNumber, int CVV , String NameOnCard, String expirydate){
		// It has to receive payment object. Check constructor above.
		// Call paymnetObject.pay();
		
		
	}
	protected void payByDC(int amount, int noOfDays, int debitCardNumber, int CVV , String NameOnCard, String expirydate){
		// It has to receive payment object.
		// Call paymnetObject.pay();
	}
	protected void redeemCoupon(int amount, int noOfDays, int couponNo){
		// It has to receive payment object.
		// Call paymnetObject.pay();
	}
	protected void payByCash(int amount, int noOfDays){
		// It has to receive payment object.
		// Call paymnetObject.pay();
	}
	protected void paywithPaypal(int amount, int noOfDays, String paypalAccountId){
		// It has to receive payment object.
		// Call paymnetObject.pay();
	}
	
	protected void payByPass(int amount, int noOfDays, String passId) {
		
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
