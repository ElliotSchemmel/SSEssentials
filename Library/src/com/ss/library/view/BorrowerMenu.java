package com.ss.library.view;

import java.sql.Date;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.ss.library.entity.Book;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.BookLoans;
import com.ss.library.entity.Borrower;
import com.ss.library.entity.Branch;
import com.ss.library.service.AdminService;

public class BorrowerMenu {

	private AdminService admin = new AdminService();
	
	public void getMenuOne(Scanner sc) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {

				// Ask for card number and check database to confirm it it valid
				System.out.println("Enter your Card Number: ");
				Integer cardNumber = sc.nextInt();
				
				List<Borrower> borrowers = admin.readAllBorrowers();
				boolean foundCardNumber = false;
				
				for(Borrower b : borrowers) {
					if (b.getCardNo().equals(cardNumber)) {
						foundCardNumber = true;
						break;
					}
				}
				
				if (foundCardNumber) { // continue to menu two
					getMenuTwo(sc, cardNumber);
				}
				else { // loop again and ask for valid card number
					System.out.println("The card number you entered did not match one on file");
					continue;
				}
				

			} catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter a valid card number");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}

	public void getMenuTwo(Scanner sc, Integer cardNumber) throws ClassNotFoundException, SQLException {

		while (true) {

			System.out.println("1) Check out a book");
			System.out.println("2) Return a book");
			System.out.println("3) Quit to Previous");

			try {
				switch(sc.nextInt()) {
					case 1:
						this.getMenuCheckOut(sc, cardNumber);
						continue;
					case 2:
						this.getMenuReturn(sc, cardNumber);
						continue;
					case 3:
						break;
					default:
						System.out.println("Incorrect input, please enter 1, 2, or 3");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input, please enter 1, 2, or 3");
				sc.next();
				continue;
			} 
			break;
		}
		return;
	}

	public void getMenuCheckOut(Scanner sc, Integer cardNumber) throws ClassNotFoundException, SQLException {
		// List branches to check out book from
		// Show books from selected branch that have >= 1 noOfCopies
		// Add entry into book_loans
		// 	set date out to today's date
		//  set due date to one week from today's date
		
		while (true) {
			
			try {
						
				// print out list of Branches
				List<Branch> branches = admin.readLibraryBranches();

				List<BookCopies> copiesAtBranch = new ArrayList<BookCopies>();
				
				int branchNumber = 0;
				int count = 0;

				System.out.println("Pick the Branch you want to check out from: ");
				
				for(Branch b : branches) {
					System.out.println(++count + ") " + b.getBranchName() + ", " + b.getBranchAddress());
				}
				System.out.println(++count + ") Quit to previous");

				branchNumber = sc.nextInt();
				if (branchNumber == count) break;
				else if (branchNumber > count || branchNumber < 1) {
					System.out.println("Select one of the given integers");
					continue;
				}
		
				while (true) {
					try {
						// get copies from given branch that have available noOfCopies > 0
						copiesAtBranch = admin.readBookCopiesFromBranchId(branchNumber).stream()
								.filter(e -> e.getNoOfCopies() > 0).collect(Collectors.toList());
						
						// get titles of book for each bookId in copiesAtBranch
						List<Book> books = new ArrayList<Book>();
						copiesAtBranch.forEach(copy -> {
								try {
									books.add(admin.readBooksById(copy.getBookId()).get(0));
								} catch (ClassNotFoundException | SQLException e) {
									e.printStackTrace();
								}
						});
						
						// print titles of books and ask user to choose one
						int count2 = 0;

						System.out.println("Select number of book you wish to check out");
						for(Book b : books) {
							System.out.println(++count2 + ") " + b.getTitle());
						}
						System.out.println(++count2 + ") Quit to previous");
						
						int choice = sc.nextInt();

						if(choice == count2) {
							break;
						}
						else if (choice > count2 || choice < 1) {
							System.out.println("Select one of the given integers");
							continue;
						}
						
						
						// select one of the titles offered and check out that book
						
						// add entry into book loans 
						// dateOut = today
						// dateDue = one week from today
						Borrower borrower = admin.readBorrowersById(cardNumber).get(0);
						BookLoans bookLoan = new BookLoans();
						bookLoan.setBookId(books.get(choice - 1).getBookId());
						bookLoan.setBranchId(branchNumber);
						bookLoan.setCardNo(cardNumber);
						
						Date today = Date.valueOf(LocalDate.now());
						bookLoan.setDateOut(today);
						
						DayOfWeek day = LocalDate.now().getDayOfWeek();
						Date nextWeek = Date.valueOf(LocalDate.now().with(TemporalAdjusters.next(day)));
						bookLoan.setDateDue(nextWeek);
						bookLoan.setDateIn(null);
						
						// can not re check out book bc BookLoan will already exist
						admin.addBookLoan(bookLoan);
						
						// update noOfCopies from that branch
						BookCopies bookCopies = new BookCopies();
						bookCopies.setBookId(books.get(choice - 1).getBookId());
						bookCopies.setBranchId(branchNumber);
						bookCopies.setNoOfCopies(admin.readNoOfCopiesById(bookCopies) - 1);
						System.out.println(admin.updateBookCopies(bookCopies));
					
					} catch (InputMismatchException e) {
						System.out.println("Incorrect input, please enter a valid integer");
						sc.next();
						continue;
					}
				} 
			} catch(InputMismatchException e) {
				System.out.println("Incorrect input, please enter a valid integer");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}

	public void getMenuReturn(Scanner sc, Integer cardNumber) throws ClassNotFoundException, SQLException {
		// Read BookLoans and ask user which book that they have checked out they would like to return
		// Add dateIn as today  into BookLoans 
		
		while (true) {
			
			try {
				
				List<BookLoans> bookLoans = admin.readBookLoansById(cardNumber);
				int count = 0;
				
				// print out list of book titles currently checked out by user
				System.out.println("Select which book you would like to return");
				for(BookLoans bl : bookLoans) {
					System.out.println(++count + ") " + admin.readBooksById(bl.getBookId()).get(0).getTitle());
				}
				System.out.println(++count + ") Quit to previous");
				
				// get input and verify input
				int choice = sc.nextInt();
				if (choice == count) {
					break;
				}
				else if (choice < 1 || choice > count) {
					System.out.println("Please enter one of the integers given");
					continue;
				}
				
				// return chosen book
				Date today = Date.valueOf(LocalDate.now());
				bookLoans.get(choice - 1).setDateIn(today);
				System.out.println(admin.updateBookLoans(bookLoans.get(choice - 1)));
				
				// update noOfCopies from that branch
				BookCopies bookCopies = new BookCopies();
				bookCopies.setBookId(bookLoans.get(choice - 1).getBookId());
				bookCopies.setBranchId(bookLoans.get(choice - 1).getBranchId());
				bookCopies.setNoOfCopies(admin.readNoOfCopiesById(bookCopies) + 1);
				System.out.println(admin.updateBookCopies(bookCopies));
						
			} catch(InputMismatchException e) {
				System.out.println("Incorrect input, please enter a valid integer");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}
}
