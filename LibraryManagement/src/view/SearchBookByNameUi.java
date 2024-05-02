package view;

import java.util.List;
import java.util.Scanner;

import controller.booksController.SearchBookByNameController;
import entity.Books;

public class SearchBookByNameUi {
    private static final Scanner sc = new Scanner(System.in);

    public void searchByName() {
        try {
            System.out.println("Enter name book to search: ");
            String nameBook = sc.nextLine();
            SearchBookByNameController controller = new SearchBookByNameController(); 
            List<Books> books = controller.searchBookNameController(nameBook);

            if (books.isEmpty()) {
                System.out.println("No book found with name.");
            } else {
                System.out.println("===============================");

                System.out.println("Information book is: ");
                for (Books book : books) {
                    System.out.println(" Title: " + book.getBook_name() +
                            "\n Author: " + book.getBook_author() +
                            "\n Price: " + book.getBook_price() +
                            "\n Borrow Date: " + book.getBorrow_date() +
                            "\n Status: " + book.getBook_status() +
                            "\n Publisher: " + book.getBook_publisher() +
                            "\n Type: " + book.getBook_type());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}