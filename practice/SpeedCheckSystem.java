import java.util.Scanner;

public class SpeedCheckSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        int totalSpeed = 0;
        int maxSpeed = Integer.MIN_VALUE;
        int overspeedCount = 0;

        while (true) {
            System.out.print("請輸入時速（輸入 -1 結束）：");
            if (sc.hasNextInt()) {
                int speed = sc.nextInt();

                if (speed == -1) {
                    break;
                }

                if (!isValidSpeed(speed)) {
                    continue;
                }

                count++;
                totalSpeed += speed;

                if (speed > maxSpeed) {
                    maxSpeed = speed;
                }

                if (isOverspeeding(speed)) {
                    overspeedCount++;
                }

                double safeDistance = calculateSafeDistance(speed);
                System.out.println("法定安全距離：" + safeDistance + " 公尺");
                System.out.println();
            } else {
                sc.next();
            }
        }

        if (count == 0) {
            System.out.println("No data entered.");
        } else {
            double averageSpeed = (double) totalSpeed / count;
            printSummary(count, totalSpeed, averageSpeed, maxSpeed, overspeedCount);
        }

        sc.close();
    }

    public static boolean isValidSpeed(int speed) {
        return speed >= 0 && speed <= 120;
    }

    public static double calculateSafeDistance(int speed) {
        return speed * 0.5;
    }

    public static boolean isOverspeeding(int speed) {
        return speed > 110;
    }

    public static void printSummary(int count, int totalSpeed, double averageSpeed, int maxSpeed, int overspeedCount) {
        System.out.println("\n=== Speed Check Summary ===");
        System.out.println("Total vehicles: " + count);
        System.out.println("Average speed: " + averageSpeed);
        System.out.println("Max speed: " + maxSpeed);
        System.out.println("Overspeeding vehicles: " + overspeedCount);
    }
}