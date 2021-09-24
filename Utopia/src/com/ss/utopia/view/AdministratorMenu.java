package com.ss.utopia.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.entity.Flight;
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
						this.overRide(sc);
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
					case 1:
						continue;

					case 2:
						continue;
					case 3:
						List<Flight> flights = admin.readFlights();
						for (Flight f : flights) {
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

					case 4:
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
		
	}

	private void ticketAndPassengerCRUD(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void airportCRUD(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void travelerCRUD(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void employeeCRUD(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void overRide(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
}
