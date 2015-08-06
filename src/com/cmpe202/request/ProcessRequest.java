package com.cmpe202.request;

import com.cmpe202.ride.Ride;

public class ProcessRequest implements RequestStateInterface{
	
	private Request request;
	
	public ProcessRequest(Request r){
		request = r;
	}

	public String receiveRequest() {
		// TODO Auto-generated method stub
		return "Already received a request";
	}

	public String processRequest(String requestType) {
		if(requestType=="member"){
			
		request.setRequestState(new InitiateRequest(request));
		return "Redirected to the requested page";}
		else if(requestType=="ride"){
			
			request.setRequestState(new InitiateRequest(request));
			String[] arguments = new String[] {"sravya@gmail.com"};
  	       // Ride.main(arguments);
  		
		return "Redirected to the requested page";	
		
		}
		else if(requestType=="pass"){
			request.setRequestState(new InitiateRequest(request));
			String[] arguments = new String[] {"sravya@gmail.com"};
  	      IssuePass.main(arguments);
          
		return "Redirected to the requested page";	}
		else
			request.setRequestState(new CancelRequest(request));
		return "Request has been cancelled";
			
	}

	public String cancelRequest() {
		
		return "A request must be evaluated first";
	}


}
