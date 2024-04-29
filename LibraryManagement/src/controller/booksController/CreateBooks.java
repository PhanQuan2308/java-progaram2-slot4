package controller.booksController;

import entity.Books;
import model.BookDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CreateBooks {
    private static final Scanner sc = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void addBooks(){

        String specialCharacters = "[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>\\/?]";
        boolean hasSpecialCharacter;
        String nameBook, authorBook, dateInput,publBook,typeBook,statusBook ;
        Double priceInput;

        try {

            //===============================================
            do {
                System.out.println("Enter name book: ");
                nameBook = sc.nextLine();

                hasSpecialCharacter = nameBook.matches(".*" + specialCharacters + ".*");

                if (hasSpecialCharacter) {
                    System.out.println("Name book do not have charactor, please enter again");
                }
            } while (hasSpecialCharacter);

            //===============================================

            do{
                System.out.println("Enter author book: ");
                authorBook = sc.nextLine();

                hasSpecialCharacter = authorBook.matches(".*" + specialCharacters + ".*");

                if(hasSpecialCharacter){
                    System.out.println("Author book do not have charactor, please enter again");
                }
            }while (hasSpecialCharacter);

            double price;


            //===============================================

            do {
                System.out.println("Enter price of the book: ");
                priceInput = Double.valueOf(sc.nextLine());

                try {
                    // Parse the input as a double
                    price = Double.parseDouble(String.valueOf(priceInput));

                    if (price <= 0) {
                        System.out.println("Price must be greater than 0. Please enter again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid price.");
                    price = -1; // Assigning a negative value to price to continue the loop
                }
            } while (price <= 0);


            //===============================================

            Date borrowDate;
            do{
                System.out.println("Enter date of book (YYYY-MM-DD): ");
                dateInput = sc.nextLine();


                try {
                    borrowDate = dateFormat.parse(dateInput);


                }catch (java.text.ParseException e){
                    System.out.println("Invalid date format. Please enter date in format YYYY-MM-DD.");
                    borrowDate = null;
                }

            }while (borrowDate == null);

            //===============================================

            System.out.println("Enter stutus of book: ");
            statusBook = sc.nextLine();
            //===============================================

            System.out.println("Enter publisher book: ");
            publBook = sc.nextLine();
            //===============================================

            System.out.println("Enter type of book: ");
            typeBook = sc.nextLine();


            java.sql.Date sqlDate = new java.sql.Date(borrowDate.getTime());
            Books books = new Books(nameBook, authorBook, priceInput, sqlDate, statusBook, publBook, typeBook);
            BookDao bookDao = new BookDao();
            bookDao.createBooks(books);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        addBooks();
    }
}
