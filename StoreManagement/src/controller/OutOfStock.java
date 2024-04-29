package controller;

import java.util.Scanner;

import entity.Product;

import java.util.List;

import model.ProductDAO;

public class OutOfStock {
    public static final Scanner sc = new Scanner(System.in);

    public static void outOfStock() {
        try {
            System.out.println("All products out of stock: ");
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.getOutOfStock();

            if (products.isEmpty()) {
                System.out.println("No products out of stock");
            } else {
                System.out.println("All products out of stock: ");
                for (Product product : products) {
                    System.out.println(product.getProduct_name() + " - " + product.getDescription() + " - " +
                            product.getQuantity() + " - $" + product.getPrice() + " - " + product.getBrand());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    
}
