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
	public void pay() {
		// TODO Auto-generated method stub
		
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
