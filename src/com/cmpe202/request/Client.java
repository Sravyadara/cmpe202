package com.cmpe202.request;

import java.sql.SQLException;
import java.util.Scanner;

import com.cmpe202.member.Member;
import com.cmpe202.member.MemberDAO;

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
        // handle user commands
        boolean quit = false;
        int menuItem;
        do {
//              System.out.print("Choose menu item: ");
              menuItem = in.nextInt();
              switch (menuItem) {
              case 1:
            	  System.out.print("");
                    System.out.println("Welcome to Ride Service");
                    
                    req= new Request();
            		req.receiveRequest("ride");
            		
            		
                    break;
              case 2:
                    System.out.println("Pass Request");
                    req= new Request();
            		req.receiveRequest("pass");
            		
                    break;
              case 0:
                    quit = true;
                    break;
              default:
                    System.out.println("Invalid choice.");
              }
        } while (!quit);
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
			 displayMember(member);
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
