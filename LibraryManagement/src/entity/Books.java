package entity;

import java.util.Date;

public class Books {

    private String book_name;
    private String book_author;
    private double book_price;
    private java.sql.Date borrow_date;
    private String book_status;
    private String book_publisher;
    private String book_type;
    public Books() {
    }

    public Books(String book_name, String book_author, double book_price, java.sql.Date borrow_date, String book_status, String book_publisher, String book_type) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_price = book_price;
        this.borrow_date = borrow_date;
        this.book_status = book_status;
        this.book_publisher = book_publisher;
        this.book_type = book_type;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_status() {
        return book_status;
    }

    public void setBook_status(String book_status) {
        this.book_status = book_status;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public java.sql.Date getBorrow_date() {
        return (java.sql.Date) borrow_date;
    }

    public void setBorrow_date(java.sql.Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }
}
