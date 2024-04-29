package controller.booksController;

import model.BookDao;

import java.util.Scanner;

public class ShowAllBook {
    public static final Scanner sc = new Scanner(System.in);

    public static void displayAllBook(){
        try {
            System.out.println("All Books: ");
            BookDao bookDao = new BookDao();
            bookDao.showAllBooks();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        displayAllBook();
    }
}
