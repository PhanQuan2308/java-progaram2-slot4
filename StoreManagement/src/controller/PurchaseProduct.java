package controller;

import java.util.List;
import java.util.Scanner;
import entity.Product;
import model.ProductDAO;
import java.util.Date; // Import Date class
import java.text.SimpleDateFormat;

public class PurchaseProduct {
    public static final Scanner sc = new Scanner(System.in);

    public static void purchaseProduct() {
        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> allProducts = productDAO.showAllProduct();

            // Hiển thị toàn bộ danh sách sản phẩm
            System.out.println("All available products:");
            for (Product product : allProducts) {
                System.out.println(product.getProduct_id() + " - " +product.getProduct_name() + " - " + product.getDescription() + " - "
                        + product.getQuantity() + " - $" + product.getPrice() + " - " + product.getBrand());
            }

            // Nhập thông tin đơn hàng từ người dùng
            System.out.println("Enter ID of the product to buy: ");
            int productId = Integer.parseInt(sc.nextLine());

            // Kiểm tra xem sản phẩm có tồn tại không
            boolean isValidProduct = false;
            Product selectedProduct = null;
            for (Product product : allProducts) {
                if (product.getProduct_id() == productId) {
                    selectedProduct = product;
                    isValidProduct = true;
                    break;
                }
            }

            if (!isValidProduct) {
                System.out.println("Invalid product ID.");
                return;
            }

            System.out.println("Enter quantity to buy: ");
            int quantityToBuy = Integer.parseInt(sc.nextLine());

            // Kiểm tra số lượng mua có hợp lệ không
            if (quantityToBuy <= 0 || quantityToBuy > selectedProduct.getQuantity()) {
                System.out.println("Invalid quantity to buy.");
                return;
            }

            // Thực hiện mua hàng
            if (productDAO.purchaseProduct(selectedProduct.getProduct_name(), quantityToBuy)) {
                System.out.println("Purchase successful.");
            } else {
                System.out.println("Failed to purchase the product.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    
}
