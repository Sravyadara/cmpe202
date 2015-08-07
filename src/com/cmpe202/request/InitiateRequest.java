package com.cmpe202.request;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cmpe202.member.Member;

public class InitiateRequest implements RequestStateInterface{
	
	private Request request;
	private Connection reqconnection;
    private Statement reqstatement;
	
	public InitiateRequest(Request r){
		request = r;
	}

	public String receiveRequest(String memberId,String requestType) {
		// TODO Auto-generated method stub
	try{	
		insertRequest(memberId,requestType);
		int reqid=getMaxRequestId();
		System.out.println("RequestID "+ reqid);
	}
	catch(SQLException e)
	{
		
	}
		
	 	   request.setRequestState(new ProcessRequest(request));
		   
		return "Received a request";
	}

	public String processRequest(String requestType) {
		// TODO Auto-generated method stub
		return "Must receive a request first";
	}

	public String cancelRequest() {
		// TODO Auto-generated method stub
		return "Must receive a request first";
	}

	public void insertRequest(String memberId,String reqType)throws SQLException {
		  try{
			  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	   //get current date time with Date()
		 	   Date date = new Date();
		 	   String reqdate= dateFormat.format(date).toString();
		 	   String stareq="Initiated";
	       reqconnection = ConnectionFactory.getConnection();
	       reqstatement = reqconnection.createStatement();
	       String sql = "INSERT INTO request (member_emailid, requesttype,request_state,date)" +
	                    "VALUES (" + "\""  + memberId + "\""  + "," + "\""  + reqType + "\""  + "," + "\""  +stareq + "\""  +","+ "\""  +reqdate + "\""  +")";
	       reqstatement.executeUpdate(sql);
	       System.out.println("Inserted into Request table");
		      
	
	}finally {
        DbUtil.close(reqstatement);
        DbUtil.close(reqconnection);
    }


}
	public int getMaxRequestId() throws SQLException
	{   int requestid = 0;
		String query = "select * from request";
        ResultSet rs = null;
        Member member = null;
        try {
            reqconnection = ConnectionFactory.getConnection();
            reqstatement = reqconnection.createStatement();
            rs = reqstatement.executeQuery(query);
           while (rs.next()) {
            	
            	requestid++;	
            	
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(reqstatement);
            DbUtil.close(reqconnection);
        }
       
		return requestid;
	}
}
