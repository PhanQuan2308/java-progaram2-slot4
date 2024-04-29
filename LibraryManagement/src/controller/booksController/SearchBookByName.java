package controller.booksController;

import entity.Books;
import model.BookDao;

import java.util.List;
import java.util.Scanner;

public class SearchBookByName {
    private static final Scanner sc = new Scanner(System.in);

    public static void searchBookName(){
        try {
            System.out.println("Enter name book to search: ");
            String nameBook = sc.nextLine();

            BookDao bookDao = new BookDao();
            List<Books> result = bookDao.searchByNameBook(nameBook);

            if(result.isEmpty()){
                System.out.println("No book found with name.");
            }else {
                System.out.println("===============================");

                System.out.println("Information book is: ");
                for (Books book : result) {
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

    public static void main(String[] args) {
        searchBookName();
    }
}
