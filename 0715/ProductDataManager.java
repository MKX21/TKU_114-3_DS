import java.util.ArrayList;
import java.util.Scanner;

public class ProductDataManager {

    private static final ArrayList<String> productNames = new ArrayList<>();
    private static final ArrayList<Integer> productPrices = new ArrayList<>();
    private static final ArrayList<Integer> productStocks = new ArrayList<>();

    public static void main(String[] args) {
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        initializeData(records);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getValidMenuChoice(scanner);

            switch (choice) {
                case 1:
                    displayProductTable();
                    break;
                case 2:
                    searchProduct(scanner);
                    break;
                case 3:
                    displayLowStockProducts(10);
                    break;
                case 4:
                    displayTotalInventoryValue();
                    break;
                case 5:
                    addNewRecordInteractive(scanner);
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
        System.out.println("=== 商品資料管理系統 ===");
        System.out.println("1. 顯示解析後的商品表格");
        System.out.println("2. 商品搜尋 (支援完整或部分名稱)");
        System.out.println("3. 顯示低庫存商品 (低於 10 件)");
        System.out.println("4. 顯示庫存總價值");
        System.out.println("5. 輸入並驗證新增一筆商品資料");
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

    public static void initializeData(String[] records) {
        for (String record : records) {
            try {
                parseAndAddRecord(record);
            } catch (Exception e) {
                System.out.println("初始資料載入錯誤: " + e.getMessage());
            }
        }
    }

    public static void parseAndAddRecord(String record) throws IllegalArgumentException {
        if (record == null || record.trim().isEmpty()) {
            throw new IllegalArgumentException("資料列不能為空");
        }

        String[] parts = record.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("格式不符，欄位數量應為 3 個 (目前有 " + parts.length + " 個)");
        }

        String name = parts[0].trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("商品名稱不能為空");
        }

        int price;
        try {
            price = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("價格轉換失敗，必須為有效整數");
        }

        if (price < 0) {
            throw new IllegalArgumentException("價格不能為負數 (" + price + ")");
        }

        int stock;
        try {
            stock = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("庫存轉換失敗，必須為有效整數");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("庫存不能為負數 (" + stock + ")");
        }

        productNames.add(name);
        productPrices.add(price);
        productStocks.add(stock);
    }

    public static void displayProductTable() {
        System.out.println("\n--- 商品資料表格 ---");
        System.out.printf("%-4s %-20s %-10s %-6s\n", "編號", "商品名稱", "價格 (元)", "庫存 (件)");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < productNames.size(); i++) {
            System.out.printf("[%2d] %-20s NT$ %-7d %-6d\n", 
                (i + 1), productNames.get(i), productPrices.get(i), productStocks.get(i));
        }
    }

    public static void searchProduct(Scanner scanner) {
        System.out.print("請輸入搜尋關鍵字 (支援完整或部分搜尋): ");
        scanner.nextLine();
        String query = scanner.nextLine().trim().toLowerCase();

        if (query.isEmpty()) {
            System.out.println("關鍵字不能為空。");
            return;
        }

        boolean found = false;
        System.out.println("\n--- 搜尋結果 ---");
        for (int i = 0; i < productNames.size(); i++) {
            String nameLower = productNames.get(i).toLowerCase();
            if (nameLower.equals(query) || nameLower.contains(query)) {
                System.out.printf("[%2d] %-20s NT$ %-7d %-6d\n", 
                    (i + 1), productNames.get(i), productPrices.get(i), productStocks.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("未找到任何相符的商品。");
        }
    }

    public static void displayLowStockProducts(int threshold) {
        System.out.println("\n--- 低庫存警告商品列表 (低於 " + threshold + " 件) ---");
        boolean hasLowStock = false;
        for (int i = 0; i < productNames.size(); i++) {
            if (productStocks.get(i) < threshold) {
                System.out.printf("商品: %-20s | 目前庫存: %d 件\n", productNames.get(i), productStocks.get(i));
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("所有商品庫存皆充足。");
        }
    }

    public static void displayTotalInventoryValue() {
        long totalValue = 0;
        for (int i = 0; i < productNames.size(); i++) {
            totalValue += (long) productPrices.get(i) * productStocks.get(i);
        }
        System.out.println("\n--- 庫存價值分析 ---");
        System.out.printf("系統商品總類數: %d 類\n", productNames.size());
        System.out.printf("目前庫存總價值: NT$ %,d 元\n", totalValue);
    }

    public static void addNewRecordInteractive(Scanner scanner) {
        System.out.println("\n請輸入新增商品資料，格式為 '名稱,價格,庫存' (例如: Webcam,1500,15)");
        System.out.print("輸入資料: ");
        scanner.nextLine();
        String record = scanner.nextLine();

        try {
            parseAndAddRecord(record);
            System.out.println("【成功】商品資料已成功解析並匯入系統！");
        } catch (IllegalArgumentException e) {
            System.out.println("【格式或數值錯誤】" + e.getMessage());
            System.out.println("程式未中斷，請重新嘗試。");
        }
    }
}
