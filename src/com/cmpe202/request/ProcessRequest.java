package com.cmpe202.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.cmpe202.member.Member;
import com.cmpe202.ride.Dispatch;
import com.cmpe202.ride.DispatchDAO;
import com.cmpe202.ride.Ride;
import com.cmpe202.ride.RideDAO;
import com.cmpe202.member.Driver;

public class ProcessRequest implements RequestStateInterface {

	private Request request;
	private Connection reqconnection;
	private Statement reqstatement;

	public ProcessRequest(Request r) {
		request = r;
	}

	public String receiveRequest(String memberId, String requestType,
			String ridetype, String pickuploc, String dropoffloc,
			String vehicletype, String pickuptime, int noofseats) {
		// TODO Auto-generated method stub
		return "Already received a request";
	}

	public String receiveRequest(String memberId, String requestType) {
		// TODO Auto-generated method stub
		return "Already received a request";
	}

	// public String processRequest(String requestType) {
	// if(requestType=="member"){
	//
	// request.setRequestState(new InitiateRequest(request));
	// return "Redirected to the requested page";}
	// else if(requestType=="ride"){
	//
	// request.setRequestState(new InitiateRequest(request));
	// String[] arguments = new String[] {"sravya@gmail.com"};
	// // Ride.main(arguments);
	//
	// return "Redirected to the requested page";
	//
	// }
	// else if(requestType=="pass"){
	// request.setRequestState(new InitiateRequest(request));
	// String[] arguments = new String[] {"sravya@gmail.com"};
	// IssuePass.main(arguments);
	//
	// return "Redirected to the requested page"; }
	// else
	// request.setRequestState(new CancelRequest(request));
	// return "Request has been cancelled";
	//
	// }
	// public String processRequest(String requestType,Ride ride) {
	//
	// return "Request has been cancelled";
	// }

	public String processRequest(String requestType) {
		System.out.println("Request is in processing state");
		RideDAO ridedao = new RideDAO();
		Ride ride;
		DispatchDAO dispatchDAO = new DispatchDAO();
		if (requestType.equalsIgnoreCase("ride")) {
			try {
				int rideid = getMaxRideId();

				ride = ridedao.getRideDetails(rideid);
				//Member myMember = new Member();
				DispatchDAO dispatchdao = new DispatchDAO();
				Dispatch dispatch = new Ride();
				Driver driver = dispatch.searchDriver(ride);
				if (driver != null) {
					dispatch.initiateRide();
					HashMap<String, String> rideDetails = new HashMap<String, String>();
					rideDetails.put("requestId",
							Integer.toString(ride.getRequestid()));
					rideDetails.put("driverId", driver.getMemberid());
					rideDetails
							.put("memberId", dispatchdao
									.getCustomerByEmail(ride.getRequestid()));
					rideDetails.put("requestType", ride.getRidetype());
					dispatch.RideInTransit(rideDetails);
					dispatch.ConcludeRide(rideDetails);
					request.setRequestState(new ConcludeRequest(request));
					dispatchDAO
							.updateDriverStatus("free", driver.getMemberid());
				} else{
					System.out.println("Sorry, we do not have any drivers available. Please try again after some time");
				}
				// request.setRequestState(new ConcludeRequest(request));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			IssuePass issuepass = new IssuePass();
			issuepass.issuePass();
			request.setRequestState(new ConcludeRequest(request));
			return "Request is Served";
		}
		/*System.out.println("                "); */
		return "Request is Served";

	}

	public String concludeRequest() {
		// TODO Auto-generated method stub
		return "Already received a request";
	}

	public String cancelRequest() {

		return "A request must be evaluated first";
	}

	public int getMaxRideId() throws SQLException {
		int rideidcounter = 0;
		String query = "select * from ride";
		ResultSet rs = null;
		try {
			reqconnection = ConnectionFactory.getConnection();
			reqstatement = reqconnection.createStatement();
			rs = reqstatement.executeQuery(query);
			while (rs.next()) {

				rideidcounter++;

			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(reqstatement);
			DbUtil.close(reqconnection);
		}

		return rideidcounter;
	}

}