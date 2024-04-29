package controller.booksController;

import entity.Books;
import model.BookDao;

import java.util.List;
import java.util.Scanner;

public class SearchByPublisher {
    private static final Scanner sc = new Scanner(System.in);

    public static void searchBookPublisher(){
        try {
            System.out.println("Enter author book to search: ");
            String publisherBook = sc.nextLine();

            BookDao bookDao = new BookDao();
            List<Books> result = bookDao.searchByPublisher(publisherBook);

            if(result.isEmpty()){
                System.out.println("No publisher found with name.");
            }else {
                System.out.println("===============================");

                System.out.println("Information book search by publisher is: ");
                for (Books publisher : result) {
                    System.out.println(" Title: " + publisher.getBook_name() +
                            "\n Author: " + publisher.getBook_author() +
                            "\n Price: " + publisher.getBook_price() +
                            "\n Borrow Date: " + publisher.getBorrow_date() +
                            "\n Status: " + publisher.getBook_status() +
                            "\n Publisher: " + publisher.getBook_publisher() +
                            "\n Type: " + publisher.getBook_type());
                    System.out.println("==========================");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        searchBookPublisher();
    }

}
