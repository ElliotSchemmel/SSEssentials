package com.ss.utopia.view;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.FlightBookings;
import com.ss.utopia.entity.Passenger;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;
import com.ss.utopia.service.AdminService;

public class AdministratorMenu {
	
	private AdminService admin = new AdminService();

	public void getMenuOne(Scanner sc) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {
				System.out.println("Choose Administrator function: ");
				System.out.println("1) Add/Update/Delete/Read Flights");
				System.out.println("2) Add/Update/Delete/Read Seats");
				System.out.println("3) Add/Update/Delete/Read Tickets and Passengers");
				System.out.println("4) Add/Update/Delete/Read Airports");
				System.out.println("5) Add/Update/Delete/Read Travelers");
				System.out.println("6) Add/Update/Delete/Read Employees");
				System.out.println("7) Over-ride Trip Cancellation for a ticket");
				System.out.println("8) Quit to previous");
				
				switch(sc.nextInt()) {
					case 1:
						this.flightCRUD(sc);
						continue;
					case 2:
						this.seatCRUD(sc);
						continue;
					case 3:
						this.ticketAndPassengerCRUD(sc);
						continue;
					case 4:
						this.airportCRUD(sc);
						continue;
					case 5:
						this.travelerCRUD(sc);
						continue;
					case 6: 
						this.employeeCRUD(sc);
						continue;
					case 7: 
						this.overRideTicketCancellation(sc);
						continue;
					case 8: 
						break;
					default:
						System.out.println("Enter a number 1-8");
						continue;
				}
				
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-8");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}

	private void flightCRUD(Scanner sc) throws ClassNotFoundException, SQLException {

		while (true) {
			try {
				
				System.out.println("Choose CRUD action:");
				System.out.println("1) Add flight");
				System.out.println("2) Update flight");
				System.out.println("3) Read flights");
				System.out.println("4) Delete flight");
				System.out.println("5) Quit to previous");
				
				switch(sc.nextInt()) {
				
					// add
					case 1:
						// ask user to give route, airplane, departure_time, reserved_seats, and seat_price
						// make sure reserved_seats is less than the max_capacity for the given airplane

						Flight addFlight = new Flight();
						
						// read all current flights and set new flight id to 1 above current max id
						List<Flight> flightsA = admin.readFlights();
						int maxId = 0;
						for (Flight f : flightsA) {
							if (f.getId() > maxId) {
								maxId = f.getId();
							}
						}
						addFlight.setId(maxId + 1);
						

						// choose departure and arrival airport
						List<Airport> airports = admin.readAirports();

						// choose departure airport
						int count = 0;
						System.out.println("Choose Departure Airport:");
						for (Airport a : airports) {
							System.out.println(++count + ") " + a.getIata_id() + ", " + a.getCity());
						}
						System.out.println(++count + ") Quit");
						
						int selection = sc.nextInt();
						if (selection == count) {
							continue;
						}
						else if (selection < 1 || selection > count) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}


						// choose destination airport
						int count2 = 0;
						System.out.println("Choose Destination Airport:");
						for (Airport a : airports) {
							System.out.println(++count2 + ") " + a.getIata_id() + ", " + a.getCity());
						}
						System.out.println(++count2 + ") Quit");
						
						int selection2 = sc.nextInt();
						if (selection2 == count2) {
							continue;
						}
						else if (selection2 < 1 || selection2 > count2) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// read all routes and add new route if given route is not present
						// use saveReturnPk here 
						
						List<Route> currentRoutes = admin.readRoutes();
						Integer routeId = 0;
						
						for (Route r : currentRoutes) {
							if (admin.readAirportsById(r.getOrigin_id()).get(0).getCity().equals(airports.get(selection - 1).getCity()) 
								&& admin.readAirportsById(r.getDestination_id()).get(0).getCity().equals(airports.get(selection2 - 1).getCity())) {
								routeId = r.getId();
							}
						}
						
						// if found route, use that route
						if (routeId > 0) {
							addFlight.setRoute_id(routeId);
						}
						else { // otherwise, add new route and use that
							Route addRoute = new Route();
							addRoute.setOrigin_id(airports.get(selection - 1).getIata_id());
							addRoute.setDestination_id(airports.get(selection2 - 1).getIata_id());
							
							routeId = admin.addRouteReturnPK(addRoute);
							
							// check if route added properly, break if not
							if (routeId == 0) {
								System.out.println("New route could not be added");
								continue;
							}

							// add route_id
							addFlight.setRoute_id(routeId);
						}
						
						
						// choose which airplane to add
						System.out.println("Choose airplane to add to flight:");
						List<Airplane> airplanes = admin.readAirplanes();
						int countA = 0;
						for (Airplane a : airplanes) {
							System.out.println(++countA + ") " +
												"Airplane Id: " + a.getId() + 
												" Max Capacity: " + 
												admin.readAirplaneTypesById(a.getType_id()).get(0).getMax_capacity()
									);
						}
						System.out.println(++countA + ") Quit");
						
						int selectionA = sc.nextInt();
						if (selectionA == countA) {
							continue;
						}
						else if (selectionA < 1 || selectionA > countA) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// add airplane_id to flight
						addFlight.setAirplane_id(airplanes.get(selectionA - 1).getId());
						
						List<String> dates = Arrays.asList("2021-09-30", "2021-10-01", "2021-10-02");
						List<String> times = Arrays.asList("06:30:00", "07:30:00", "10:00:00", "14:30:00", "16:00:00");
						
						// choose departure date from given list
						System.out.println("Choose Departure Date: ");
						int countDate = 0;
						for (String s : dates) {
							System.out.println(++countDate + ") " + s);
						}
						System.out.println(++countDate + ") Quit");
						
						int selectionDate = sc.nextInt();
						if (selectionDate == countDate) {
							continue;
						}
						else if (selectionDate < 1 || selectionDate > countDate) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// choose departure time from given list
						System.out.println("Choose Departure Time: ");
						int countTime = 0;
						for (String s : times) {
							System.out.println(++countTime + ") " + s);
						}
						System.out.println(++countTime + ") Quit");
						
						int selectionTime = sc.nextInt();
						if (selectionTime == countTime) {
							continue;
						}
						else if (selectionTime < 1 || selectionTime > countTime) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						String dateTime = dates.get(selectionDate - 1) + " " + times.get(selectionTime - 1);
						
						// add departure date and time
						addFlight.setDeparture_time(Timestamp.valueOf(dateTime));
						
						// choose reserved seats
						int maxCapacity = admin.readAirplaneTypesById(admin.readAirplanesById(
										  addFlight.getAirplane_id()).get(0).getType_id()).get(0).getMax_capacity();
						System.out.println("Enter number of reserved seats");
						System.out.println("Max Capacity of chosen airplane: " + maxCapacity);
						
						int reservedSeats = sc.nextInt();
						
						if (reservedSeats < 0 || reservedSeats > maxCapacity) {
							System.out.println("Invalid number of seats entered, please enter an integer between 0 and the max capacity of the chosen plane");
							continue;
						}
						
						// add reserved_seats to flight
						addFlight.setReserved_seats(reservedSeats);
						
						// choose seat_price
						System.out.println("Enter the price of seats for this flight: ");
						
						Float seatPrice = sc.nextFloat();
						
						// set the seat_price
						addFlight.setSeat_price(seatPrice);
						
						// add the flight to the database
						System.out.println(admin.addFlight(addFlight));
						
						continue;

					// update
					case 2:
						// ask user for update of all fields, check that all fields given are valid
						// make sure reserved_seats is less than the max_capacity for the given airplane
						
						// choose which flight to update
						System.out.println("Choose which flight to update:");
						List<Flight> flightsU = admin.readFlights();
						int countU = 0;
						for (Flight f : flightsU) {
							System.out.println(
									++countU + ") " +
									"Flight id: " + f.getId() +
									", Route id: " + f.getRoute_id() +
									", Airplane id: " + f.getAirplane_id() +
									", Departure time: " + f.getDeparture_time() +
									", Reserved seats: " + f.getReserved_seats() +
									", Seat price: $" + f.getSeat_price()
									);
						}
						System.out.println(++countU + ") Quit");
						
						int selectionU = sc.nextInt();
						if (selectionU == countU) {
							continue;
						}
						else if (selectionU < 1 || selectionU > countU) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						Flight updateFlight = flightsU.get(selectionU - 1);
						
						// choose departure and arrival airport
						List<Airport> airportsU = admin.readAirports();

						// choose departure airport
						int countUD = 0;
						System.out.println("Choose Departure Airport:");
						for (Airport a : airportsU) {
							System.out.println(++countUD + ") " + a.getIata_id() + ", " + a.getCity());
						}
						System.out.println(++countUD + ") Quit");
						
						int selectionUD = sc.nextInt();
						if (selectionUD == countUD) {
							continue;
						}
						else if (selectionUD < 1 || selectionUD > countUD) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}

						// choose destination airport
						int countUA = 0;
						System.out.println("Choose Destination Airport:");
						for (Airport a : airportsU) {
							System.out.println(++countUA + ") " + a.getIata_id() + ", " + a.getCity());
						}
						System.out.println(++countUA + ") Quit");
						
						int selectionUA = sc.nextInt();
						if (selectionUA == countUA) {
							continue;
						}
						else if (selectionUA < 1 || selectionUA > countUA) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// read all routes and add new route if given route is not present
						// use saveReturnPk here
						
						List<Route> currentRoutesU = admin.readRoutes();
						Integer routeIdU = 0;
						
						for (Route r : currentRoutesU) {
							if (admin.readAirportsById(r.getOrigin_id()).get(0).getCity().equals(airportsU.get(selectionUD - 1).getCity()) 
								&& admin.readAirportsById(r.getDestination_id()).get(0).getCity().equals(airportsU.get(selectionUA - 1).getCity())) {
								routeIdU = r.getId();
							}
						}
						
						// if found route, use that route
						if (routeIdU > 0) {
							updateFlight.setRoute_id(routeIdU);
						}
						else { // otherwise, add new route and use that
							Route addRoute = new Route();
							addRoute.setOrigin_id(airportsU.get(selectionUD - 1).getIata_id());
							addRoute.setDestination_id(airportsU.get(selectionUA - 1).getIata_id());
							
							routeIdU = admin.addRouteReturnPK(addRoute);
							
							// check if route added properly, break if not
							if (routeIdU == 0) {
								System.out.println("New route could not be added");
								continue;
							}

							// update route_id
							updateFlight.setRoute_id(routeIdU);
						}
						
						// choose which airplane to change to
						System.out.println("Choose new airplane for flight:");
						List<Airplane> airplanesU = admin.readAirplanes();
						int countAU = 0;
						for (Airplane a : airplanesU) {
							System.out.println(++countAU + ") " +
												"Airplane Id: " + a.getId() + 
												" Max Capacity: " + 
												admin.readAirplaneTypesById(a.getType_id()).get(0).getMax_capacity()
									);
						}
						System.out.println(++countAU + ") Quit");
						
						int selectionAU = sc.nextInt();
						if (selectionAU == countAU) {
							continue;
						}
						else if (selectionAU < 1 || selectionAU > countAU) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// update airplane_id to flight
						updateFlight.setAirplane_id(airplanesU.get(selectionAU - 1).getId());
						
						List<String> datesU = Arrays.asList("2021-09-30", "2021-10-01", "2021-10-02");
						List<String> timesU = Arrays.asList("06:30:00", "07:30:00", "10:00:00", "14:30:00", "16:00:00");
						
						// choose departure date from given list
						System.out.println("Choose Departure Date: ");
						int countDateU = 0;
						for (String s : datesU) {
							System.out.println(++countDateU + ") " + s);
						}
						System.out.println(++countDateU + ") Quit");
						
						int selectionDateU = sc.nextInt();
						if (selectionDateU == countDateU) {
							continue;
						}
						else if (selectionDateU < 1 || selectionDateU > countDateU) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// choose departure time from given list
						System.out.println("Choose Departure Time: ");
						int countTimeU = 0;
						for (String s : timesU) {
							System.out.println(++countTimeU + ") " + s);
						}
						System.out.println(++countTimeU + ") Quit");
						
						int selectionTimeU = sc.nextInt();
						if (selectionTimeU == countTimeU) {
							continue;
						}
						else if (selectionTimeU < 1 || selectionTimeU > countTimeU) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						String dateTimeU = datesU.get(selectionDateU - 1) + " " + timesU.get(selectionTimeU - 1);
						
						// update departure date and time
						updateFlight.setDeparture_time(Timestamp.valueOf(dateTimeU));
						
						// choose reserved seats
						int maxCapacityU = admin.readAirplaneTypesById(admin.readAirplanesById(
										  updateFlight.getAirplane_id()).get(0).getType_id()).get(0).getMax_capacity();
						System.out.println("Enter number of reserved seats");
						System.out.println("Max Capacity of chosen airplane: " + maxCapacityU);
						
						int reservedSeatsU = sc.nextInt();
						
						if (reservedSeatsU < 0 || reservedSeatsU > maxCapacityU) {
							System.out.println("Invalid number of seats entered, please enter an integer between 0 and the max capacity of the chosen plane");
							continue;
						}
						
						// add reserved_seats to flight
						updateFlight.setReserved_seats(reservedSeatsU);
						
						// choose seat_price
						System.out.println("Enter the price of seats for this flight: ");
						
						Float seatPriceU = sc.nextFloat();
						
						// set the seat_price
						updateFlight.setSeat_price(seatPriceU);
						
						// add the flight to the database
						System.out.println(admin.updateFlight(updateFlight));
						
						
						continue;
						
					// read
					case 3:
						List<Flight> flightsR = admin.readFlights();
						for (Flight f : flightsR) {
							System.out.println(
									"Flight id: " + f.getId() +
									", Route id: " + f.getRoute_id() +
									", Airplane id: " + f.getAirplane_id() +
									", Departure time: " + f.getDeparture_time() +
									", Reserved seats: " + f.getReserved_seats() +
									", Seat price: $" + f.getSeat_price()
									);
						}
						continue;

					// delete 
					case 4:
						// list all flights, ask user which to delete and then delete that flight
						System.out.println("Choose which flight to delete:");
						List<Flight> flightsD = admin.readFlights();
						int countD = 0;
						for (Flight f : flightsD) {
							System.out.println(
									++countD + ") " +
									"Flight id: " + f.getId() +
									", Route id: " + f.getRoute_id() +
									", Airplane id: " + f.getAirplane_id() +
									", Departure time: " + f.getDeparture_time() +
									", Reserved seats: " + f.getReserved_seats() +
									", Seat price: $" + f.getSeat_price()
									);
						}
						System.out.println(++countD + ") Quit");
						
						int selectionD = sc.nextInt();
						if (selectionD == countD) {
							continue;
						}
						else if (selectionD < 1 || selectionD > countD) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// delete chosen flight
						System.out.println(admin.deleteFlight(flightsD.get(selectionD - 1)));

						continue;

					case 5:
						break;
					default:
						System.out.println("Enter a number 1-5");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-5");
				sc.next();
				continue;
			}
			break;
		}
		return;
		
	}

	private void seatCRUD(Scanner sc) {
		// TODO Auto-generated method stub
		while (true) {
			try {
				System.out.println("Choose CRUD action: ");
				System.out.println("1) Add");
				System.out.println("2) Update");
				System.out.println("3) Read");
				System.out.println("4) Delete");
				System.out.println("5) Quit");

				switch(sc.nextInt()) {
				
					// add
					case 1:
						continue;
						
					// update
					case 2:
						continue;
						
					// read
					case 3:
						continue;
						
					//delete
					case 4:
						continue;
						
					//quit
					case 5:
						break;

					default:
						System.out.println("Invalid input, try again enter 1-5");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-5");
				sc.next();
				continue;
			}
			break;
		}
		return;	// once deleted, delete all associated routes
		
	}

	private void ticketAndPassengerCRUD(Scanner sc) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {
				System.out.println("Choose CRUD action: ");
				System.out.println("1) Add");
				System.out.println("2) Update");
				System.out.println("3) Read");
				System.out.println("4) Delete");
				System.out.println("5) Quit");

				switch(sc.nextInt()) {
				
					// add
					case 1:
						
						// add a passenger, get all details of passenger and ask them to book a flight
						// update booking, flight_bookings to correspond with flight
						
						
						Passenger addPassenger = new Passenger();
						
						System.out.println("Enter Given Name of Passenger:");
						sc.nextLine();
						addPassenger.setGiven_name(sc.nextLine());
						
						System.out.println("Enter Family Name of Passenger:");
						addPassenger.setFamily_name(sc.nextLine());

						System.out.println("Enter Date of Birth for Passenger in format yyyy-mm-dd:");
						addPassenger.setDob(Date.valueOf(sc.nextLine()));

						System.out.println("Enter Gender of Passenger:");
						addPassenger.setGender(sc.nextLine());

						System.out.println("Enter Address of Passenger:");
						addPassenger.setAddress(sc.nextLine());

						System.out.println("Choose a flight to book this passenger on:");
						
						List<Flight> flights = admin.readFlights();
						
						int count = 0;
						for (Flight f : flights) {
							System.out.println(
									++count + ") " +
									"Flight id: " + f.getId() +
									", Route id: " + f.getRoute_id() +
									", Airplane id: " + f.getAirplane_id() +
									", Departure time: " + f.getDeparture_time() +
									", Reserved seats: " + f.getReserved_seats() +
									", Seat price: $" + f.getSeat_price()
									);
						}
						System.out.println(++count + ") Quit");
						
						int selection = sc.nextInt();
						if (selection == count) {
							continue;
						}
						else if (selection < 1 || selection > count) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// create new booking, use that pk to add to flight_bookings and passenger
						
						// create new booking
						Booking booking = new Booking();
						booking.setIs_active(1);
						booking.setConfirmation_code("Confirmed");
						
						Integer pk = admin.addBookingReturnPK(booking);
						
						// create new flight_bookings with booking id and flight id
						FlightBookings flightBooking = new FlightBookings();
						flightBooking.setFlight_id(flights.get(selection - 1).getId());
						flightBooking.setBooking_id(pk);
						
						// set the pk from booking as the passenger.booking_id
						addPassenger.setBooking_id(pk);
						
						// add entries to tables for booking, flight_bookings, and passenger
						System.out.println(admin.addBooking(booking));
						System.out.println(admin.addFlightBookings(flightBooking));
						System.out.println(admin.addPassenger(addPassenger));
						
						continue;
						
					// update
					case 2:
						continue;
						
					// read
					case 3:

						List<Passenger> passengerR = admin.readPassengers();
						for (Passenger p : passengerR) {
							System.out.println(
									"Passenger id: " + p.getId() +
									", Booking id: " + p.getBooking_id() +
									", Given Name: " + p.getGiven_name() +
									", Family Name: " + p.getFamily_name() +
									", DOB: " + p.getDob() +
									", Gender: " + p.getGender() +
									", Address: " + p.getAddress() 
									);
						}
						continue;
						
					//delete
					case 4:
						
						System.out.println("Choose which passenger to delete:");
						int countD = 0;
						List<Passenger> passengersD = admin.readPassengers();
						for (Passenger p : passengersD) {
							System.out.println(
									++countD + ") " +
									"Passenger id: " + p.getId() +
									", Booking id: " + p.getBooking_id() +
									", Given Name: " + p.getGiven_name() +
									", Family Name: " + p.getFamily_name() +
									", DOB: " + p.getDob() +
									", Gender: " + p.getGender() +
									", Address: " + p.getAddress() 
									);
						}
						System.out.println(++countD + ") Quit");
						
						int selectionD = sc.nextInt();
						if (selectionD == countD) {
							continue;
						}
						else if (selectionD < 1 || selectionD > countD) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// for deleted passenger, go into flight and remove their seat from reserved_seats

						// delete chosen passenger
						System.out.println(admin.deletePassenger(passengersD.get(selectionD - 1)));
						
						continue;
						
					//quit
					case 5:
						break;

					default:
						System.out.println("Invalid input, try again enter 1-5");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-5");
				sc.next();
				continue;
			}
			break;
		}
		return;	
		
	}

	private void airportCRUD(Scanner sc) throws SQLException, ClassNotFoundException {
		// only allow airports to be deleted if there are no current flights using it
		while (true) {
			try {
				System.out.println("Choose CRUD action: ");
				System.out.println("1) Add");
				System.out.println("2) Update");
				System.out.println("3) Read");
				System.out.println("4) Delete");
				System.out.println("5) Quit");

				switch(sc.nextInt()) {
				
					// add
					case 1:
						
						List<Airport> airports = admin.readAirports();
						
						Airport addAirport = new Airport();
						
						System.out.println("Enter unique Iada_id of new Airport in format of 3 uppercase letters:");
						sc.nextLine();
						String iata_id = sc.nextLine();
						
						// check if iata_id is in correct format and unique
						if (iata_id.matches("[A-Z]{3}")) {
							// check db for other matches
							for (Airport a : airports) {
								if (iata_id.equals(a.getIata_id())) {
									System.out.println("Iata_Id " + iata_id + " already exists, try again");
									continue;
								}
							}
							
						}
						else {
							System.out.println("Invalid form for Airport Iata_id, try again");
							continue;
						}
						
						addAirport.setIata_id(iata_id);
						
						System.out.println("Enter name of city the airport is located in:");
						
						String city = sc.nextLine();
						
						addAirport.setCity(city);
						
						System.out.println(admin.addAirport(addAirport));
						
						continue;
						
					// update
					case 2:

						List<Airport> airportsU = admin.readAirports();
					
						int countU = 0;
						System.out.println("Choose which airport to update:");
						for (Airport a : airportsU) {
							System.out.println(++countU + ") " + a.getIata_id() + ", " + a.getCity());
						}
						System.out.println(++countU + ") Quit");
						
						int selectionU = sc.nextInt();
						if (selectionU == countU) {
							continue;
						}
						else if (selectionU < 1 || selectionU > countU) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						Airport updateAirport = airportsU.get(selectionU - 1);
						
						System.out.println("Enter new city name for the airport or N/A for no change:");
						sc.nextLine();
						String cityU = sc.nextLine();
						
						if (!"N/A".equals(cityU)) {
							updateAirport.setCity(cityU);
						}
						
						System.out.println(admin.updateAirport(updateAirport));
						
						continue;
						
					// read
					case 3:

						List<Airport> airportsR = admin.readAirports();
					
						System.out.println("List of all airports");
						for (Airport a : airportsR) {
							System.out.println(a.getIata_id() + ", " + a.getCity());
						}
						continue;
						
					//delete
					case 4:
						List<Airport> airportsD = admin.readAirports();
					
						int countD = 0;
						System.out.println("Choose which airport to delete:");
						for (Airport a : airportsD) {
							System.out.println(++countD + ") " + a.getIata_id() + ", " + a.getCity());
						}
						System.out.println(++countD + ") Quit");
						
						int selectionD = sc.nextInt();
						if (selectionD == countD) {
							continue;
						}
						else if (selectionD < 1 || selectionD > countD) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						Airport deleteAirport = airportsD.get(selectionD - 1);
						
						// check for flights currently using routes with this airport
						// if any routes are active, notify user and do not delete airport
						
						List<Flight> flights = admin.readFlights();
						
						for (Flight f : flights) {
							if(admin.readRoutesById(f.getRoute_id()).get(0).getOrigin_id().equals(deleteAirport.getIata_id())
								|| admin.readRoutesById(f.getRoute_id()).get(0).getDestination_id().equals(deleteAirport.getIata_id())) {
								System.out.println("Airport is currently being used on a route of a current flight and can not be deleted");
								continue;
							}
						}
						
						System.out.println(admin.deleteAirport(deleteAirport));
						
						continue;
						
					//quit
					case 5:
						break;

					default:
						System.out.println("Invalid input, try again enter 1-5");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-5");
				sc.next();
				continue;
			}
			break;
		}
		return;	
		
	}

	private void travelerCRUD(Scanner sc) throws ClassNotFoundException, SQLException {

		while (true) {
			try {
				System.out.println("Choose CRUD action: ");
				System.out.println("1) Add");
				System.out.println("2) Update");
				System.out.println("3) Read");
				System.out.println("4) Delete");
				System.out.println("5) Quit");

				switch(sc.nextInt()) {
				
					// add
					case 1:
						
						User addUser = new User();
						addUser.setRole_id(2); // user is a traveler
						
						System.out.println("Enter Given Name of Traveler:");
						sc.nextLine();
						addUser.setGiven_name(sc.nextLine());
						
						System.out.println("Enter Family Name of Traveler:");
						addUser.setFamily_name(sc.nextLine());

						System.out.println("Enter Username of Traveler:");
						addUser.setUsername(sc.nextLine());

						System.out.println("Enter Email of Traveler:");
						addUser.setEmail(sc.nextLine());

						System.out.println("Enter Password of Traveler:");
						addUser.setPassword(sc.nextLine());

						System.out.println("Enter Phone Number of Traveler:");
						addUser.setPhone(sc.nextLine());

						System.out.println(admin.addUser(addUser));
						
						continue;
						
					// update
					case 2:
						
						List<User> usersU = admin.readUsersByRoleId(2);
						
						System.out.println("Select which traveler your would like to update:");
						int countU = 0;
						for (User u : usersU) {
							System.out.println(++countU + ") " +
									" Traveler Id: " + u.getId() +
									", Role Id: " + u.getRole_id() +
									", Given Name: " + u.getGiven_name() +
									", Family Name: " + u.getFamily_name() +
									", Username: " + u.getUsername() + 
									", Email: " + u.getEmail() + 
									", Password: " + u.getPassword() +
									", Phone: " + u.getPhone()
									);
						}
						System.out.println(++countU + ") Quit");
						
						int selectionU = sc.nextInt();
						if (selectionU == countU) {
							continue;
						}
						else if (selectionU < 1 || selectionU > countU) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						User updateUser = usersU.get(selectionU - 1);
						
						System.out.println("Enter new Given Name of Traveler or N/A for no change:");
						sc.nextLine();
						String givenName = sc.nextLine();
						if (!"N/A".equals(givenName)) {
							updateUser.setGiven_name(givenName);
						}
						
						System.out.println("Enter new Family Name of Traveler or N/A for no change:");
						String familyName = sc.nextLine();
						if (!"N/A".equals(familyName)) {
							updateUser.setFamily_name(familyName);
						}

						System.out.println("Enter new Username of Traveler or N/A for no change:");
						String username = sc.nextLine();
						if (!"N/A".equals(username)) {
							updateUser.setUsername(username);
						}

						System.out.println("Enter new Email of Traveler or N/A for no change:");
						String email = sc.nextLine();
						if (!"N/A".equals(email)) {
							updateUser.setEmail(email);
						}

						System.out.println("Enter new Password of Traveler or N/A for no change:");
						String password = sc.nextLine();
						if (!"N/A".equals(password)) {
							updateUser.setPassword(password);
						}

						System.out.println("Enter new Phone Number of Traveler or N/A for no change:");
						String phone = sc.nextLine();
						if (!"N/A".equals(phone)) {
							updateUser.setPhone(phone);
						}
						
						System.out.println(admin.updateUser(updateUser));

						continue;
						
					// read
					case 3:
						
						List<User> usersR = admin.readUsersByRoleId(2); // reads all users that are Travelers
						
						for (User u : usersR) {
							System.out.println("Traveler Id: " + u.getId() +
									", Role Id: " + u.getRole_id() +
									", Given Name: " + u.getGiven_name() +
									", Family Name: " + u.getFamily_name() +
									", Username: " + u.getUsername() + 
									", Email: " + u.getEmail() + 
									", Password: " + u.getPassword() +
									", Phone: " + u.getPhone()
									);
						}
						
						continue;
						
					//delete
					case 4:

						List<User> usersD = admin.readUsersByRoleId(2); // reads only travelers
						
						System.out.println("Select which traveler your would like to update:");
						int countD = 0;
						for (User u : usersD) {
							System.out.println(++countD + ") " +
									" Traveler Id: " + u.getId() +
									", Role Id: " + u.getRole_id() +
									", Given Name: " + u.getGiven_name() +
									", Family Name: " + u.getFamily_name() +
									", Username: " + u.getUsername() + 
									", Email: " + u.getEmail() + 
									", Password: " + u.getPassword() +
									", Phone: " + u.getPhone()
									);
						}
						System.out.println(++countD + ") Quit");
						
						int selectionD = sc.nextInt();
						if (selectionD == countD) {
							continue;
						}
						else if (selectionD < 1 || selectionD > countD) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// should check bookings for flights that the user was on and remove their seat and booking
						
						System.out.println(admin.deleteUser(usersD.get(selectionD - 1)));
						
						continue;
						
					//quit
					case 5:
						break;

					default:
						System.out.println("Invalid input, try again enter 1-5");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-5");
				sc.next();
				continue;
			}
			break;
		}
		return;	
		
	}

	private void employeeCRUD(Scanner sc) throws ClassNotFoundException, SQLException {

		while (true) {
			try {
				System.out.println("Choose CRUD action: ");
				System.out.println("1) Add");
				System.out.println("2) Update");
				System.out.println("3) Read");
				System.out.println("4) Delete");
				System.out.println("5) Quit");

				switch(sc.nextInt()) {
				
					// add
					case 1:
						
						User addUser = new User();
						addUser.setRole_id(1); // user is an employee
						
						System.out.println("Enter Given Name of Employee:");
						sc.nextLine();
						addUser.setGiven_name(sc.nextLine());
						
						System.out.println("Enter Family Name of Employee:");
						addUser.setFamily_name(sc.nextLine());

						System.out.println("Enter Username of Employee:");
						addUser.setUsername(sc.nextLine());

						System.out.println("Enter Email of Employee:");
						addUser.setEmail(sc.nextLine());

						System.out.println("Enter Password of Employee:");
						addUser.setPassword(sc.nextLine());

						System.out.println("Enter Phone Number of Employee:");
						addUser.setPhone(sc.nextLine());

						System.out.println(admin.addUser(addUser));
						
						continue;
						
					// update
					case 2:
						
						List<User> usersU = admin.readUsersByRoleId(1); // only search for employees
						
						System.out.println("Select which employee your would like to update:");
						int countU = 0;
						for (User u : usersU) {
							System.out.println(++countU + ") " +
									" Employee Id: " + u.getId() +
									", Role Id: " + u.getRole_id() +
									", Given Name: " + u.getGiven_name() +
									", Family Name: " + u.getFamily_name() +
									", Username: " + u.getUsername() + 
									", Email: " + u.getEmail() + 
									", Password: " + u.getPassword() +
									", Phone: " + u.getPhone()
									);
						}
						System.out.println(++countU + ") Quit");
						
						int selectionU = sc.nextInt();
						if (selectionU == countU) {
							continue;
						}
						else if (selectionU < 1 || selectionU > countU) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						User updateUser = usersU.get(selectionU - 1);
						
						System.out.println("Enter new Given Name of Employee or N/A for no change:");
						sc.nextLine();
						String givenName = sc.nextLine();
						if (!"N/A".equals(givenName)) {
							updateUser.setGiven_name(givenName);
						}
						
						System.out.println("Enter new Family Name of Employee or N/A for no change:");
						String familyName = sc.nextLine();
						if (!"N/A".equals(familyName)) {
							updateUser.setFamily_name(familyName);
						}

						System.out.println("Enter new Username of Employee or N/A for no change:");
						String username = sc.nextLine();
						if (!"N/A".equals(username)) {
							updateUser.setUsername(username);
						}

						System.out.println("Enter new Email of Employee or N/A for no change:");
						String email = sc.nextLine();
						if (!"N/A".equals(email)) {
							updateUser.setEmail(email);
						}

						System.out.println("Enter new Password of Employee or N/A for no change:");
						String password = sc.nextLine();
						if (!"N/A".equals(password)) {
							updateUser.setPassword(password);
						}

						System.out.println("Enter new Phone Number of Employee or N/A for no change:");
						String phone = sc.nextLine();
						if (!"N/A".equals(phone)) {
							updateUser.setPhone(phone);
						}
						
						System.out.println(admin.updateUser(updateUser));

						continue;
						
					// read
					case 3:
						
						List<User> usersR = admin.readUsersByRoleId(1); // reads all users that are employees
						
						for (User u : usersR) {
							System.out.println("Employee Id: " + u.getId() +
									", Role Id: " + u.getRole_id() +
									", Given Name: " + u.getGiven_name() +
									", Family Name: " + u.getFamily_name() +
									", Username: " + u.getUsername() + 
									", Email: " + u.getEmail() + 
									", Password: " + u.getPassword() +
									", Phone: " + u.getPhone()
									);
						}
						
						continue;
						
					//delete
					case 4:

						List<User> usersD = admin.readUsersByRoleId(1); // reads only employees
						
						System.out.println("Select which Employee your would like to update:");
						int countD = 0;
						for (User u : usersD) {
							System.out.println(++countD + ") " +
									" Employee Id: " + u.getId() +
									", Role Id: " + u.getRole_id() +
									", Given Name: " + u.getGiven_name() +
									", Family Name: " + u.getFamily_name() +
									", Username: " + u.getUsername() + 
									", Email: " + u.getEmail() + 
									", Password: " + u.getPassword() +
									", Phone: " + u.getPhone()
									);
						}
						System.out.println(++countD + ") Quit");
						
						int selectionD = sc.nextInt();
						if (selectionD == countD) {
							continue;
						}
						else if (selectionD < 1 || selectionD > countD) {
							System.out.println("Invalid number chosen, try again");
							continue;
						}
						
						// should check bookings for flights that the user was on and remove their seat and booking
						
						System.out.println(admin.deleteUser(usersD.get(selectionD - 1)));
						
						continue;
						
					//quit
					case 5:
						break;

					default:
						System.out.println("Invalid input, try again enter 1-5");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-5");
				sc.next();
				continue;
			}
			break;
		}
		return;	
		
	}

	private void overRideTicketCancellation(Scanner sc) {
		
		// from the cancel trip function in the Traveler menu, 
		// get a list of cancelled trips and ask the user which one 
		// they would like to override and re-book that ticket
		
	}
}
