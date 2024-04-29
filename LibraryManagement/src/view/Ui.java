package view;

import java.util.Scanner;

import controller.booksController.BorrowBook;
import controller.booksController.CreateBooks;
import controller.booksController.SearchBookByName;
import controller.booksController.SearchByAuthor;
import controller.booksController.SearchByPublisher;
import controller.booksController.SearchByType;
import controller.booksController.ShowAllBook;
import controller.booksController.ShowAllBookBorrowed;

public class Ui {
    public static final Scanner sc = new Scanner(System.in);
    public  static void menu(){
        System.out.println("===============Library Manager =================");
        System.out.println("1. Create new books");
        System.out.println("2. Search by name");
        System.out.println("3. Search by author");
        System.out.println("4. Search by publisher");
        System.out.println("5. Search by type");
        System.out.println("6. Borrow books");
        System.out.println("7. Show all books");
        System.out.println("8. Show all books borrowed");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        int choice;
        do{
            menu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    CreateBooks.addBooks();
                    break;
                case 2:
                    SearchBookByName.searchBookName();
                    break;
                case 3:
                    SearchByAuthor.searchBookAuthor();
                    break;
                case 4:
                    SearchByPublisher.searchBookPublisher();
                    break;
                case 5:
                    SearchByType.searchBookType();
                    break;
                case 6:
                    BorrowBook.borrowBook();
                    break;
                case 7:
                    ShowAllBook.displayAllBook();
                    break;
                case 8:
                    ShowAllBookBorrowed.showAllBorrowedBooks();
                    break;
                case 0:
                    System.out.println("Exit");
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(choice != 0);
    }
}
