package controller.booksController;

import entity.Books;
import model.BookDao;

import java.util.List;
import java.util.Scanner;

public class BorrowBook {
    private static final Scanner sc = new Scanner(System.in);

    public static void borrowBook(){
        try {
            BookDao bookDao = new BookDao();
            List<String> bookNames = bookDao.showAllBooks(); // Corrected method name

            if(bookNames.isEmpty()){
                System.out.println("No books available for borrowing.");
                return;
            }

            System.out.println("Choose a book to borrow:");
            for (int i = 0; i < bookNames.size(); i++) {
                System.out.println((i + 1) + ". " + bookNames.get(i));
            }

            System.out.print("Enter the number of the book: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice < 1 || choice > bookNames.size()) {
                System.out.println("Invalid choice. Please enter a number within the range.");
                return;
            }

            String selectedBook = bookNames.get(choice - 1);

            Books book = bookDao.searchByNameBook(selectedBook).get(0);

            if ("borrowed".equals(book.getBook_status())) {
                System.out.println("This book has already been borrowed. Please choose another book.");
            } else {
                System.out.println("Enter student ID: ");
                int studentId = sc.nextInt();
                sc.nextLine();
                bookDao.borrowBook(studentId, selectedBook);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        borrowBook();
    }
}
