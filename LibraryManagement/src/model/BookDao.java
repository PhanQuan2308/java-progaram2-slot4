package model;

import dao.DbConnection;
import entity.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public static Connection conn;

    static {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String SQL_INSERT_BOOK = "INSERT INTO books (book_name, book_author, book_price, borrow_date, book_status, book_publisher, book_type) values (?,?,?,?,?,?,?)";
    public static final String SQL_SEARCH_NAME_BY_CHAR = "SELECT * FROM books WHERE book_name LIKE ?";
    public static final String SQL_SEARCH_AUTHOR_BY_CHAR = "SELECT * FROM books WHERE book_author LIKE ?";
    public static final String SQL_SEARCH_PUBLISHER_BY_CHAR = "SELECT * FROM books WHERE book_publisher LIKE ?";
    public static final String SQL_SEARCH_TYPE_BY_CHAR  = "SELECT * FROM books WHERE book_type LIKE ?";
    public static final String SQL_SHOW_ALL_BOOKS = "SELECT * FROM books";
    public static final String SQL_BORROW_BOOKS = "UPDATE books SET book_status = 'borrowed' WHERE book_name = ?";

    public static final String SQL_INSERT_BORROW_RECORD = "INSERT INTO borrow_records (student_id, book_name, borrow_date) VALUES (?, ?, ?)";

    public static final String SQL_RETURN_BOOK = "UPDATE books SET book_status = 'available' WHERE book_name = ?";

    public void createBooks(Books books) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_BOOK);
            preparedStatement.setString(1, books.getBook_name());
            preparedStatement.setString(2, books.getBook_author());
            preparedStatement.setDouble(3, books.getBook_price());
            preparedStatement.setDate(4, books.getBorrow_date());
            preparedStatement.setString(5, books.getBook_status()); // Add parameter binding for book_status
            preparedStatement.setString(6, books.getBook_publisher());
            preparedStatement.setString(7, books.getBook_type()); // Assuming book_type is the 7th parameter

            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                System.out.println("Create success");
            } else {
                System.out.println("Create fail");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Books> searchByNameBook(String name){
        List<Books> result = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SEARCH_NAME_BY_CHAR);
            preparedStatement.setString(1,"%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String bookName = rs.getString("book_name");
                String bookAuthor = rs.getString("book_author");
                double bookPrice = rs.getDouble("book_price");
                java.sql.Date borrowDate = rs.getDate("borrow_date");
                String bookStatus = rs.getString("book_status");
                String bookPublisher = rs.getString("book_publisher");
                String bookType = rs.getString("book_type");

                Books book = new Books(bookName, bookAuthor, bookPrice, borrowDate, bookStatus, bookPublisher, bookType);
                result.add(book);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public List<Books> searchByAuthor(String author){
        List<Books> result = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SEARCH_AUTHOR_BY_CHAR);
            preparedStatement.setString(1,"%" + author + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String bookName = rs.getString("book_name");
                String bookAuthor = rs.getString("book_author");
                double bookPrice = rs.getDouble("book_price");
                java.sql.Date borrowDate = rs.getDate("borrow_date");
                String bookStatus = rs.getString("book_status");
                String bookPublisher = rs.getString("book_publisher");
                String bookType = rs.getString("book_type");

                Books book = new Books(bookName, bookAuthor, bookPrice, borrowDate, bookStatus, bookPublisher, bookType);
                result.add(book);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Books> searchByPublisher(String publisher){
        List<Books> result = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SEARCH_PUBLISHER_BY_CHAR);
            preparedStatement.setString(1,"%" + publisher + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String bookName = rs.getString("book_name");
                String bookAuthor = rs.getString("book_author");
                double bookPrice = rs.getDouble("book_price");
                java.sql.Date borrowDate = rs.getDate("borrow_date");
                String bookStatus = rs.getString("book_status");
                String bookPublisher = rs.getString("book_publisher");
                String bookType = rs.getString("book_type");

                Books book = new Books(bookName, bookAuthor, bookPrice, borrowDate, bookStatus, bookPublisher, bookType);
                result.add(book);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Books> searchByType(String type){
        List<Books> result = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SEARCH_TYPE_BY_CHAR);
            preparedStatement.setString(1,"%" + type + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String bookName = rs.getString("book_name");
                String bookAuthor = rs.getString("book_author");
                double bookPrice = rs.getDouble("book_price");
                java.sql.Date borrowDate = rs.getDate("borrow_date");
                String bookStatus = rs.getString("book_status");
                String bookPublisher = rs.getString("book_publisher");
                String bookType = rs.getString("book_type");

                Books book = new Books(bookName, bookAuthor, bookPrice, borrowDate, bookStatus, bookPublisher, bookType);
                result.add(book);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<String> showAllBooks() {
        List<String> bookNames = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SHOW_ALL_BOOKS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String bookName = rs.getString("book_name");
                bookNames.add(bookName); // Thêm tên sách vào danh sách bookNames
                String bookAuthor = rs.getString("book_author");
                double bookPrice = rs.getDouble("book_price");
                java.sql.Date borrowDate = rs.getDate("borrow_date");
                String bookStatus = rs.getString("book_status");
                String bookPublisher = rs.getString("book_publisher");
                String bookType = rs.getString("book_type");

                System.out.println("Book Name: " + bookName);
                System.out.println("Author: " + bookAuthor);
                System.out.println("Price: " + bookPrice);
                System.out.println("Borrow Date: " + borrowDate);
                System.out.println("Status: " + bookStatus);
                System.out.println("Publisher: " + bookPublisher);
                System.out.println("Type: " + bookType);
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bookNames; // Trả về danh sách các tên sách
    }

    public void borrowBook(int studentId, String bookName){
        try {
            // Cập nhật trạng thái của sách thành 'borrowed'
            PreparedStatement borrowStatement = conn.prepareStatement(SQL_BORROW_BOOKS);
            borrowStatement.setString(1, bookName);
            borrowStatement.executeUpdate();

            // Thêm bản ghi mượn sách vào bảng borrow_records
            PreparedStatement insertBorrowStatement = conn.prepareStatement(SQL_INSERT_BORROW_RECORD);
            insertBorrowStatement.setInt(1, studentId);
            insertBorrowStatement.setString(2, bookName);
            insertBorrowStatement.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now())); // Lấy ngày hiện tại
            insertBorrowStatement.executeUpdate();

            System.out.println("Book borrowed successfully.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getBorrowedBooks(int studentId) {
        List<String> borrowedBooks = new ArrayList<>();
        try {
            String sql = "SELECT book_name FROM borrow_records WHERE student_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String bookName = rs.getString("book_name");
                borrowedBooks.add(bookName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return borrowedBooks;
    }




    public void returnBook(String bookName, int studentId) {
        try {
            // Cập nhật trạng thái của sách thành 'available'
            PreparedStatement returnStatement = conn.prepareStatement(SQL_RETURN_BOOK);
            returnStatement.setString(1, bookName);
            int updatedRows = returnStatement.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("Trả sách thành công.");
                // Xóa thông tin của sách đã được trả khỏi bảng borrow_records
                String deleteRecordQuery = "DELETE FROM borrow_records WHERE book_name = ? AND student_id = ?";
                PreparedStatement deleteStatement = conn.prepareStatement(deleteRecordQuery);
                deleteStatement.setString(1, bookName);
                deleteStatement.setInt(2, studentId);
                deleteStatement.executeUpdate();
            } else {
                System.out.println("Không thể trả sách. Sách có thể chưa được mượn hoặc không tồn tại.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}










