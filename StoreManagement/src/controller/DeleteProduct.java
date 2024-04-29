package controller;

import java.util.Scanner;

import model.ProductDAO;

public class DeleteProduct {
    public static final Scanner sc = new Scanner(System.in);

    public static void deleteProduct(){
        try {
            System.out.println("Enter name product: ");
            String productName = sc.nextLine();
            
            ProductDAO productDAO = new ProductDAO();
            boolean isDeleted = productDAO.deleteProduct(productName);
            
            if (isDeleted) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Failed to delete product.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }    
    }
}
   
