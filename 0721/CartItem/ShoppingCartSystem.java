package CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartSystem {

    private static List<CartItem> cart = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addItem();
                    break;
                case "2":
                    updateQuantity();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    listCartAndTotal();
                    break;
                case "5":
                    clearCart();
                    break;
                case "6":
                    System.out.println("系統已結束，謝謝使用！");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效的選項，請重新選擇！");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== 購物車管理系統 ===");
        System.out.println("1. 加入商品 / 增加數量");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 檢視購物車與計算總額");
        System.out.println("5. 清空購物車");
        System.out.println("6. 離開系統");
        System.out.println("======================");
    }

    private static void addItem() {
        System.out.print("請輸入商品代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("【錯誤】商品代碼不可為空白！");
            return;
        }

        CartItem existingItem = findItemById(id);

        if (existingItem != null) {
            System.out.println("【提示】購物車已有此商品（" + existingItem.getName() + "，目前數量: " + existingItem.getQuantity() + "）。");
            int addQty = readPositiveInt("請輸入要累加的數量: ");
            if (addQty <= 0) return;

            existingItem.setQuantity(existingItem.getQuantity() + addQty);
            System.out.println("【成功】已累加數量，目前 「" + existingItem.getName() + "」 數量為: " + existingItem.getQuantity());
        } else {
            System.out.print("請輸入商品名稱: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("【錯誤】商品名稱不可為空白！");
                return;
            }

            int price = readPositiveInt("請輸入商品單價: ");
            if (price <= 0) return;

            int quantity = readPositiveInt("請輸入購買數量: ");
            if (quantity <= 0) return;

            cart.add(new CartItem(id, name, price, quantity));
            System.out.println("【成功】已新增商品 「" + name + "」 至購物車！");
        }
    }

    private static void updateQuantity() {
        System.out.print("請輸入要修改數量的商品代碼: ");
        String id = scanner.nextLine().trim();

        CartItem item = findItemById(id);

        if (item == null) {
            System.out.println("【找不到】購物車中無此商品代碼: " + id);
            return;
        }

        System.out.println("當前商品資訊: " + item);
        System.out.print("請輸入新的數量: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("【錯誤】數量必須為有效整數！");
            scanner.nextLine();
            return;
        }

        int newQty = scanner.nextInt();
        scanner.nextLine();

        if (newQty <= 0) {
            System.out.println("【錯誤】數量必須大於 0，不接受更新為 <= 0 的數值！");
            return;
        }

        item.setQuantity(newQty);
        System.out.println("【成功】已將 「" + item.getName() + "」 的數量更新為: " + newQty);
    }

    private static void removeItem() {
        System.out.print("請輸入要移除的商品代碼: ");
        String id = scanner.nextLine().trim();

        CartItem item = findItemById(id);

        if (item != null) {
            cart.remove(item);
            System.out.println("【成功】已將商品 「" + item.getName() + "」 從購物車移除！");
        } else {
            System.out.println("【找不到】購物車中無此商品代碼: " + id);
        }
    }

    private static void listCartAndTotal() {
        if (cart.isEmpty()) {
            System.out.println("【提示】購物車目前是空的。");
            return;
        }

        System.out.println("=== 購物車清單 ===");
        int grandTotal = 0;
        for (CartItem item : cart) {
            System.out.println(item);
            grandTotal += item.getTotalPrice();
        }
        System.out.println("------------------");
        System.out.println("【購物車總額】: $" + grandTotal);
    }

    private static void clearCart() {
        if (cart.isEmpty()) {
            System.out.println("【提示】購物車本身已是空的。");
            return;
        }
        cart.clear();
        System.out.println("【成功】購物車已清空！");
    }

    private static CartItem findItemById(String id) {
        if (id.isEmpty()) {
            return null;
        }
        for (CartItem item : cart) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    private static int readPositiveInt(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextInt()) {
            System.out.println("【錯誤】請輸入有效的整數數字！");
            scanner.nextLine();
            return -1;
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        if (val <= 0) {
            System.out.println("【錯誤】數值必須大於 0！");
            return -1;
        }
        return val;
    }
}