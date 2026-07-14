import java.util.Scanner;

public class ProductArraySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int totalTransactions = 0;
        int totalRevenue = 0;

        while (true) {
            displayMenu();
            int choice = getValidChoice(sc);

            if (choice == 7) {
                displaySummary(totalTransactions, totalRevenue);
                break;
            }

            switch (choice) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    queryProduct(sc, names, prices, stocks);
                    break;
                case 3:
                    int purchaseRevenue = purchaseProduct(sc, names, prices, stocks);
                    if (purchaseRevenue > 0) {
                        totalTransactions++;
                        totalRevenue += purchaseRevenue;
                    }
                    break;
                case 4:
                    restockProduct(sc, names, stocks);
                    break;
                case 5:
                    displayLowStockProducts(names, prices, stocks);
                    break;
                case 6:
                    displayTotalStockValue(names, prices, stocks);
                    break;
            }
            System.out.println();
        }
        sc.close();
    }

    public static void displayMenu() {
        System.out.println("=== 商品管理系統選單 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (庫存 < 10)");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束並顯示操作摘要");
        System.out.print("請輸入選項 (1-7)：");
    }

    public static int getValidChoice(Scanner sc) {
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice >= 1 && choice <= 7) {
                    return choice;
                }
            } else {
                sc.next();
            }
            System.out.print("輸入錯誤，請輸入 1 到 7 之間的整數：");
        }
    }

    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 商品列表 ---");
        System.out.printf("%-6s %-15s %-10s %-10s\n", "編號", "商品名稱", "單價", "庫存量");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("[ %d ]  %-15s %-10d %-10d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("\n請輸入欲查詢的商品編號 (1-" + names.length + ")：");
        int index = getValidProductIndex(sc, names.length);
        System.out.println("\n--- 商品詳細資訊 ---");
        System.out.println("商品名稱：" + names[index]);
        System.out.println("商品單價：" + prices[index] + " 元");
        System.out.println("目前庫存：" + stocks[index] + " 個");
    }

    public static int purchaseProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("\n請輸入欲購買的商品編號 (1-" + names.length + ")：");
        int index = getValidProductIndex(sc, names.length);

        if (stocks[index] == 0) {
            System.out.println("錯誤：該商品目前已無庫存，無法購買！");
            return 0;
        }

        System.out.print("請輸入欲購買數量 (目前庫存 " + stocks[index] + " 個)：");
        while (true) {
            if (sc.hasNextInt()) {
                int quantity = sc.nextInt();
                if (quantity > 0 && quantity <= stocks[index]) {
                    stocks[index] -= quantity;
                    int cost = prices[index] * quantity;
                    System.out.println("成功購買 " + names[index] + " 共 " + quantity + " 個，總共 " + cost + " 元。");
                    return cost;
                } else if (quantity <= 0) {
                    System.out.print("錯誤：購買數量必須大於 0，請重新輸入：");
                } else {
                    System.out.print("錯誤：庫存不足，請重新輸入：");
                }
            } else {
                System.out.print("錯誤：請輸入有效的整數數量：");
                sc.next();
            }
        }
    }

    public static void restockProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.print("\n請輸入欲補貨的商品編號 (1-" + names.length + ")：");
        int index = getValidProductIndex(sc, names.length);

        System.out.print("請輸入要補貨的數量：");
        while (true) {
            if (sc.hasNextInt()) {
                int quantity = sc.nextInt();
                if (quantity > 0) {
                    stocks[index] += quantity;
                    System.out.println("成功補貨！" + names[index] + " 目前新庫存為：" + stocks[index] + " 個。");
                    break;
                } else {
                    System.out.print("錯誤：補貨數量必須大於 0，請重新輸入：");
                }
            } else {
                System.out.print("錯誤：請輸入有效的整數數量：");
                sc.next();
            }
        }
    }

    public static void displayLowStockProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 低庫存商品警告 (庫存 < 10) ---");
        System.out.printf("%-6s %-15s %-10s %-10s\n", "編號", "商品名稱", "單價", "庫存量");
        boolean hasLowStock = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("[ %d ]  %-15s %-10d %-10d\n", (i + 1), names[i], prices[i], stocks[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("（目前沒有任何商品的庫存低於 10 個）");
        }
    }

    public static void displayTotalStockValue(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 全部庫存總價值 ---");
        int grandTotal = 0;
        for (int i = 0; i < names.length; i++) {
            int value = prices[i] * stocks[i];
            System.out.printf("%-15s : %d 元 * %d 個 = %d 元\n", names[i], prices[i], stocks[i], value);
            grandTotal += value;
        }
        System.out.println("----------------------------------------");
        System.out.println("目前庫存總價值為：" + grandTotal + " 元");
    }

    public static void displaySummary(int transactions, int revenue) {
        System.out.println("\n========================================");
        System.out.println("系統結束，感謝使用！以下為本次操作摘要：");
        System.out.println("1. 本次交易成功筆數：" + transactions + " 筆");
        System.out.println("2. 本次累計交易金額：" + revenue + " 元");
        System.out.println("========================================");
    }

    public static int getValidProductIndex(Scanner sc, int totalProducts) {
        while (true) {
            if (sc.hasNextInt()) {
                int id = sc.nextInt();
                if (id >= 1 && id <= totalProducts) {
                    return id - 1;
                }
            } else {
                sc.next();
            }
            System.out.print("錯誤：無此商品。請重新輸入編號 (1-" + totalProducts + ")：");
        }
    }
}
