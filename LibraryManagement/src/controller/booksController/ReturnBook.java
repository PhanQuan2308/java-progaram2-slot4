package controller.booksController;

import model.BookDao;

import java.util.List;
import java.util.Scanner;

public class ReturnBook {
    private static final Scanner sc = new Scanner(System.in);

    public static void returnBook(int studentId) {
        try {
            BookDao bookDao = new BookDao();

            List<String> borrowedBooks = bookDao.getBorrowedBooks(studentId);

            if (borrowedBooks.isEmpty()) {
                System.out.println("Student has not borrowed any books.");
                return;
            }

            System.out.println("Books borrowed by student: ");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.println((i + 1) + ". " + borrowedBooks.get(i));
            }

            System.out.print("Enter the number of the book to return: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice < 1 || choice > borrowedBooks.size()) {
                System.out.println("Invalid choice. Please enter a number within the range.");
                return;
            }

            String bookToReturn = borrowedBooks.get(choice - 1);
            bookDao.returnBook(bookToReturn, studentId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        returnBook(studentId);
    }

}
