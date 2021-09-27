package com.ss.utopia.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.BookingUser;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBookings;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.AdminService;

public class TravelerMenu {
	
	private AdminService admin = new AdminService();
	
	public void getMenuOne(Scanner sc) throws ClassNotFoundException, SQLException {
		
		Boolean hasLooped = false;
				
		while (true) {
			try {
				
				List<User> travelers = admin.readUsersByRoleId(2);
				
				System.out.println("Enter your Username:"); 
				if (!hasLooped) {
					sc.nextLine();
				}
				String username = sc.nextLine(); 
				System.out.println("Enter your Password:"); 
				String password = sc.nextLine();
				
				Boolean foundTraveler = false;
				
				for (User t : travelers) {
					if (username.equals(t.getUsername())) {
						if (password.equals(t.getPassword())) {
							this.getMenuTwo(sc,  t);
							foundTraveler = true;
						}
					}
				}
				
				if (foundTraveler) break;

				else {
					System.out.println("No traveler with given username and password found in database, try again");
					hasLooped = true;
					continue;
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter valid username and password");
				sc.next();
				continue;
			}
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
				
				// check available seats on flight
				Integer availableSeats = admin.readAirplaneTypesById(admin.readAirplanesById(flights.get(selection - 1)
						.getAirplane_id()).get(0).getType_id()).get(0).getMax_capacity()
						- flights.get(selection - 1).getReserved_seats();
				
				if (availableSeats < 1) {
					System.out.println("No seats left on chosen flight, pick another flight or quit to previous");
					continue;
				}
				
				System.out.println("There are " + availableSeats + " available seats on chosen flight");
				
				// create new booking, link to user with booking_user
				Booking booking = new Booking();
				booking.setIs_active(1);
				booking.setConfirmation_code("traveler");
				
				Integer booking_id = admin.addBookingReturnPK(booking);
				
				BookingUser bookingUser = new BookingUser();
				bookingUser.setBooking_id(booking_id);
				bookingUser.setUser_id(user.getId());
				
				// link booking to flight with flight_bookings
				FlightBookings flightBookings = new FlightBookings();
				flightBookings.setBooking_id(booking_id);
				flightBookings.setFlight_id(flights.get(selection - 1).getId());
				

				// add booking_user and flight_bookings
				System.out.println(admin.addFlightBookings(flightBookings));
				System.out.println(admin.addBookingUser(bookingUser));
				
				// add reserved seat to flight
				Integer seats = flights.get(selection - 1).getReserved_seats();
				flights.get(selection - 1).setReserved_seats(seats + 1);
				
				System.out.println(admin.updateFlight(flights.get(selection - 1)));

				break;
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid User Id");
				sc.next();
				continue;
			}
		}
		return;
	}

	private void cancelTrip(Scanner sc, User user) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {
				
				List<BookingUser> bookingUsers = admin.readBookingUsersByUserId(user.getId());
				List<Booking> bookings = new ArrayList<Booking>();
				
				for (BookingUser bu : bookingUsers) {
					bookings.add(admin.readBookingsById(bu.getBooking_id()).get(0));
				}
				
				List<Booking> activeBookings = bookings.stream().filter(e -> (e.getIs_active() == 1)).collect(Collectors.toList());
				
				List<Flight> bookedFlights = new ArrayList<Flight>();
				
				for (Booking ab : activeBookings) {
					bookedFlights.add(admin.readFlightsById(admin.readFlightBookingsByBookingId(
							ab.getId()).get(0).getFlight_id()).get(0));
				}
				
				System.out.println("Pick one of your booked flights that you want to cancel a ticket for: ");
				
				int count = 0;
				for (Flight f : bookedFlights) {
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
				
				// remove seat from chosen flight
				Integer seats = bookedFlights.get(selection - 1).getReserved_seats();
				bookedFlights.get(selection - 1).setReserved_seats(seats - 1);
				
				// set booking to inactive
				activeBookings.get(selection - 1).setIs_active(0);
				
				System.out.println(admin.updateFlight(bookedFlights.get(selection - 1)));
				System.out.println(admin.updateBooking(activeBookings.get(selection - 1)));
				
				break;
				
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter a valid User Id");
				sc.next();
				continue;
			}
		}
		return;	
	}

}
