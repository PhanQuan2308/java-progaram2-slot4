package controller.booksController;

import java.util.List;
import java.util.Scanner;

import model.BookDao;

public class ShowAllBookBorrowed {
    public static final Scanner sc = new Scanner(System.in);

    public static void showAllBorrowedBooks(){
        try{       
             BookDao bookDao = new BookDao();
             System.out.println("Enter your id: ");
             int idStu = sc.nextInt();
             List<String> borrowedBooks = bookDao.getBorrowedBooks(idStu);
             if(borrowedBooks.isEmpty()){
                System.out.println("No books borrowed");
             }else{
                System.out.println("List book borrowed: ");
                for(String book : borrowedBooks){
                    System.out.println("-" + book);
                }
             }
            

        }catch(Exception e){
        e.printStackTrace();

    }
    
}
public static void main(String[] args) {
    showAllBorrowedBooks();
}
}