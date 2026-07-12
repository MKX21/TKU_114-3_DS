import java.util.Scanner;

public class GradeStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        int total = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int passCount = 0;
        int failCount = 0;

        while (true) {
            System.out.print("請輸入成績（輸入 -1 結束）：");
            if (sc.hasNextInt()) {
                int score = sc.nextInt();

                if (score == -1) {
                    break;
                }

                if (!isValidScore(score)) {
                    continue;
                }

                count++;
                total += score;

                if (score > max) {
                    max = score;
                }
                if (score < min) {
                    min = score;
                }

                if (isPassing(score)) {
                    passCount++;
                } else {
                    failCount++;
                }
            } else {
                sc.next();
            }
        }

        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            double average = (double) total / count;
            printSummary(count, total, average, max, min, passCount, failCount);
        }

        sc.close();
    }

    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    public static boolean isPassing(int score) {
        return score >= 60;
    }

    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void printSummary(int count, int total, double average, int max, int min, int passCount, int failCount) {
        System.out.println("\n=== Grade Summary ===");
        System.out.println("Count: " + count);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        System.out.println("Average grade: " + getGrade(average));
    }
}
