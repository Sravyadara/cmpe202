package com.cmpe202.request;

import java.sql.SQLException;
import java.util.Scanner;

import com.cmpe202.member.Executive;
import com.cmpe202.member.Member;
import com.cmpe202.member.MemberDAO;
import com.cmpe202.member.MembersClient;
import com.cmpe202.ride.Ride;
import com.cmpe202.request.SignUpForm;

public class Client {

	private static void displayMember(Member member) {
		System.out.println("Member ID:" + member.getMemberid());
		System.out.println("Member Name:" + member.getMemberName());
		System.out.println("role:" + member.getRole());
		System.out.println("address:" + member.getAddress());
		System.out.println("contact" + member.getContact());
		System.out.println();
	}

	public static void callMenu(String memberId) {
		Scanner in = new Scanner(System.in);
		System.out
				.print("**********************************************************\n");
		System.out
				.print("                    Available Services                      \n");
		System.out
				.print("**********************************************************\n");
		System.out.println("1. Ride Request");
		System.out.println("2. Pass Request");
		System.out.print("Choose menu item: ");
		String reqType;
		// handle user commands
		boolean quit = false;
		int menuItem;
		menuItem = in.nextInt();
		switch (menuItem) {
		case 1: {
			System.out.println("                        ");
			System.out.println("Welcome to Ride Service");
			System.out.println("-----------------------");
			req = new Request();
			reqType = "ride";
			Scanner scanner = new Scanner(System.in);
			String ridetype, pickuploc, dropoffloc, vehicletype, pickuptime;
			int noofseats;
			System.out.println("Enter PickUp Location");
			pickuploc = scanner.nextLine();
			System.out.println("Enter DropOff Location:");
			dropoffloc = scanner.nextLine();
			System.out.println("Enter Ride Type");
			System.out.println("a. Taxi");
			System.out.println("b. Ride Share");
			ridetype = scanner.nextLine();
			if (ridetype.equalsIgnoreCase("a")) {
				ridetype = "Taxi";
			} else {
				ridetype = "Ride Share";
			}
			System.out.println("Enter PickUp Time(HH:MM)");
			pickuptime = scanner.nextLine();
			System.out.println("Enter No of Passengers");
			noofseats = scanner.nextInt();
			System.out.println("Enter Vehicle Type");
			System.out.println("1. Sedan");
			System.out.println("2. SUV");
			System.out.println("3. Luxury");
			int veh = scanner.nextInt();

			if (veh == 1) {
				vehicletype = "Sedan";
			} else if (veh == 2) {
				vehicletype = "SUV";
			} else {
				vehicletype = "Luxury";
			}
			System.out
					.println("Your request got submitted, PLease wait for the response");
			req.receiveRequest(memberId, reqType, ridetype, pickuploc,
					dropoffloc, vehicletype, pickuptime, noofseats);

		}
			break;
		case 2: {
			System.out.println("                        ");
			System.out.println("Welcome to Pass Service");
			System.out.println("-----------------------");
			req = new Request();
			reqType = "pass";
			req = new Request();
			req.receiveRequest(memberId, reqType);
		}
			break;
		case 0:
			quit = true;
			break;
		default:
			System.out.println("Invalid choice.");
		}
	}

	public static void callDriverMenu(String memberId) throws SQLException {
		Scanner in = new Scanner(System.in);
		int drivermenuselection;
		System.out
				.print("**********************************************************\n");
		System.out
				.print("                    Available Services                      \n");
		System.out
				.print("**********************************************************\n");
		System.out.println("1. My Profile");
		//System.out.println("2. Vehicle details");
		System.out.print("Choose menu item: ");
		drivermenuselection = in.nextInt();
		getDriverDetails(memberId);

	}

	public static void getDriverDetails(String memberId) throws SQLException {
		Scanner in = new Scanner(System.in);
		MemberDAO memDAO = new MemberDAO();
		Member mem = memDAO.getMember(memberId);
		System.out
				.println("***********************************************************\n");
		System.out
				.println("                     View Profile                          \n");
		System.out
				.println("***********************************************************\n");
		System.out.println("Enter Name: " + mem.getMemberName());
		System.out.println("                                        \n");

		System.out.println("Enter contactno(999-999-9999):" + mem.getContact());
		System.out.println("                                        \n");

		System.out.println("Enter MemberID(EmailId):" + mem.getMemberid());
		System.out.println("                                        \n");

		System.out.println("Enter Password:" + mem.getPassword());
		System.out.println("                                        \n");

		System.out.println("Enter Address:" + mem.getAddress());
		System.out.println("                                        \n");

		System.out.println("Enter Payment Card No:" + mem.getPaymentDetails());
		System.out.println("                                        \n");

		System.out.println("Enter Expiry Date (YYYY-MM-DD):"
				+ mem.getExpirydate());
		System.out.println("                                        \n");

		System.out.println("Enter CVV:" + mem.getCvv());
		System.out.println("                                        \n");

		System.out.println("Enter License No:" + mem.getLicenseDetails());
		System.out.println("                                        \n");

		System.out.println("Enter SSN(Last 4 digits):" + mem.getSsn());
		System.out.println("                                        \n");

		System.out.println("Enter Ur Executive" + mem.getManager());
		System.out.println("                                        \n");

	}

	public static void callExecutiveMenu(String memberId) throws SQLException {
		Scanner in = new Scanner(System.in);
		int executivemenuselection;
		System.out
				.print("**********************************************************\n");
		System.out
				.print("                    Available Services                      \n");
		System.out
				.print("**********************************************************\n");
		//System.out.println("1. My Profile");
		System.out.println("1. My reportes");
		System.out.print("Choose menu item: ");
		executivemenuselection = in.nextInt();
		MemberDAO memberDao = new MemberDAO();
		Member member = memberDao.getMember(memberId);
		
		if(executivemenuselection == 1) {
			Executive executive = new Executive(member.getMemberName());
			MembersClient.m = executive;
			MembersClient.doClientTasks();
		}
	}

	static Request req;

	public static void main(String[] args) throws Exception {
		callhomescreen();

	}

	public static void callhomescreen() throws Exception {
		Scanner in = new Scanner(System.in);
		int homescreenselection;
		System.out
				.print("**********************************************************\n");
		System.out
				.print("                Welcome to Ride Services                     \n");
		System.out
				.print("**********************************************************\n");
		System.out.println("1. Login");
		System.out.println("2. SignUp");
		System.out.print("Choose menu item: ");
		homescreenselection = in.nextInt();
		if (homescreenselection == 1) {
			Member member = login();
		} else {
			SignUpForm sign = new SignUpForm();
			String memberId = sign.signUp();
			MemberDAO memberDao = new MemberDAO();
			Member member = memberDao.getMember(memberId);
			if (member != null) {
				System.out.println("role:" + member.getRole());
				if (member.getRole().equalsIgnoreCase("customer")) {
					callMenu(memberId);
				} else if (member.getRole().equalsIgnoreCase("driver")) {
					callDriverMenu(memberId);
				} else if (member.getRole().equalsIgnoreCase("executive")) {
					callExecutiveMenu(memberId);
				}

			}
		}
	}

	public static Member login() throws Exception {

		String memberId;
		String password;
		Scanner scanner = new Scanner(System.in);
		System.out.println("                ");
		System.out.println("Enter MemberID:");
		memberId = scanner.nextLine();
		System.out.println("Enter Password:");
		password = scanner.nextLine();
		MemberDAO memberDao = new MemberDAO();
		Member member = memberDao.getMember(memberId);
		if (member != null) {
			System.out.println("role:" + member.getRole());
			if (member.getRole().equalsIgnoreCase("customer")) {
				callMenu(memberId);
			} else if (member.getRole().equalsIgnoreCase("driver")) {
				callDriverMenu(memberId);
			} else if (member.getRole().equalsIgnoreCase("executive")) {
				callExecutiveMenu(memberId);
			}

		} else
			System.out.println("No Member with Id: " + memberId);

		return member;
	}

}