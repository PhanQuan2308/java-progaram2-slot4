package controller;

import java.util.List;
import java.util.Scanner;

import entity.Product;
import model.ProductDAO;

public class BestSellingProduct {
    public static final Scanner sc = new Scanner(System.in);

    public static void bestSellingProducts() {
        try {
            System.out.println("====== Best Selling Products ========");
            System.out.println("Enter limit: ");
            int limit = Integer.parseInt(sc.nextLine());
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.getBestSellingProduct(limit);

            if (products.isEmpty()) {
                System.out.println("No best selling products found.");
            } else {
                System.out.println("Best Selling Products:");
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
