package view;
import java.util.Scanner;

import controller.BestSellingProduct;
import controller.CreateProduct;
import controller.DeleteProduct;
import controller.OutOfStock;
import controller.PurchaseProduct;
import controller.SearchByName;
import controller.ShowAllProduct;
import model.ProductDAO;
public class Ui {

    private static final Scanner sc = new Scanner(System.in);

    public static void menu(){
        System.out.println("========= Store Management ========");
        System.out.println("1. Add Product");
        System.out.println("2. Search Product By Name");
        System.out.println("3. Delete Product");
        System.out.println("4. Show All Products");
        System.out.println("5: Products out of stock");
        System.out.println("6. Purchase Products");
        System.out.println("7: Products Best Selling");
        System.out.println("0: Exit");
        System.out.println("Enter your choice: "); 
    }

    public static void main(String[] args) {
        int choice;
        do{
            menu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    CreateProduct.addProduct();
                    break;
                case 2:
                    SearchByName.searchByName();
                    break;
                case 3:
                    DeleteProduct.deleteProduct();
                    break;
                case 4:
                    ShowAllProduct.showAllProduct();
                    break;
                case 5:
                    OutOfStock.outOfStock();
                    break;
                case 6:
                    PurchaseProduct.purchaseProduct();
                    break;
                case 7:
                    BestSellingProduct.bestSellingProducts();
                case 0:
                    System.out.println("Exit");
                    break;                        
                default:
                    System.out.println("Input invalid");
                    break;
            }
        }while(choice != 0);
    }
    
}
