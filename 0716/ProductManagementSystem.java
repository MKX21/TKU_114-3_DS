import java.util.Scanner;

public class ProductManagementSystem {

    private static final Product[] products = new Product[10];
    private static int productCount = 0;

    // 紀錄操作摘要的計數器
    private static int addCount = 0;
    private static int sellCount = 0;
    private static int restockCount = 0;
    private static int priceUpdateCount = 0;

    public static void main(String[] args) {
        initializeDefaultProducts();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getValidMenuChoice(scanner);

            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    searchProductByName(scanner);
                    break;
                case 3:
                    addNewProduct(scanner);
                    break;
                case 4:
                    sellProduct(scanner);
                    break;
                case 5:
                    restockProduct(scanner);
                    break;
                case 6:
                    modifyProductPrice(scanner);
                    break;
                case 7:
                    displayLowStockProducts();
                    break;
                case 8:
                    displayTotalInventoryValue();
                    break;
                case 9:
                    displaySummaryAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("無效的選項，請重新選擇。");
            }
            System.out.println();
        }
        scanner.close();
    }

    // --- 輔助方法 1：初始化預設的 5 項商品 ---
    public static void initializeDefaultProducts() {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 4); // 故意設為 4 件以利測試低庫存
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);
        productCount = 5;
    }

    // --- 輔助方法 2：顯示功能選單 ---
    public static void printMenu() {
        System.out.println("=== 商品管理系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
        System.out.print("請選擇功能 (1-9): ");
    }

    // --- 輔助方法 3：驗證選單輸入是否為整數 ---
    public static int getValidMenuChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("錯誤：請輸入有效數字！");
            System.out.print("請選擇功能 (1-9): ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // --- 輔助方法 4：顯示全部商品表格 ---
    public static void displayAllProducts() {
        System.out.println("\n--- 目前商品清單 ---");
        System.out.printf("%-4s %-20s %-12s %-6s\n", "編號", "商品名稱", "價格", "庫存");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < productCount; i++) {
            System.out.printf("[%2d] %-20s NT$ %-8d %-6d 件\n", 
                (i + 1), products[i].getName(), products[i].getPrice(), products[i].getStock());
        }
        System.out.println("目前商品種類數: " + productCount + "/10");
    }

    // --- 輔助方法 5：依照完整名稱精準搜尋 (忽略大小寫與前後空白) ---
    public static void searchProductByName(Scanner scanner) {
        System.out.print("請輸入要搜尋的完整商品名稱: ");
        scanner.nextLine(); 
        String targetName = scanner.nextLine().trim();

        int index = findProductIndexByName(targetName);
        if (index == -1) {
            System.out.println("查無此商品，請確認名稱是否正確。");
            return;
        }

        System.out.println("【找到商品】" + products[index]);
    }

    // --- 輔助方法 6：新增商品 (含重名驗證、陣列容量驗證) ---
    public static void addNewProduct(Scanner scanner) {
        if (productCount >= products.length) {
            System.out.println("【新增失敗】系統商品儲存空間已滿 (上限 10 項)！");
            return;
        }

        System.out.print("請輸入新增商品名稱: ");
        scanner.nextLine(); 
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("【新增失敗】商品名稱不能為空空白！");
            return;
        }

        if (findProductIndexByName(name) != -1) {
            System.out.println("【新增失敗】已存在同名商品 \"" + name + "\"，不可重複新增！");
            return;
        }

        System.out.print("請輸入商品價格: ");
        int price = scanner.nextInt();
        System.out.print("請輸入商品庫存: ");
        int stock = scanner.nextInt();

        products[productCount] = new Product(name, price, stock);
        System.out.println("【新增成功】" + products[productCount]);
        productCount++;
        addCount++;
    }

    // --- 輔助方法 7：出售商品 ---
    public static void sellProduct(Scanner scanner) {
        System.out.print("請輸入要出售的商品名稱: ");
        scanner.nextLine(); 
        String name = scanner.nextLine().trim();

        int index = findProductIndexByName(name);
        if (index == -1) {
            System.out.println("【失敗】找不到該商品！");
            return;
        }

        System.out.print("請輸入出售數量: ");
        int amount = scanner.nextInt();

        if (products[index].sell(amount)) {
            System.out.println("【交易成功】已售出 " + amount + " 件 " + products[index].getName());
            System.out.println("更新後狀態 -> " + products[index]);
            sellCount++;
        } else {
            System.out.println("【交易失敗】出售數量必須大於 0 且不能超過現有庫存 (目前庫存: " + products[index].getStock() + " 件)！");
        }
    }

    // --- 輔助方法 8：補充庫存 ---
    public static void restockProduct(Scanner scanner) {
        System.out.print("請輸入要補貨的商品名稱: ");
        scanner.nextLine(); 
        String name = scanner.nextLine().trim();

        int index = findProductIndexByName(name);
        if (index == -1) {
            System.out.println("【失敗】找不到該商品！");
            return;
        }

        System.out.print("請輸入補充數量: ");
        int amount = scanner.nextInt();

        if (products[index].restock(amount)) {
            System.out.println("【補貨成功】已補貨 " + amount + " 件 " + products[index].getName());
            System.out.println("更新後狀態 -> " + products[index]);
            restockCount++;
        } else {
            System.out.println("【補貨失敗】補充數量必須大於 0 ！");
        }
    }

    // --- 輔助方法 9：修改商品價格 ---
    public static void modifyProductPrice(Scanner scanner) {
        System.out.print("請輸入要修改價格的商品名稱: ");
        scanner.nextLine(); 
        String name = scanner.nextLine().trim();

        int index = findProductIndexByName(name);
        if (index == -1) {
            System.out.println("【失敗】找不到該商品！");
            return;
        }

        System.out.print("請輸入新價格: ");
        int newPrice = scanner.nextInt();

        if (products[index].setPrice(newPrice)) {
            System.out.println("【價格修改成功】" + products[index].getName() + " 新價格為 NT$ " + products[index].getPrice() + " 元");
            priceUpdateCount++;
        } else {
            System.out.println("【修改失敗】價格必須大於 0 ！");
        }
    }

    // 核心工具方法：透過名稱尋找商品在陣列中的索引位置 (忽略大小寫、前後空白)
    private static int findProductIndexByName(String name) {
        if (name == null) return -1;
        String cleanName = name.trim().toLowerCase();
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().toLowerCase().equals(cleanName)) {
                return i;
            }
        }
        return -1;
    }

    // 顯示低庫存商品 (呼叫 Product 自身的 isLowStock 判定)
    private static void displayLowStockProducts() {
        System.out.println("\n--- 低庫存警示商品 ---");
        boolean hasLowStock = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i] + " [警告：庫存過低！]");
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前所有商品庫存皆充足。");
        }
    }

    // 顯示全部庫存總價值
    private static void displayTotalInventoryValue() {
        long totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            totalValue += products[i].getInventoryValue();
        }
        System.out.println("\n--- 庫存價值統計 ---");
        System.out.printf("目前商品總估值: NT$ %,d 元\n", totalValue);
    }

    // 結束時列印操作統計摘要
    private static void displaySummaryAndExit() {
        System.out.println("\n=========================================");
        System.out.println("         系統關閉 - 本次操作摘要         ");
        System.out.println("=========================================");
        System.out.println(" * 新增商品成功次數 : " + addCount + " 次");
        System.out.println(" * 成功出售交易次數 : " + sellCount + " 次");
        System.out.println(" * 成功補貨交易次數 : " + restockCount + " 次");
        System.out.println(" * 價格調整異動次數 : " + priceUpdateCount + " 次");
        System.out.println("=========================================");
        System.out.println("感謝使用，資料已成功保存。系統關閉。");
    }
}