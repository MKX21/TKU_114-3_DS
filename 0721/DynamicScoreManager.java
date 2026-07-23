import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> scores = new ArrayList<>();

        System.out.println("=== 課堂實作：動態成績管理 ===");
        System.out.println("請開始輸入成績 ( 0 - 100 )，輸入 -1 結束：");

        while (true) {
            System.out.print("請輸入成績：");
            
            if (!scanner.hasNextInt()) {
                System.out.println("【錯誤】請輸入有效的整數成績！");
                scanner.next();
                continue;
            }

            int input = scanner.nextInt();

            if (input == -1) {
                break;
            }

            if (input < 0 || input > 100) {
                System.out.println("【錯誤】成績必須在 0 到 100 之間，請重新輸入。");
            } else {
                scores.add(input);
            }
        }

        System.out.println("\n-----------------------------");

        if (scores.isEmpty()) {
            System.out.println("未輸入任何有效成績，程式結束。");
        } else {
            printSummary(scores);
        }

        scanner.close();
    }

    public static double getAverage(List<Integer> scores) {
        if (scores.isEmpty()) return 0.0;
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    public static int getMaxScore(List<Integer> scores) {
        return Collections.max(scores);
    }

    public static int getMinScore(List<Integer> scores) {
        return Collections.min(scores);
    }

    public static List<Integer> getPassingScores(List<Integer> scores) {
        List<Integer> passingScores = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passingScores.add(score);
            }
        }
        return passingScores;
    }

    public static void printSummary(List<Integer> scores) {
        System.out.println("【統計結果】");
        System.out.println("總筆數\t: " + scores.size() + " 筆");
        System.out.printf("平均分數\t: %.2f 分\n", getAverage(scores));
        System.out.println("最高分數\t: " + getMaxScore(scores) + " 分");
        System.out.println("最低分數\t: " + getMinScore(scores) + " 分");
        
        List<Integer> passingList = getPassingScores(scores);
        System.out.println("及格名單\t: " + passingList + " (共 " + passingList.size() + " 筆)");
    }
}