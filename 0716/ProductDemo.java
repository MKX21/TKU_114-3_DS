public class ProductDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 初始化驗證與防呆展示 ===");
        Product p1 = new Product("Mechanical Keyboard", 2480, 10);
        Product p2 = new Product("  ", -500, -5);

        System.out.println(p1);
        System.out.println(p2 + " (空白名稱與負數值已被安全重置)");
        System.out.println();

        System.out.println("=== 2. 價格修改測試 (setPrice) ===");
        System.out.println("嘗試修改 p1 價格為 2980 元: " + (p1.setPrice(2980) ? "成功" : "失敗"));
        System.out.println("嘗試修改 p1 價格為 -100 元: " + (p1.setPrice(-100) ? "成功" : "失敗"));
        System.out.println(p1);
        System.out.println();

        System.out.println("=== 3. 進貨與出貨測試 (restock & sell) ===");
        System.out.println("p1 進貨 5 件: " + (p1.restock(5) ? "成功" : "失敗"));
        System.out.println("p1 進貨 -2 件: " + (p1.restock(-2) ? "成功" : "失敗"));
        
        System.out.println("p1 售出 12 件: " + (p1.sell(12) ? "成功" : "失敗"));
        System.out.println("p1 售出 10 件 (庫存不足): " + (p1.sell(10) ? "成功" : "失敗"));
        System.out.println(p1);
        System.out.println();

        System.out.println("=== 4. 低庫存與總價值分析 (isLowStock & getInventoryValue) ===");
        System.out.println("p1 目前庫存: " + p1.getStock() + " 件 | 是否觸發低庫存警告: " + (p1.isLowStock() ? "是" : "否"));
        System.out.println("p1 庫存總價值: NT$ " + String.format("%,d", p1.getInventoryValue()) + " 元");
        System.out.println();
        
        System.out.println("將 p1 庫存消耗至 2 件...");
        p1.sell(1);
        System.out.println("p1 目前庫存: " + p1.getStock() + " 件 | 是否觸發低庫存警告: " + (p1.isLowStock() ? "是" : "否"));
    }
}
