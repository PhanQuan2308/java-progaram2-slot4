package controller;

import java.util.Scanner; // Importing Scanner from java.util
import java.util.List;

import model.ProductDAO;
import entity.Product;

public class SearchByName {
    private static final Scanner sc = new Scanner(System.in);

    public static void searchByName() {
        try {
            System.out.println("Enter name product to search: ");
            String searchName = sc.nextLine();
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.searchByName(searchName);

            if (products.isEmpty()) {
                System.out.println("No products found!");
            } else {
                for (Product product : products) {
                    System.out.println(product.getProduct_name() + " - " + product.getDescription() + " - " +
                            product.getQuantity() + " - $" + product.getPrice() + " - " + product.getBrand());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
