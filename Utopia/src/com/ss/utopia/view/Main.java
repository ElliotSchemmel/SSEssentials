package com.ss.utopia.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		EmployeeMenu empMenu = new EmployeeMenu();
		AdministratorMenu adminMenu = new AdministratorMenu();
		TravelerMenu travMenu = new TravelerMenu();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("Welcome to the Utopia Airlines Management System.");
			System.out.println("Which category of a user are you?");
			System.out.println("1) Employee / Agent");
			System.out.println("2) Administrator");
			System.out.println("3) Traveler");
			System.out.println("4) Quit");

			try {
				switch(sc.nextInt()) {
					case 1:
						empMenu.getMenuOne(sc);
						continue;
					case 2:
						adminMenu.getMenuOne(sc);
						continue;
					case 3: 
						travMenu.getMenuOne(sc);
						continue;
					case 4:
						break;
					default:
						System.out.println("Incorrect input, please enter 1, 2, 3, or 4");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input, please enter 1, 2, 3, or 4");
				sc.next();
				continue;
			}
			break;
		}
		sc.close();
	}
}
