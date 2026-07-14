import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        
        System.out.println("\n--- 銷售數據表格 ---");
        displayMatrix(sales);
        
        System.out.println("\n--- 統計分析結果 ---");
        displayStatistics(sales);

        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] sales) {
        System.out.println("請開始輸入各項商品的銷售量 (3 項商品，各 4 天)：");
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    System.out.print("請輸入商品 " + (i + 1) + " 在第 " + (j + 1) + " 天的銷售量：");
                    if (sc.hasNextInt()) {
                        int value = sc.nextInt();
                        if (value >= 0) {
                            sales[i][j] = value;
                            break;
                        } else {
                            System.out.println("錯誤：銷售量不得小於 0，請重新輸入！");
                        }
                    } else {
                        System.out.println("錯誤：請輸入有效的整數！");
                        sc.next();
                    }
                }
            }
        }
    }

    public static void displayMatrix(int[][] sales) {
        System.out.println("商品\\天數\t第 1 天\t第 2 天\t第 3 天\t第 4 天");
        for (int i = 0; i < sales.length; i++) {
            System.out.print("商品 " + (i + 1) + "\t");
            for (int j = 0; j < sales[i].length; j++) {
                System.out.print(sales[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void displayStatistics(int[][] sales) {
        int[] productTotals = new int[sales.length];
        int maxProductTotal = -1;
        int maxProductIndex = -1;

        for (int i = 0; i < sales.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < sales[i].length; j++) {
                rowSum += sales[i][j];
            }
            productTotals[i] = rowSum;
            System.out.println("商品 " + (i + 1) + " 的總銷售量：" + rowSum);

            if (rowSum > maxProductTotal) {
                maxProductTotal = rowSum;
                maxProductIndex = i;
            }
        }

        System.out.println();

        int numDays = sales[0].length;
        for (int j = 0; j < numDays; j++) {
            int colSum = 0;
            for (int i = 0; i < sales.length; i++) {
                colSum += sales[i][j];
            }
            System.out.println("第 " + (j + 1) + " 天全部商品的總銷售量：" + colSum);
        }

        System.out.println();
        if (maxProductIndex != -1) {
            System.out.println("總銷售量最高的商品是：商品 " + (maxProductIndex + 1) + " (總銷售量：" + maxProductTotal + ")");
        }
    }
}
