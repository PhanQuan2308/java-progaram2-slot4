package view;

import java.util.List;
import java.util.Scanner;

import controller.booksController.ShowAllBookController;
import entity.Books;

public class ShowAllBookUi {
    public static final Scanner sc = new Scanner(System.in);

    public void showAllBooksUi() {
        System.out.println("All Books: ");
        ShowAllBookController showAllBookController = new ShowAllBookController();
        List<Books> books = showAllBookController.displayAllBook();

        for (Books book : books) {
            System.out.println("Book Name: " + book.getBook_name());
            System.out.println("Author: " + book.getBook_author());
            System.out.println("Price: " + book.getBook_price());
            System.out.println("Borrow Date: " + book.getBorrow_date());
            System.out.println("Status: " + book.getBook_status());
            System.out.println("Publisher: " + book.getBook_publisher());
            System.out.println("Type: " + book.getBook_type());
            System.out.println("----------------------------------------");
        }
    }
}

