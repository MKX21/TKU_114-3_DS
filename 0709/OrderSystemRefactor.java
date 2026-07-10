import java.util.Scanner;

public class OrderSystemRefactor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int totalAmount = 0;
        int totalQuantity = 0;
        int option = -1;
        
        while (option != 0) {
            printMenu();
            System.out.print("請輸入商品選項：");
            option = sc.nextInt();
            
            if (option == 0) {
                break;
            }
            
            int price = getPrice(option);
            
            if (price == 0) {
                System.out.println("無此品項，請重新選擇。");
                System.out.println();
                continue;
            }
            
            String itemName = getItemName(option);
            int quantity = readValidQuantity(sc);
            int subtotal = calculateSubtotal(price, quantity);
            
            totalAmount += subtotal;
            totalQuantity += quantity;
            
            System.out.println(itemName + " x " + quantity + ", 小計: " + subtotal);
            System.out.println();
        }
        
        printReceipt(totalQuantity, totalAmount);
        
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return 0;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Coffee";
            default: return "";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();
        
        while (quantity <= 0) {
            System.out.print("數量不合法，請重新輸入：");
            quantity = sc.nextInt();
        }
        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("=== 結帳總計 ===");
        System.out.println("總數量: " + totalItems);
        System.out.println("總金額: " + totalAmount);
    }
}
