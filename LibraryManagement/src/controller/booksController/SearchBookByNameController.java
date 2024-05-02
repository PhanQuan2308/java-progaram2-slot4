package controller.booksController;

import java.util.List;

import entity.Books;
import model.BookDao;

public class SearchBookByNameController {

    public List<Books> searchBookNameController(String name) {
        BookDao bookDao = new BookDao();
        List<Books> books = bookDao.searchByNameBook(name);
        return books; 
    }

   
}
