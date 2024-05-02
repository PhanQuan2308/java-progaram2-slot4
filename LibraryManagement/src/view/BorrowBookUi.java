package view;

import java.util.List;
import java.util.Scanner;
import controller.booksController.BorrowBookController;
import controller.booksController.SearchBookByNameController;
import entity.Books;

public class BorrowBookUi {
    private static final Scanner sc = new Scanner(System.in);

    public void borrowBookUi() {
        try {
            System.out.println("Enter student ID: ");
            int studentId = sc.nextInt();
            sc.nextLine(); // Consume newline character

            System.out.println("Enter book name: ");
            String bookName = sc.nextLine();

            SearchBookByNameController searchBookByNameController = new SearchBookByNameController();
            List<Books> availableBooks = searchBookByNameController.searchBookNameController(bookName);

            if (availableBooks.isEmpty()) {
                System.out.println("No matching books found.");
                return;
            }

            System.out.println("Choose a book to borrow:");
            for (int i = 0; i < availableBooks.size(); i++) {
                System.out.println((i + 1) + ". " + availableBooks.get(i).getBook_name());
            }

            System.out.print("Enter the number of the book: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character

            if (choice < 1 || choice > availableBooks.size()) {
                System.out.println("Invalid choice. Please enter a number within the range.");
                return;
            }

            Books selectedBook = availableBooks.get(choice - 1);

            if ("borrowed".equals(selectedBook.getBook_status())) {
                System.out.println("This book has already been borrowed. Please choose another book.");
            } else {
                BorrowBookController bookController = new BorrowBookController();
                bookController.borrowBook(studentId, selectedBook.getBook_name());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
