package com.cmpe202.request;

import java.sql.SQLException;
import java.util.Scanner;

import com.cmpe202.member.Member;
import com.cmpe202.member.MemberDAO;
<<<<<<< HEAD
import com.cmpe202.ride.Dispatch;
import com.cmpe202.ride.EngagedState;
import com.cmpe202.ride.ReleasedState;
=======
import com.cmpe202.ride.Ride;

public class Client {
	
	private static void displayMember(Member member) {
        System.out.println("Member ID:" + member.getMemberid());
        System.out.println("Member Name:" + member.getMemberName());
 
        /*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dob = format.format(employee.getDob());
 */
        System.out.println("role:" + member.getRole());
        System.out.println("address:" + member.getAddress());
        System.out.println("contact" + member.getContact());
        System.out.println();
    }
	public static void callMenu(String memberId){
		Scanner in = new Scanner(System.in);
		System.out.print("**********************************************************\n");
		System.out.print("                    Available Services                      \n");
		System.out.print("**********************************************************\n");
        System.out.println("1. Ride Request");
        System.out.println("2. Pass Request");
        System.out.println("0. Quit");
        System.out.print("Choose menu item: ");
        String reqType;
        // handle user commands
        boolean quit = false;
        int menuItem;
       
//              System.out.print("Choose menu item: ");
              menuItem = in.nextInt();
              switch (menuItem) {
              case 1:
            	    {System.out.print("");
                    System.out.println("Welcome to Ride Service");
                    System.out.println("-----------------------");
                    req= new Request();
                    reqType= "ride";
            		
            		 Scanner scanner = new Scanner(System.in);
                     String ridetype,pickuploc,dropoffloc,vehicletype,pickuptime;
               
                     int noofseats;
                     System.out.println("Enter PickUp Location");
                     pickuploc=scanner.nextLine();
                     System.out.println("Enter DropOff Location:");
                     dropoffloc=scanner.nextLine();
                     System.out.println("Enter Ride Type");
                     System.out.println("a. Taxi");
                     System.out.println("b. Ride Share");
                     ridetype=scanner.nextLine();
                     if(ridetype.equalsIgnoreCase("a"))
                     {
                    	 ridetype= "Taxi"; 
                     }
                     else
                     {
                    	 ridetype="Ride Share";
                     }
                     System.out.println("Enter PickUp Time(HH:MM)");
                     pickuptime=scanner.nextLine();
                     System.out.println("Enter No of Passengers");
                     noofseats=scanner.nextInt();
                     System.out.println("Enter Vehicle Type");
                    
                     System.out.println("1. Sedan");
                     System.out.println("2. SUV");
                     System.out.println("3. Luxury");
                     int veh=scanner.nextInt();
                     
                     if(veh==1)
                     {
                    	 vehicletype= "Sedan"; 
                     }
                     else if(veh==2)
                     {
                    	 vehicletype="SUV";
                     }
                     else 
                     {
                    	 vehicletype="Luxury";
                     }
                     
                     
                     System.out.println("Your request got submitted, PLease wait for the response");
                     req.receiveRequest(memberId,reqType);
                     
                     

                    }
                    break;
              case 2:
            	    reqType= "pass";
                    System.out.println("Pass Request");
                    req= new Request();
            		req.receiveRequest(memberId,reqType);
            		
                    break;
              case 0:
                    quit = true;
                    break;
              default:
                    System.out.println("Invalid choice.");
              }
//        } while (!quit);
        //System.out.println("Bye-bye!");
		
	
	}
	

	static Request req;
	public static void main(String[] args) 
	{   
		 try {
		String memberId="sravya@gmail.com";	 
		MemberDAO memberDao = new MemberDAO();
		Member member = memberDao.getMember(memberId);
		 if(member != null)
			 {System.out.println("Success");
			 //displayMember(member);

			 callMenu(memberId);
			 }
         else
             System.out.println("No Member with Id: "+ memberId);
     } catch (NumberFormatException e) {
         e.printStackTrace();
     } catch (SQLException e) {
         e.printStackTrace();
     }
		 
	} 
	

	    
		

}
