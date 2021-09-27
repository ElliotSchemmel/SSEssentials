package com.ss.utopia.view;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.AdminService;

public class EmployeeMenu {
	
	private AdminService admin = new AdminService();
	
	public void getMenuOne(Scanner sc) throws ClassNotFoundException, SQLException {
		
		Boolean hasLooped = false;
		
		while (true) {
			try {

				List<User> employees = admin.readUsersByRoleId(1);
				
				System.out.println("Enter your Username:"); 
				if (!hasLooped) {
					sc.nextLine();
				}
				String username = sc.nextLine(); 
				System.out.println("Enter your Password:"); 
				String password = sc.nextLine();
				
				Boolean foundEmployee = false;
				
				for (User e : employees) {
					if (username.equals(e.getUsername())) {
						if (password.equals(e.getPassword())) {
							this.getMenuTwo(sc);
							foundEmployee = true;
						}
					}
				}
				
				if (foundEmployee) break;

				else {
					System.out.println("No employee with given username and password found in database, try again");
					hasLooped = true;
					continue;
				}
			/*	System.out.println("1) View Flights You Manage");
				System.out.println("2) Quit to previous");
				
				switch(sc.nextInt()) {
					case 1:
						this.getMenuTwo(sc);
						continue;
					case 2: 
						break;
					default:
						System.out.println("Enter a number 1-2");
						continue;
				}
			*/	
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-2");
				sc.next();
				continue;
			}
		}
		return;
	}

	private void getMenuTwo(Scanner sc) throws ClassNotFoundException, SQLException {
		
		while (true) {
			
			// print out a list of flights using iata_id and city names from airport using route_id from flight
			
			try {
				List<Flight> flights = admin.readFlights();
				
				int count = 0;
				
				System.out.println("Select Flight to Manage");
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
				
				this.getMenuThree(sc, flights.get(selection - 1));
				
				break;
			}

			catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid integer");
				sc.next();
				continue;
			}
		}
	}

	private void getMenuThree(Scanner sc, Flight flight) throws ClassNotFoundException, SQLException {

		while (true) {
			try {
				System.out.println("1) View more details about the flight");
				System.out.println("2) Update the details of the flight");
				System.out.println("3) Add seats to flight");
				System.out.println("4) Quit to previous");
				
				switch(sc.nextInt()) {
					case 1:
						this.viewDetails(sc, flight);
						continue;
					case 2:
						this.updateDetails(sc, flight);
						continue;
					case 3:
						this.addSeats(sc, flight);
						continue;
					case 4: 
						break;
					default:
						System.out.println("Enter a number 1-4");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-4");
				sc.next();
				continue;
			}
			break;
		}
		return;
		
	}

	private void viewDetails(Scanner sc, Flight flight) throws ClassNotFoundException, SQLException {
		
		System.out.println("You have chosen to view the Flight with Flight Id: " + flight.getId());
		System.out.println(
				"Departure Airport: "  
				+ admin.readAirportsById(admin.readRoutesById(flight.getRoute_id()).get(0).getOrigin_id()).get(0).getCity() +
				", Arrival Airport: "
				+ admin.readAirportsById(admin.readRoutesById(flight.getRoute_id()).get(0).getDestination_id()).get(0).getCity() +
				", Available Seats: "
				+ (admin.readAirplaneTypesById(admin.readAirplanesById(flight.getAirplane_id()).get(0).getType_id()).get(0).getMax_capacity() - flight.getReserved_seats()) +
				" / " 
				+ admin.readAirplaneTypesById(admin.readAirplanesById(flight.getAirplane_id()).get(0).getType_id()).get(0).getMax_capacity()
				);
		System.out.println();
		
	}

	private void updateDetails(Scanner sc, Flight flight) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {
				System.out.println("You have chosen to update the Flight with Flight Id: " + flight.getId());
				System.out.println(
					"Departure Airport: "  
					+ admin.readAirportsById(admin.readRoutesById(flight.getRoute_id()).get(0).getOrigin_id()).get(0).getCity() +
					", Arrival Airport: "
					+ admin.readAirportsById(admin.readRoutesById(flight.getRoute_id()).get(0).getDestination_id()).get(0).getCity());
				
				sc.nextLine();
				
				System.out.println("Please enter new Origin Airport City or enter N/A for no change: ");
				String origin = sc.nextLine();
				if (!"N/A".equals(origin)) {
					// check routes for city
				}
				
				System.out.println("Please enter new Destination Airport City or enter N/A for no change: ");
				String destination = sc.nextLine();
				if (!"N/A".equals(destination)) {
					// check routes for city
				}
				
				// get route_id from combination or make new one if none exists and cities are valid and different

				System.out.println("Please enter new Departure Date in format 'yyyy-mm-dd' or enter N/A for no change: ");
				String date = sc.nextLine();
				String flightDate;
				if (!"N/A".equals(date)) {
					flightDate = date;
				}
				else { 
					flightDate = flight.getDeparture_time().toString().split(" ")[0];
				}
				
				System.out.println("Please enter new Departure time in format 'hh:mm:ss' or enter N/A for no change: ");
				String time = sc.nextLine();
				String flightTime;
				if (!"N/A".equals(time)) {
					flightTime = time;
				}
				else { 
					flightTime = flight.getDeparture_time().toString().split(" ")[1];
				}
				
				// concatenate date and time to add to flight.departure_time
				String dateTime = flightDate + " " + flightTime;
				
				flight.setDeparture_time(Timestamp.valueOf(dateTime));
				
				// update flight and return statement
				System.out.println(admin.updateFlight(flight));
				
				break;	
				
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid integer");
				sc.next();
				continue;
			}
		}
		return;
		
	}

	private void addSeats(Scanner sc, Flight flight) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {
				// check available seats, ask user to add up to that amount of seats, do it and return response
				Integer availableSeats = admin.readAirplaneTypesById(admin.readAirplanesById(flight.getAirplane_id()).get(0).getType_id()).get(0).getMax_capacity() - flight.getReserved_seats();
				
				System.out.println("There are currently " + availableSeats + " seats available on this flight " +
									"out of " + (flight.getReserved_seats() + availableSeats) + " total.");
				System.out.println("How many seats would you like to add?");
				
				Integer addSeats = sc.nextInt();
				
				if (addSeats < 0 || addSeats > availableSeats) {
					System.out.println("Invalid number of seats entered, flight can not be updated");
					continue;
				}
				
				flight.setReserved_seats(addSeats + flight.getReserved_seats());
				System.out.println(admin.updateFlight(flight));
				
				break;
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid integer");
				sc.next();
				continue;
			}
		}
		
	}

}
