import java.util.Scanner;

public class ProductSearchSystem {

    private static final String[] products = {
        "Apple iPhone 17 Pro Max",
        "Samsung Galaxy S24 Ultra",
        "Sony WH-1000XM5 Headphones",
        "Apple MacBook Air M3",
        "Nintendo Switch OLED",
        "Sony PlayStation 5 Slim"
    };

    private static final int[] prices = {29900, 43900, 11900, 35900, 10480, 15980};
    private static final int[] stocks = {15, 8, 24, 12, 30, 5};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getValidMenuChoice(scanner);

            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    exactSearch(scanner);
                    break;
                case 3:
                    partialSearch(scanner);
                    break;
                case 4:
                    showLongestProduct();
                    break;
                case 5:
                    showFirstOccurrenceIndex(scanner);
                    break;
                case 6:
                    System.out.println("系統已結束，謝謝使用！");
                    running = false;
                    break;
                default:
                    System.out.println("無效的選項，請重新選擇。");
            }
            System.out.println();
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("=== 商品名稱搜尋系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
        System.out.println("3. 部分名稱搜尋 (可顯示多筆結果)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品名稱與搜尋關鍵字第一次出現的位置");
        System.out.println("6. 結束");
        System.out.print("請選擇功能 (1-6): ");
    }

    public static int getValidMenuChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("錯誤：請輸入有效數字！");
            System.out.print("請選擇功能 (1-6): ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void showAllProducts() {
        System.out.println("\n--- 商品列表 ---");
        System.out.printf("%-4s %-30s %-10s %-6s\n", "編號", "商品名稱", "價格", "庫存");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("[%2d] %-30s NT$ %-7d %-6d\n", (i + 1), products[i], prices[i], stocks[i]);
        }
    }

    public static void exactSearch(Scanner scanner) {
        System.out.print("請輸入要精準搜尋的商品完整名稱: ");
        scanner.nextLine(); 
        String input = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (int i = 0; i < products.length; i++) {
            if (products[i].trim().toLowerCase().equals(input)) {
                System.out.printf("找到商品！\n編號: %d | 名稱: %s | 價格: NT$ %d | 庫存: %d\n", 
                                  (i + 1), products[i], prices[i], stocks[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("查無此商品，請確認名稱是否正確。");
        }
    }

    public static void partialSearch(Scanner scanner) {
        System.out.print("請輸入搜尋關鍵字 (部分名稱): ");
        scanner.nextLine(); 
        String input = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        int matchCount = 0;
        System.out.println("\n--- 搜尋結果 ---");
        for (int i = 0; i < products.length; i++) {
            if (products[i].toLowerCase().contains(input)) {
                System.out.printf("[%2d] %-30s NT$ %-7d %-6d\n", (i + 1), products[i], prices[i], stocks[i]);
                found = true;
                matchCount++;
            }
        }
        if (!found) {
            System.out.println("未找到任何含有該關鍵字的商品。");
        } else {
            System.out.println("共找到 " + matchCount + " 筆相符的商品。");
        }
    }

    public static void showLongestProduct() {
        if (products.length == 0) {
            System.out.println("目前系統中無商品。");
            return;
        }
        int longestIndex = 0;
        for (int i = 1; i < products.length; i++) {
            if (products[i].length() > products[longestIndex].length()) {
                longestIndex = i;
            }
        }
        System.out.println("\n名稱最長的商品：");
        System.out.printf("名稱: %s (字元數: %d) | 價格: NT$ %d | 庫存: %d\n", 
                          products[longestIndex], products[longestIndex].length(), prices[longestIndex], stocks[longestIndex]);
    }

    public static void showFirstOccurrenceIndex(Scanner scanner) {
        System.out.print("請輸入要比對的字元或關鍵字: ");
        scanner.nextLine(); 
        String input = scanner.nextLine();

        System.out.println("\n--- 第一次出現位置分析 (區分大小寫) ---");
        for (int i = 0; i < products.length; i++) {
            int index = products[i].indexOf(input);
            if (index != -1) {
                System.out.printf("商品: %-30s | 關鍵字 \"%s\" 首次出現在索引位置: %d\n", products[i], input, index);
            } else {
                System.out.printf("商品: %-30s | 未包含關鍵字 \"%s\"\n", products[i], input);
            }
        }
    }
}
