public class RectangleDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. 初始化與邊界條件防護 ===");
        Rectangle r1 = new Rectangle(5.0, 5.0);
        Rectangle r2 = new Rectangle(12.5, 4.0);
        Rectangle rInvalid = new Rectangle(-3.0, 8.0);

        System.out.println("r1 -> " + r1);
        System.out.println("r2 -> " + r2);
        System.out.println("rInvalid (含負數初始化防呆) -> " + rInvalid);
        System.out.println();

        System.out.println("=== 2. 矩形運算與型態分析 ===");
        displayRectangleInfo("矩形 1", r1);
        displayRectangleInfo("矩形 2", r2);
        displayRectangleInfo("無效矩形", rInvalid);

        System.out.println("=== 3. 驗證正數 Setter 防護 ===");
        System.out.println("嘗試將 r2 的寬度修改為 -5.0：");
        boolean setWidthResult = r2.setWidth(-5.0);
        System.out.println("修改結果: " + (setWidthResult ? "成功" : "失敗 (已被攔截)"));
        System.out.println("當前 r2 狀態: " + r2);
        System.out.println();

        System.out.println("嘗試將 r2 的高度修改為 12.5 (使其變成正方形)：");
        boolean setHeightResult = r2.setHeight(12.5);
        System.out.println("修改結果: " + (setHeightResult ? "成功" : "失敗"));
        displayRectangleInfo("修改後的 r2", r2);
    }

    private static void displayRectangleInfo(String name, Rectangle r) {
        System.out.println("[" + name + " 資訊]");
        System.out.println("  " + r);
        System.out.printf("  面積: %.2f\n", r.calculateArea());
        System.out.printf("  周長: %.2f\n", r.calculatePerimeter());
        System.out.println("  是否為正方形: " + (r.isSquare() ? "是" : "否"));
        System.out.println();
    }
}