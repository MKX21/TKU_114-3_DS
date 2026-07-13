import java.util.Scanner;

public class MovieTicketingSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int adultCount = 0;
        int studentCount = 0;
        int childElderCount = 0;
        int totalAmount = 0;

        while (true) {
            printMenu();
            System.out.print("請輸入選項：");
            if (sc.hasNextInt()) {
                int option = sc.nextInt();

                if (option == 0) {
                    break;
                }

                if (option < 1 || option > 3) {
                    continue;
                }

                int quantity = readValidQuantity(sc);
                int price = getTicketPrice(option);
                int subtotal = price * quantity;

                totalAmount += subtotal;

                switch (option) {
                    case 1:
                        adultCount += quantity;
                        break;
                    case 2:
                        studentCount += quantity;
                        break;
                    case 3:
                        childElderCount += quantity;
                        break;
                }

                System.out.println("已加入購物車：" + quantity + " 張");
                System.out.println();
            } else {
                sc.next();
            }
        }

        printReceipt(adultCount, studentCount, childElderCount, totalAmount);

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Movie Ticket Menu ===");
        System.out.println("1. 全票 (Adult)       $300");
        System.out.println("2. 學生票 (Student)    $250");
        System.out.println("3. 優待票 (Child/Elder) $150");
        System.out.println("0. 結帳 (Checkout)");
    }

    public static int getTicketPrice(int option) {
        switch (option) {
            case 1: return 300;
            case 2: return 250;
            case 3: return 150;
            default: return 0;
        }
    }

    public static int readValidQuantity(Scanner sc) {
        int quantity;
        while (true) {
            System.out.print("請輸入購買數量：");
            if (sc.hasNextInt()) {
                quantity = sc.nextInt();
                if (quantity > 0) {
                    break;
                }
            } else {
                sc.next();
            }
        }
        return quantity;
    }

    public static void printReceipt(int adultCount, int studentCount, int childElderCount, int totalAmount) {
        int totalTickets = adultCount + studentCount + childElderCount;
        int finalAmount = totalAmount;
        String hasDiscount = "No";

        if (totalAmount >= 1000) {
            finalAmount = (int) Math.round(totalAmount * 0.85);
            hasDiscount = "Yes (15% OFF)";
        }

        System.out.println("\n=== Receipt ===");
        System.out.println("Adult tickets: " + adultCount);
        System.out.println("Student tickets: " + studentCount);
        System.out.println("Child/Elder tickets: " + childElderCount);
        System.out.println("Total tickets: " + totalTickets);
        System.out.println("Original amount: " + totalAmount);
        System.out.println("Discount: " + hasDiscount);
        System.out.println("Final amount: " + finalAmount);
    }
}