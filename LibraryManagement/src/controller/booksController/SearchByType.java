package controller.booksController;

import entity.Books;
import model.BookDao;

import java.util.List;
import java.util.Scanner;

public class SearchByType {
    private static final Scanner sc = new Scanner(System.in);

    public static void searchBookType(){
        try {
            System.out.println("Enter type book to search: ");
            String typeBook = sc.nextLine();

            BookDao bookDao = new BookDao();
            List<Books> result = bookDao.searchByType(typeBook);

            if(result.isEmpty()){
                System.out.println("No type found with name.");
            }else {
                System.out.println("===============================");

                System.out.println("Information book search by type is: ");
                for (Books type : result) {
                    System.out.println(" Title: " + type.getBook_name() +
                            "\n Author: " + type.getBook_author() +
                            "\n Price: " + type.getBook_price() +
                            "\n Borrow Date: " + type.getBorrow_date() +
                            "\n Status: " + type.getBook_status() +
                            "\n Publisher: " + type.getBook_publisher() +
                            "\n Type: " + type.getBook_type());
                    System.out.println("==========================");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        searchBookType();
    }
}
