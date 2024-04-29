package model;

import java.sql.Connection;
import java.sql.PreparedStatement; // Correct import statement
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.cj.xdevapi.PreparableStatement;

import dao.DbConnection;
import entity.Product;

public class ProductDAO {
    public static final Connection conn;

    static {
        Connection tempConn = null;
        try {
            tempConn = DbConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = tempConn;
    }

    public static final String SQL_INSERT_PRODUCT = "INSERT INTO products(product_name, description, quantity, price, brand) values(?,?,?,?,?)";
    public static final String SQL_SEARCH_BY_NAME = "SELECT * FROM products WHERE product_name LIKE ?";
    public static final String SQL_DELETE_BY_NAME = "DELETE FROM products WHERE product_name LIKE ?";
    public static final String SQL_SHOW_ALL_PRODUCT = "SELECT * FROM products";
    public static final String SQL_PRODUCT_OUT_OF_STOCK = "SELECT * FROM products WHERE quantity <= 0";
    public static final String SQL_UPDATE_PRODUCT = "UPDATE products SET  quantity = ? WHERE product_name =?";
    public static final String SQL_BEST_SELLING_PRODUCT = "SELECT p.product_id, p.product_name, p.description, p.quantity, p.price, p.brand, SUM(o.quantity_ordered) AS total_ordered "
            + "FROM products p "
            + "JOIN orders o ON p.product_id = o.product_id "
            + "GROUP BY p.product_id, p.product_name, p.description, p.price, p.brand "
            + "ORDER BY total_ordered DESC "
            + "LIMIT 1";

    public void createProduct(Product product) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_PRODUCT);
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getBrand());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product created successfully.");
            } else {
                System.out.println("Failed to create product.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SEARCH_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_name(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                product.setBrand(rs.getString("brand"));
                products.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;

    }

    public boolean deleteProduct(String nameProduct) {
        boolean deleted = false;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_BY_NAME);
            preparedStatement.setString(1, nameProduct);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
                deleted = true;
            } else {
                System.out.println("Failed to delete product.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public List<Product> showAllProduct() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SHOW_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                product.setBrand(rs.getString("brand"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getOutOfStock() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_PRODUCT_OUT_OF_STOCK);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_name(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getDouble("price"));
                product.setBrand(rs.getString("brand"));
                products.add(product);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return products;

    }

    public void updateProduct(Product product) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_PRODUCT);
            preparedStatement.setInt(1, product.getQuantity());
            preparedStatement.setString(2, product.getProduct_name());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public boolean insertOrder(int productId, int quantityOrdered) {
        try {
            String SQL_INSERT_ORDER = "INSERT INTO orders (product_id, quantity_ordered, order_date) VALUES (?, ?, NOW())";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_ORDER);
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, quantityOrdered);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean purchaseProduct(String productName, int quantityToBuy) {
        try {
            // Tìm sản phẩm trong danh sách sản phẩm dựa trên productName
            Product product = null;
            for (Product p : showAllProduct()) {
                if (p.getProduct_name().equalsIgnoreCase(productName)) {
                    product = p;
                    break;
                }
            }

            // Kiểm tra xem sản phẩm có tồn tại không
            if (product == null) {
                System.out.println("Product not found");
                return false;
            }

            int available = product.getQuantity();
            if (quantityToBuy <= 0 || quantityToBuy > available) {
                System.out.println("Invalid quantity to buy or not enough quantity available.");
                return false;
            }

            int remaining = available - quantityToBuy;
            product.setQuantity(remaining);

            // Cập nhật thông tin sản phẩm
            updateProduct(product);

            // Thêm thông tin đơn hàng vào bảng orders
            if (insertOrder(product.getProduct_id(), quantityToBuy)) {
                if (remaining == 0) {
                    System.out.println("Product " + productName + " out of stock");
                } else {
                    System.out.println("Purchase successful");
                }
                return true;
            } else {
                System.out.println("Failed to insert order.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getBestSellingProduct(int limit) {
        List<Product> bestSellingProducts = new ArrayList<>();
        try {
            String SQL_BEST_SELLING_PRODUCT = "SELECT p.product_id, p.product_name, p.description, p.quantity, p.price, p.brand, SUM(o.quantity_ordered) AS total_ordered "
                    + "FROM products p "
                    + "JOIN orders o ON p.product_id = o.product_id "
                    + "GROUP BY p.product_id, p.product_name, p.description, p.price, p.brand "
                    + "ORDER BY total_ordered DESC "
                    + "LIMIT ?";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_BEST_SELLING_PRODUCT);
            preparedStatement.setInt(1, limit);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_name(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("total_ordered")); // Sửa thành total_ordered để lấy số lượng đã bán
                product.setPrice(rs.getDouble("price"));
                product.setBrand(rs.getString("brand"));
                bestSellingProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bestSellingProducts;
    }

}