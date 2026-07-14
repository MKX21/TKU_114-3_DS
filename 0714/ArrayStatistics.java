import java.util.Scanner;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 歡迎使用成績統計系統 ===");

        int count = readCount(sc);
        int[] scores = new int[count];

        inputScores(sc, scores);

        System.out.println("\n--- 統計結果 ---");
        System.out.print("所有學生成績為：");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + (i == scores.length - 1 ? "" : ", "));
        }
        System.out.println();

        int total = calculateTotal(scores);
        double average = (double) total / count;
        int max = findMax(scores);
        int min = findMin(scores);

        System.out.println("總分：" + total + " 分");
        System.out.printf("平均：%.2f 分\n", average);
        System.out.println("最高分：" + max + " 分");
        System.out.println("最低分：" + min + " 分");

        int passCount = countPass(scores);
        int failCount = count - passCount;
        System.out.println("及格人數：" + passCount + " 人");
        System.out.println("不及格人數：" + failCount + " 人");

        System.out.print("\n請輸入要搜尋的目標成績：");
        while (!sc.hasNextInt()) {
            System.out.print("請輸入有效的整數成績：");
            sc.next();
        }
        int target = sc.nextInt();

        int index = findIndex(scores, target);
        if (index != -1) {
            System.out.println("成績 " + target + " 第一次出現的索引位置（Index）為：" + index);
        } else {
            System.out.println("找不到成績 " + target + " 的資料。");
        }

        System.out.println("\n系統結束，謝謝使用！");
        sc.close();
    }

    public static int readCount(Scanner sc) {
        int count = 0;
        while (true) {
            System.out.print("請輸入資料筆數 (範圍 1 ~ 50)：");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                if (count >= 1 && count <= 50) {
                    break;
                } else {
                    System.out.println("錯誤：筆數超出範圍，請重新輸入。");
                }
            } else {
                System.out.println("錯誤：請輸入整數。");
                sc.next();
            }
        }
        return count;
    }

    public static void inputScores(Scanner sc, int[] scores) {
        System.out.println("\n請開始輸入學生成績 (範圍 0 ~ 100)：");
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績：");
                if (sc.hasNextInt()) {
                    int score = sc.nextInt();
                    if (score >= 0 && score <= 100) {
                        scores[i] = score;
                        break;
                    } else {
                        System.out.println("錯誤：成績必須介於 0 到 100 之間！");
                    }
                } else {
                    System.out.println("錯誤：請輸入整數成績。");
                    sc.next();
                }
            }
        }
    }

    public static int calculateTotal(int[] scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum;
    }

    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    public static int countPass(int[] scores) {
        int passCount = 0;
        for (int score : scores) {
            if (score >= 60) {
                passCount++;
            }
        }
        return passCount;
    }

    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
