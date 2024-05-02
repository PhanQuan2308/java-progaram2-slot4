package controller.booksController;

import model.BookDao;
import java.util.List;
import entity.Books;

public class ShowAllBookController {

    public List<Books> displayAllBook() {
        BookDao bookDao = new BookDao();
        return bookDao.showAllBooks();
    }
}
