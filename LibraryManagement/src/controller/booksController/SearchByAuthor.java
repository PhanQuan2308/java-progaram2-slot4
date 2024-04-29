package controller.booksController;

import entity.Books;
import model.BookDao;

import java.util.List;
import java.util.Scanner;

public class SearchByAuthor {
    private static final Scanner sc = new Scanner(System.in);

    public static void searchBookAuthor(){
        try {
            System.out.println("Enter author book to search: ");
            String authorBook = sc.nextLine();

            BookDao bookDao = new BookDao();
            List<Books> result = bookDao.searchByAuthor(authorBook);

            if(result.isEmpty()){
                System.out.println("No author found with name.");
            }else {
                System.out.println("===============================");

                System.out.println("Information book search by author is: ");
                for (Books author : result) {
                    System.out.println(" Title: " + author.getBook_name() +
                            "\n Author: " + author.getBook_author() +
                            "\n Price: " + author.getBook_price() +
                            "\n Borrow Date: " + author.getBorrow_date() +
                            "\n Status: " + author.getBook_status() +
                            "\n Publisher: " + author.getBook_publisher() +
                            "\n Type: " + author.getBook_type());
                    System.out.println("==========================");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        searchBookAuthor();
    }
}
