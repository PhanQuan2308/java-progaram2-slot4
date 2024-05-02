package controller.booksController;

import model.BookDao;

public class BorrowBookController {

    public void borrowBook(int studentId, String bookName){
        try {
            BookDao bookDao = new BookDao();
            bookDao.borrowBook(studentId, bookName);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    
}
