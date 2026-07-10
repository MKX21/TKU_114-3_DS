import java.util.Scanner;

public class AtmMethodHomework {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("請選擇操作項目：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    break;

                case 3:
                    System.out.print("請輸入提款金額：");
                    int withdrawAmount = sc.nextInt();
                    while (withdrawAmount <= 0 || withdrawAmount > balance) {
                        if (withdrawAmount <= 0) {
                            System.out.print("提款金額必須大於 0，請重新輸入：");
                        } else {
                            System.out.print("餘額不足（目前餘額 " + balance + " 元），請重新輸入：");
                        }
                        withdrawAmount = sc.nextInt();
                    }
                    balance = withdraw(balance, withdrawAmount);
                    break;

                case 0:
                    System.out.println("感謝您的使用，再見！");
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1 : 查詢餘額");
        System.out.println("2 : 存款");
        System.out.println("3 : 提款");
        System.out.println("0 : 離開");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        System.out.print(message);
        int amount = sc.nextInt();
        while (amount <= 0) {
            System.out.print("存款金額必須大於 0，請重新輸入：");
            amount = sc.nextInt();
        }
        return amount;
    }

    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("存款成功！已存入 " + amount + " 元，目前餘額：" + balance + " 元");
        return balance;
    }

    public static int withdraw(int balance, int amount) {
        balance -= amount;
        System.out.println("提款成功！已領取 " + amount + " 元，目前餘額：" + balance + " 元");
        return balance;
    }

    public static void printBalance(int balance) {
        System.out.println("目前餘額為：" + balance + " 元");
    }
}
