package com.ss.utopia.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.AdminService;

public class TravelerMenu {
	
	private AdminService admin = new AdminService();
	
	public void getMenuOne(Scanner sc) throws ClassNotFoundException, SQLException {
				
		while (true) {
			try {
				
				System.out.println("Enter your User Id: "); // change to id of something
				int id = sc.nextInt(); // check that this is valid id else loop and try again
				
				List<User> users = admin.readUsersById(id);
				
				if (users.isEmpty()) {
					System.out.println("No user found with given Id, try again");
					continue;
				}
				
				this.getMenuTwo(sc, users.get(0)); // pass through User object
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid User Id");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}

	private void getMenuTwo(Scanner sc, User user) throws ClassNotFoundException, SQLException {

		while (true) {
			try {
				
				System.out.println("1) Book a Ticket");
				System.out.println("2) Cancel an Upcoming Trip");
				System.out.println("3) Quit to previous");
				
				switch(sc.nextInt()) {
				case 1:
					this.bookTicket(sc, user);
					continue;
				case 2:
					this.cancelTrip(sc, user);
					continue;
				case 3:
					break;
				default:
					System.out.println("Invalid input, please enter 1-3");
					continue;
				}
				

			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid User Id");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}

	private void bookTicket(Scanner sc, User user) throws ClassNotFoundException, SQLException {
	
		while (true) {
			try {
				
				List<Flight> flights = admin.readFlights();
				
				System.out.println("Pick the flight you want to book a ticket for: ");
				
				int count = 0;
				
				for (Flight f : flights) {
					System.out.println(
							++count + ") " +
							admin.readAirportsById(admin.readRoutesById(f.getRoute_id()).get(0).getOrigin_id()).get(0).getIata_id()
							+ ", " +
							admin.readAirportsById(admin.readRoutesById(f.getRoute_id()).get(0).getOrigin_id()).get(0).getCity()
							+ " -> " +
							admin.readAirportsById(admin.readRoutesById(f.getRoute_id()).get(0).getDestination_id()).get(0).getIata_id()
							+ ", " +
							admin.readAirportsById(admin.readRoutesById(f.getRoute_id()).get(0).getDestination_id()).get(0).getCity()
							);
				}
				System.out.println(++count + ") Quit to previous");
				
				int selection = sc.nextInt();
				
				if (selection == count) {
					break;
				}
				else if (selection < 1 || selection > count) {
					System.out.println("Enter one of the given integers");
					continue;
				}
				
				// book flight with flights.get(selection).getId()
				// add reserved seat and link user to the flight
				
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid User Id");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}

	private void cancelTrip(Scanner sc, User user) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {
				
				List<Flight> flights = admin.readFlights();
				
				System.out.println("Pick the flight you want to cancel a ticket for: ");
				
				// list flights that the user currently has booked to select from
				
				int count = 0;
				
				for (Flight f : flights) {
					System.out.println(
							++count + ") " +
							admin.readAirportsById(admin.readRoutesById(f.getRoute_id()).get(0).getOrigin_id()).get(0).getIata_id()
							+ ", " +
							admin.readAirportsById(admin.readRoutesById(f.getRoute_id()).get(0).getOrigin_id()).get(0).getCity()
							+ " -> " +
							admin.readAirportsById(admin.readRoutesById(f.getRoute_id()).get(0).getDestination_id()).get(0).getIata_id()
							+ ", " +
							admin.readAirportsById(admin.readRoutesById(f.getRoute_id()).get(0).getDestination_id()).get(0).getCity()
							);
				}
				System.out.println(++count + ") Quit to previous");
				
				int selection = sc.nextInt();
				
				if (selection == count) {
					break;
				}
				else if (selection < 1 || selection > count) {
					System.out.println("Enter one of the given integers");
					continue;
				}
				
				// book flight with flights.get(selection).getId()
				
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid User Id");
				sc.next();
				continue;
			}
			break;
		}
		return;	
	}

}
