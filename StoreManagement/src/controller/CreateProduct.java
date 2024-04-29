package controller;

import java.util.Scanner;

import entity.Product;
import model.ProductDAO;

public class CreateProduct {
    public static final Scanner sc = new Scanner(System.in);

    public static void addProduct() {
        try {
            System.out.println("Enter name product: ");
            String addName = sc.nextLine();

            System.out.println("Enter description: ");
            String addDescription = sc.nextLine();

            System.out.println("Enter quantity: ");
            int addQuantity = Integer.parseInt(sc.nextLine());

            System.out.println("Enter price: ");
            double addPrice = Double.parseDouble(sc.nextLine());

            System.out.println("Enter brand: ");
            String addBrand = sc.nextLine();

            Product product = new Product(addName, addDescription, addQuantity, addPrice, addBrand);
            ProductDAO productDAO = new ProductDAO();
            productDAO.createProduct(product);

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    
}
