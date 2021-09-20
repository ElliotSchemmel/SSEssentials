package com.ss.library.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.library.entity.Book;
import com.ss.library.service.AdminService;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		LibrarianMenu libMenu = new LibrarianMenu();
		BorrowerMenu borMenu = new BorrowerMenu();
		AdminMenu adminMenu = new AdminMenu();
		Scanner sc = new Scanner(System.in);
		
		
		while (true) {
			System.out.println("Welcome to the SS Library Management System." +
					" Which catagory of a user are you?");
			System.out.println("1) Librarian");
			System.out.println("2) Administrator");
			System.out.println("3) Borrower");
			System.out.println("4) Quit");

			try {
				switch(sc.nextInt()) {
					case 1:
						libMenu.getMenuOne(sc);
						continue;
					case 2:
						adminMenu.getMenuOne(sc);
						continue;
					case 3: 
						borMenu.getMenuOne(sc);
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
