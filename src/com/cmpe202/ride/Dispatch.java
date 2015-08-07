package com.cmpe202.ride;

import java.util.Date;
import java.util.HashMap;

import com.cmpe202.member.Member;
import com.cmpe202.payment.CreditCard;
import com.cmpe202.payment.Payment;
import com.cmpe202.request.Request;

public abstract class Dispatch {

	protected Payment p;
	private Request request;
    private Payment payment;
    private RideStateInterface rideState;
    
    private DispatchStrategyInterface ds;
	//private int startTime;
	//private int endTime;
	
	//  do we need to have a constructor here for Ride ?
	
	public Dispatch(){
    	rideState = new WaitingState(this);
    }
	
	public Dispatch(Payment paymentType) {
		this.p = paymentType;
	}
	
	public abstract void pay(int amount, HashMap<String, String> paymentModeDetails);
	
	public abstract long calculateAmount();
	
	protected void payByMode(int amount, HashMap<String, String> paymentModeDetails){
		p.pay(amount, paymentModeDetails);
		
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
