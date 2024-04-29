package controller;
import java.util.List;

import model.ProductDAO;
import entity.Product;

public class ShowAllProduct {

    public static void showAllProduct() {
        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.showAllProduct();
            for (Product product : products) {
                System.out.println(product.getProduct_name() + " - " + product.getDescription() + " - " +
                product.getQuantity() + " - $" + product.getPrice() + " - " + product.getBrand());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
}
