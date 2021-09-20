package com.ss.library.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.library.entity.Author;
import com.ss.library.entity.Book;
import com.ss.library.entity.BookAuthors;
import com.ss.library.entity.BookLoans;
import com.ss.library.entity.Borrower;
import com.ss.library.entity.Branch;
import com.ss.library.entity.Genre;
import com.ss.library.entity.BookGenres;
import com.ss.library.entity.Publisher;
import com.ss.library.service.AdminService;

public class AdminMenu {
	
	private AdminService admin = new AdminService();

	public void getMenuOne(Scanner sc) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {
				System.out.println("Choose Administrator function: ");
				System.out.println("1) Add/Update/Delete/Read Book and Author");
				System.out.println("2) Add/Update/Delete/Read Genres");
				System.out.println("3) Add/Update/Delete/Read Publishers");
				System.out.println("4) Add/Update/Delete/Read Library Branches");
				System.out.println("5) Add/Update/Delete/Read Borrowers");
				System.out.println("6) Over-ride Due Date for a Book Loan");
				System.out.println("7) Quit to previous");
				
				switch(sc.nextInt()) {
					case 1:
						this.bookAndAuthorCRUD(sc);
						continue;
					case 2:
						this.genresCRUD(sc);
						continue;
					case 3:
						this.publishersCRUD(sc);
						continue;
					case 4:
						this.libraryBranchesCRUD(sc);
						continue;
					case 5:
						this.borrowersCRUD(sc);
						continue;
					case 6: 
						this.overRideDueDate(sc);
						continue;
					case 7: 
						break;
					default:
						System.out.println("Enter a number 1-7");
						continue;
				}
				
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, try again enter 1-7");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}
	
	public void bookAndAuthorCRUD(Scanner sc) throws ClassNotFoundException, SQLException {

		Book book = new Book();
		List<Book> books = admin.readBooks();
		
		Author author = new Author();
		List<Author> authors = admin.readAuthors();

		Publisher publisher = new Publisher();
		List<Publisher> publishers = admin.readPublishers();

		Genre genre = new Genre();
		List<Genre> genres = admin.readGenres();
		
		BookAuthors bookAuthors = new BookAuthors();
		
		BookGenres bookGenres = new BookGenres();

		while (true) {
			try {
				System.out.println("1) Add");
				System.out.println("2) Read");
				System.out.println("3) Update");
				System.out.println("4) Delete");
				System.out.println("5) Quit to previous");
				
				switch(sc.nextInt()) {
					//add
					case 1:
						
						for (Book b : books) {
							System.out.println(b.getTitle());
						}
						System.out.println("Enter title of book to add as a string");
						sc.nextLine();
						String title = sc.nextLine();
						book.setTitle(title);
						
						book.setBookId(books.size() + 1);
						
						for (Author a : authors) {
							System.out.println(a.getAuthorId() + ") " + a.getAuthorName());
						}
						System.out.println("Enter ID of author to add");
						int authorId = sc.nextInt();
						book.setAuthId(authorId);
						
						for (Genre g : genres) {
							System.out.println(g.getGenre_id() + ") " + g.getGenre_name());
						}
						System.out.println("Enter ID of genre to add");
						int genreId = sc.nextInt();
						bookGenres.setGenre_id(genreId);
						
						for (Publisher p : publishers) {
							System.out.println(p.getPublisherId() + ") " + p.getPublisherName());
						}
						System.out.println("Enter ID of publisher to add");
						int publisherId = sc.nextInt();
						book.setPubId(publisherId);
						
						// add book
						System.out.println(admin.addBook(book));
						
						// add entry to bookAuthors
						bookAuthors.setBookId(book.getBookId());
						bookAuthors.setAuthorId(authorId);
						System.out.println(admin.addBookAuthors(bookAuthors));
						
						// add entry to bookGenres
						bookGenres.setBookId(book.getBookId());
						System.out.println(admin.addBookGenres(bookGenres));
						
						books.add(book);
						
						continue;
						
					//read
					case 2:
						// print list of all books, their authors, genres, and publishers
						for (Book b : books) {
							System.out.println(b.getTitle() + ", "
									+ "By: " + 
											admin.readAuthorsById(
											admin.readAuthorIdByBookId(b.getBookId()).get(0).getAuthorId())
											.get(0).getAuthorName()
									+ ", Publisher: " +
											admin.readPublishersById(b.getPubId()).get(0).getPublisherName()
									+ ", Genre: " +
											admin.readGenresById(
											admin.readGenreByBookId(b.getBookId()).get(0).getGenre_id())
											.get(0).getGenre_name()
									);
						}
						continue;
						
					//update
					case 3:
						continue;

					//delete
					case 4:
						continue;

					//quit
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
		
	}
	
	private void genresCRUD(Scanner sc) throws ClassNotFoundException, SQLException {
		
		while (true) {
			try {
				Genre genre = new Genre();
				List<Genre> genres = admin.readGenres();

				System.out.println("Genre: ");
				System.out.println("1) Add");
				System.out.println("2) Read");
				System.out.println("3) Update");
				System.out.println("4) Delete");
				System.out.println("5) Quit to previous");
				
				switch(sc.nextInt()) {
					// add
					case 1:
						try {
							System.out.println("Enter name of genre to add");
							sc.nextLine();
							genre.setGenre_name(sc.nextLine());
							System.out.println(admin.addGenre(genre));

						} catch (InputMismatchException | SQLException e) {
							System.out.println("Invalid input");
							sc.next();
							continue;
						}
						continue;
					// read
					case 2:
						System.out.println("Genres:");
						for (Genre g : genres) {
							System.out.println(g.getGenre_id() + ") " + g.getGenre_name());
						}
						continue;
					// update
					case 3:
						System.out.println("Enter Id number of genre you would like to update");
						for (Genre g : genres) {
							System.out.println(g.getGenre_id() + ") " + g.getGenre_name());
						}
						int id = sc.nextInt();

						System.out.println("Enter new name of genre");
						sc.nextLine();
						String name = sc.nextLine(); 
						
						genre.setGenre_id(id);
						genre.setGenre_name(name);
						System.out.println(admin.updateGenre(genre));
						
						continue;
					// delete
					case 4:
						System.out.println("Enter Id number of genre you would like to delete");
						for (Genre g : genres) {
							System.out.println(g.getGenre_id() + ") " + g.getGenre_name());
						}
						int id1 = sc.nextInt();

						genre.setGenre_id(id1);
						System.out.println(admin.deleteGenre(genre));
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
	}

	private void publishersCRUD(Scanner sc) throws SQLException, ClassNotFoundException {
		
		while (true) {
			try {
				Publisher publisher = new Publisher();
				List<Publisher> publishers = admin.readPublishers();

				System.out.println("Publisher: ");
				System.out.println("1) Add");
				System.out.println("2) Read");
				System.out.println("3) Update");
				System.out.println("4) Delete");
				System.out.println("5) Quit to previous");
				
				switch(sc.nextInt()) {
					// add
					case 1:
						try {
							System.out.println("Enter name of publisher to add");
							sc.nextLine();
							publisher.setPublisherName(sc.nextLine());

							System.out.println("Enter address of publisher to add");
							publisher.setPublisherAddress(sc.nextLine());
							
							System.out.println(admin.addPublisher(publisher));

						} catch (InputMismatchException | SQLException e) {
							System.out.println("Invalid input");
							sc.next();
							continue;
						}
						continue;
					// read
					case 2:
						System.out.println("Publishers:");
						for (Publisher p : publishers) {
							System.out.println(p.getPublisherId() + ") "
									+ p.getPublisherName() + ", " 
									+ p.getPublisherAddress());
						}
						continue;
					// update
					case 3:
						System.out.println("Enter Id number of publisher you would like to update");
						for (Publisher p : publishers) {
							System.out.println(p.getPublisherId() + ") "
									+ p.getPublisherName() + ", " 
									+ p.getPublisherAddress());
						}
						int id = sc.nextInt();
						publisher.setPublisherId(id);

						System.out.println("Enter new Name of publisher or N/A for no change");
						sc.nextLine();
						String name = sc.nextLine(); 
						
						System.out.println("Enter new Address of publisher or N/A for no change");
						String address = sc.nextLine();
						
						if (!"N/A".equals(name)) {
							publisher.setPublisherName(name);
						}
						if (!"N/A".equals(address)) {
							publisher.setPublisherAddress(address);
						}
						
						System.out.println(admin.updatePublisher(publisher));
						
						continue;
					// delete
					case 4:
						System.out.println("Enter Id number of publisher you would like to delete");
						for (Publisher p : publishers) {
							System.out.println(p.getPublisherId() + ") "
									+ p.getPublisherName() + ", " 
									+ p.getPublisherAddress());
						}
						int id1 = sc.nextInt();

						publisher.setPublisherId(id1);
						System.out.println(admin.deletePublisher(publisher));
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
	}
	
	private void libraryBranchesCRUD(Scanner sc) throws SQLException, ClassNotFoundException {
		
		while (true) {
			try {
				Branch branch = new Branch();
				List<Branch> branches = admin.readLibraryBranches();

				System.out.println("Library Branches: ");
				System.out.println("1) Add");
				System.out.println("2) Read");
				System.out.println("3) Update");
				System.out.println("4) Delete");
				System.out.println("5) Quit to previous");
				
				switch(sc.nextInt()) {
					// add
					case 1:
						try {
							System.out.println("Enter name of branch to add");
							sc.nextLine();
							branch.setBranchName(sc.nextLine());

							System.out.println("Enter address of branch to add");
							branch.setBranchAddress(sc.nextLine());
							
							System.out.println(admin.addLibraryBranch(branch));

						} catch (InputMismatchException | SQLException e) {
							System.out.println("Invalid input");
							sc.next();
							continue;
						}
						continue;
						
					// read
					case 2:
						System.out.println("Branches:");
						for (Branch b : branches) {
							System.out.println(b.getBranchId() + ") "
									+ b.getBranchName() + ", " 
									+ b.getBranchAddress());
						}
						continue;
						
					// update
					case 3:
						System.out.println("Enter Id number of branch you would like to update");
						for (Branch b : branches) {
							System.out.println(b.getBranchId() + ") "
									+ b.getBranchName() + ", " 
									+ b.getBranchAddress());
						}

						int id = sc.nextInt();
						branch.setBranchId(id);

						System.out.println("Enter new Name of branch or N/A for no change");
						sc.nextLine();
						String name = sc.nextLine(); 
						
						System.out.println("Enter new Address of branch or N/A for no change");
						String address = sc.nextLine();
						
						if (!"N/A".equals(name)) {
							branch.setBranchName(name);
						}
						if (!"N/A".equals(address)) {
							branch.setBranchAddress(address);
						}
						
						System.out.println(admin.updateLibraryBranch(branch));
						continue;

					// delete
					case 4:
						System.out.println("Enter Id number of branch you would like to delete");
						for (Branch b : branches) {
							System.out.println(b.getBranchId() + ") "
									+ b.getBranchName() + ", " 
									+ b.getBranchAddress());
						}
						int id1 = sc.nextInt();

						branch.setBranchId(id1);
						System.out.println(admin.deleteLibraryBranch(branch));
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
	}
		
	private void borrowersCRUD(Scanner sc) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		while (true) {
			try {
				Borrower borrower = new Borrower();
				List<Borrower> borrowers = admin.readAllBorrowers();

				System.out.println("Borrower: ");
				System.out.println("1) Add");
				System.out.println("2) Read");
				System.out.println("3) Update");
				System.out.println("4) Delete");
				System.out.println("5) Quit to previous");
				
				switch(sc.nextInt()) {
					// add
					case 1:
						try {
							System.out.println("Enter name of borrower to add");
							sc.nextLine();
							borrower.setName(sc.nextLine());

							System.out.println("Enter address of borrower to add");
							borrower.setAddress(sc.nextLine());
							
							System.out.println("Enter phone of borrower to add");
							borrower.setAddress(sc.nextLine());

							System.out.println(admin.addBorrower(borrower));

						} catch (InputMismatchException | SQLException e) {
							System.out.println("Invalid input");
							sc.next();
							continue;
						}
						continue;
					// read
					case 2:
						System.out.println("Borrowers:");
						for (Borrower b : borrowers) {
							System.out.println(b.getCardNo() + ") "
									+ b.getName() + ", " 
									+ b.getAddress() + ", "
									+ b.getPhone());
						}
						continue;
					// update
					case 3:
						System.out.println("Enter Id number of borrower you would like to update");
						for (Borrower b : borrowers) {
							System.out.println(b.getCardNo() + ") "
									+ b.getName() + ", " 
									+ b.getAddress() + ", "
									+ b.getPhone());
						}

						int id = sc.nextInt();
						borrower.setCardNo(id);

						System.out.println("Enter new Name of borrower or N/A for no change");
						sc.nextLine();
						String name = sc.nextLine(); 
						
						System.out.println("Enter new Address of borrower or N/A for no change");
						String address = sc.nextLine();
						
						System.out.println("Enter new Phone of borrower or N/A for no change");
						String phone = sc.nextLine();

						if (!"N/A".equals(name)) {
							borrower.setName(name);
						}
						if (!"N/A".equals(address)) {
							borrower.setAddress(address);
						}
						if (!"N/A".equals(phone)) {
							borrower.setPhone(phone);
						}
						
						System.out.println(admin.updateBorrower(borrower));
						
						continue;
					// delete
					case 4:
						System.out.println("Enter Id number of borrower you would like to delete");
						for (Borrower b : borrowers) {
							System.out.println(b.getCardNo() + ") "
									+ b.getName() + ", " 
									+ b.getAddress() + ", "
									+ b.getPhone());
						}
						int id1 = sc.nextInt();

						borrower.setCardNo(id1);
						System.out.println(admin.deleteBorrower(borrower));
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
	}
		
	private void overRideDueDate(Scanner sc) throws ClassNotFoundException, SQLException {
		
		List<BookLoans> bookLoans = admin.readBookLoans();
		
		while (true) {
			try {

				int count = 0;
				
				System.out.println("Select entry of book loans that you would like to override the due date of: ");
				for (BookLoans bl : bookLoans) {
					System.out.println(++count + ") " 
							+ "bookId " + bl.getBookId()
							+ " branchkId " + bl.getBranchId()
							+ " cardNo " + bl.getCardNo()
							+ " dateOut " + bl.getDateOut()
							+ " dueDate " + bl.getDateDue()
							+ " dateIn " + bl.getDateIn());
				}
				int id = sc.nextInt();
				
				if (id < 1 || id > count) {
					System.out.println("Please enter an integer shown");
					continue;
				}
				
				
				System.out.println("Enter new due date in format 'yyyy-mm-dd'");
				sc.nextLine();
				String dueDate = sc.nextLine();
				
				bookLoans.get(id - 1).setDateDue(java.sql.Date.valueOf(dueDate));
				
				admin.updateBookLoans(bookLoans.get(id - 1));
				
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter a valid integer");
				sc.next();
				continue;
			}
			break;
		}
		return;
	}
}
