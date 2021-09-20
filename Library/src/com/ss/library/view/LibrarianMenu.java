package com.ss.library.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.library.entity.Book;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.Branch;
import com.ss.library.service.AdminService;

public class LibrarianMenu {
	
	private AdminService admin = new AdminService();

	public void getMenuOne(Scanner sc) throws ClassNotFoundException, SQLException {

		while (true) {

			System.out.println("1) Enter Branch you manage");
			System.out.println("2) Quit to previous");

			try {
				switch(sc.nextInt()) {
					case 1:
						this.getMenuTwo(sc);
						continue;
					case 2:
						break;
					default:
						System.out.println("Incorrect input, please enter 1 or 2");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input, please enter 1 or 2");
				sc.next();
				continue;
			} 
			break;
		}
		return;
	}
	
	public void getMenuTwo(Scanner sc) throws ClassNotFoundException, SQLException {
		
		
		// print out list of Branches
		List<Branch> branches = admin.readLibraryBranches();
		int branchNumber = 0;
		int count = 0;

		for(Branch b : branches) {
			System.out.println(++count + ") " + b.getBranchName() + ", " + b.getBranchAddress());
		}
		System.out.println("Enter number of branch you manage");
		
		while (true) {
			try {
				branchNumber = sc.nextInt();
				if (branchNumber > 0 && branchNumber <= count) {
					break;
				}
				else {
					System.out.println("Incorrect input: Enter the number of the branch you manage");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Incorrect input: Enter the number of the branch you manage");
				sc.next();
				continue;
			}
		}

		while (true) {

			System.out.println("1) Update the details of the Library");
			System.out.println("2) Add copies of Book to the Branch");
			System.out.println("3) Quit to previous");

			try {
				switch(sc.nextInt()) {
					case 1:
						this.getMenuUpdateBranch(sc, branches.get(branchNumber - 1)); // list is 0 indexed
						continue;
					case 2:
						this.getMenuAddBookCopies(sc, branches.get(branchNumber - 1));
						continue;
					case 3:
						break;
					default:
						System.out.println("Incorrect input, please enter 1 or 2");
						continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input, please enter 1 or 2");
				sc.next();
				continue;
			} 
			break;
		}
		return;
		
	}
	
	public void getMenuUpdateBranch(Scanner sc, Branch branch) throws ClassNotFoundException, SQLException {

		System.out.println("You have chosen to update the Branch with Branch Id: " + branch.getBranchId() + 
							" and Banch Name: " + branch.getBranchName());

		int updateCount;

		while (true) {

			try {
				sc.nextLine();
				updateCount = 0;
				
				System.out.println("Please enter new branch name or enter N/A for no change: ");
				String inputName = sc.nextLine();
				if (!"N/A".equals(inputName)) {
					updateCount++;
					branch.setBranchName(inputName);
				}
				
				System.out.println("Please enter new branch address or enter N/A for no change: ");
				String inputAdd = sc.nextLine();
				if (!"N/A".equals(inputAdd)) {
					updateCount++;
					branch.setBranchAddress(inputAdd);
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Incorrect Input");
				sc.next();
				continue;
			}
			break;
		}
		
		// only update if changes are made to branch
		if (updateCount != 0) {
			System.out.println(admin.updateLibraryBranch(branch));
		}
		else {
			System.out.println("No changes made to branch " + branch.getBranchName());
		}

		return;
	}

	public void getMenuAddBookCopies(Scanner sc, Branch branch) throws ClassNotFoundException, SQLException {


			System.out.println("Pick the Book you want to add copies of, to your branch: ");
			List<Book> books = admin.readBooks();
			int count = 0;

			for(Book b : books) {
				System.out.println(++count + ") " + b.getTitle());
			}
			
			BookCopies copies = new BookCopies();
			
			while (true) {
				try {
					copies.setBookId(sc.nextInt()); 
					copies.setBranchId(branch.getBranchId());

					System.out.println("Existing number of copies: " + admin.readNoOfCopiesById(copies));

					System.out.println("Enter new number of copies: ");
					copies.setNoOfCopies(sc.nextInt());
					admin.updateBookCopies(copies);
					
				} catch (InputMismatchException e) {
					System.out.println("Incorrect input, enter positive integers for number of book or number of copies");
					sc.next();
					continue;
				}
				break;
			}
		return;
	}
}
