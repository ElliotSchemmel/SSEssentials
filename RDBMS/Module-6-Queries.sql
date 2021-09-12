# How many copies of the book titled The Lost Tribe are owned by the library branch whose name is "Sharpstown"?
select noOfCopies
from tbl_book_copies
join tbl_library_branch on  tbl_book_copies.branchId = tbl_library_branch.branchId
join tbl_book on tbl_book_copies.bookId = tbl_book.bookId
where tbl_book.title = "The Lost Tribe" and tbl_library_branch.branchName = "Sharpstown";


# How many copies of the book titled The Lost Tribe are owned by each library branch?
select tbl_library_branch.branchName, noOfCopies
from tbl_book_copies
join tbl_library_branch on  tbl_book_copies.branchId = tbl_library_branch.branchId
join tbl_book on tbl_book_copies.bookId = tbl_book.bookId
where tbl_book.title = "The Lost Tribe";


# Retrieve the names of all borrowers who do not have any books checked out
select name
from tbl_borrower
left join tbl_book_loans on tbl_book_loans.cardNo = tbl_borrower.cardNo
where tbl_book_loans.bookId is null
order by name asc;


# For each book that is loaned out from the "Sharpstown" branch and whose DueDate is today, 
# retrieve the book title, the borrower's name, and the borrower's address.
select tbl_book.title, tbl_borrower.name, tbl_borrower.address, tbl_book_loans.dueDate
from tbl_book_loans
join tbl_book on tbl_book_loans.bookId = tbl_book.bookId
join tbl_Library_branch on  tbl_book_loans.branchId = tbl_library_branch.branchId
join tbl_borrower on tbl_book_loans.cardNo = tbl_borrower.cardNo
where tbl_library_branch.branchName = "Sharpstown"
and date(tbl_book_loans.dueDate) = "2018-04-01";


# For each library branch, retrieve the branch name and the total number of books loaned out from that branch.
select tbl_library_branch.branchName, count(tbl_book_loans.bookId) as NumberOfBooksLoanedFromBranch
from tbl_book_loans
join tbl_library_branch on tbl_book_loans.branchId = tbl_library_branch.branchId
group by tbl_library_branch.branchName;


# Retrieve the names, addresses, and number of books checked out for all borrowers who have more than five books checked out. 
select tbl_borrower.name, tbl_borrower.address, count(tbl_book_loans.bookId) as NumberOfBooksCheckedOut
from tbl_book_loans
join tbl_borrower on tbl_book_loans.cardNo = tbl_borrower.cardNo
group by tbl_borrower.name, tbl_borrower.address
having count(tbl_book_loans.bookId) > 5;


# For each book authored (or co-authored) by "Stephen King", retrieve the title and the number of copies owned by the library branch whose name is "Central"
select tbl_book.title, tbl_book_copies.noOfCopies
from tbl_book
join tbl_book_copies on tbl_book.bookId = tbl_book_copies.bookId
join tbl_author on tbl_book.authId = tbl_author.authorId
join tbl_library_branch on tbl_book_copies.branchId = tbl_library_branch.branchId
where tbl_author.authorName = "Stephen King"
and tbl_library_branch.branchName = "Central";
