import java.util.Scanner;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int totalAmount = 0;
        int totalQuantity = 0;
        int option = -1;
        
        while (option != 0) {
            System.out.println("=== Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入商品選項：");
            option = sc.nextInt();
            
            int price = 0;
            String itemName = "";
            
            switch (option) {
                case 1:
                    price = 30;
                    itemName = "Black tea";
                    break;
                case 2:
                    price = 35;
                    itemName = "Green tea";
                    break;
                case 3:
                    price = 50;
                    itemName = "Coffee";
                    break;
                case 0:
                    break;
                default:
                    System.out.println("無此品項，請重新選擇。");
                    continue;
            }
            
            if (option != 0) {
                System.out.print("請輸入數量：");
                int quantity = sc.nextInt();
                
                while (quantity <= 0) {
                    System.out.print("數量不合法，請重新輸入：");
                    quantity = sc.nextInt();
                }
                
                int subtotal = price * quantity;
                
                totalAmount += subtotal;
                totalQuantity += quantity;
                
                System.out.println(itemName + " x " + quantity + ", 小計: " + subtotal);
                System.out.println();
            }
        }
        
        System.out.println("=== 結帳總計 ===");
        System.out.println("總數量: " + totalQuantity);
        System.out.println("總金額: " + totalAmount);
        
        sc.close();
    }
}
