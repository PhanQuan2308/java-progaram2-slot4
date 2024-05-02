package controller.booksController;

import entity.Books;
import model.BookDao;



public class CreateBooksController {
   
    public  void addBooks(Books books){

      
            BookDao bookDao = new BookDao();
            bookDao.createBooks(books);
        
    }

   
}
