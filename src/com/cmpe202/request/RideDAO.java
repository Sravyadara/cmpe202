package com.cmpe202.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cmpe202.member.Member;
import com.cmpe202.ride.Dispatch;

public class RideDAO {
	private Connection connection;
    private Statement statement;
    
   
 
    public RideDAO() { }
 
    public int updateStartTime(int StartTime, int requestId) throws SQLException{

        System.out.println("------Entered into updateStartTime() method in DataAccess------");
        int status = 0;
        connection = ConnectionFactory.getConnection();
        try {
            statement = connection.createStatement();
            String sql;
            sql = " UPDATE ride SET pickuptime="+StartTime+" WHERE requestid="+requestId +"";
            System.out.println("Update Start Time in ride table: " + sql);
            status = statement.executeUpdate(sql);
            connection.close();
        } catch (Exception e) {
            System.out.println("ERROR while updating start Time: "+ e.getMessage());
        }
        finally{
            connection.close();
        }
        System.out.println("------Exit from updateStartTime() method in DataAccess------");
        return status;
    }
    
    public int updateEndTime(int endTime, int requestId) throws SQLException{

        System.out.println("------Entered into updateEndTime() method in DataAccess------");
        int status = 0;
        connection = ConnectionFactory.getConnection();
        try {
            statement = connection.createStatement();
            String sql;
            sql = " UPDATE ride SET endtime="+endTime+" WHERE requestid="+requestId +"";
            System.out.println("Update end Time in ride table: " + sql);
            status = statement.executeUpdate(sql);
            connection.close();
        } catch (Exception e) {
            System.out.println("ERROR while updating end Time: "+ e.getMessage());
        }
        finally{
            connection.close();
        }
        System.out.println("------Exit from updateEndTime() method in DataAccess------");
        return status;
    }
    
    public int updateRideStatus(Dispatch rideState, int requestId) throws SQLException{

        System.out.println("------Entered into updateRideStatus() method in DataAccess------");
        int status = 0;
        connection = ConnectionFactory.getConnection();
        try {
            statement = connection.createStatement();
            String sql;
            sql = " UPDATE ride SET ridestate="+rideState+" WHERE requestid="+requestId +"";
            System.out.println("Update ridestate in ride table: " + sql);
            status = statement.executeUpdate(sql);
            connection.close();
        } catch (Exception e) {
            System.out.println("ERROR while updating ridestate: "+ e.getMessage());
        }
        finally{
            connection.close();
        }
        System.out.println("------Exit from updateRideStatus() method in DataAccess------");
        return status;
    }
    
    
    

}
