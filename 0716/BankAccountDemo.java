public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount userA = new BankAccount("123-456-789", "王大陸", 5000);
        BankAccount userB = new BankAccount("987-654-321", "陳小明", 1000);

        System.out.println("=== 初始帳戶狀態 ===");
        System.out.println(userA);
        System.out.println(userB);
        System.out.println();

        System.out.println("=== 存款測試 ===");
        boolean depSuccess = userA.deposit(2000);
        System.out.println("王大陸 存款 NT$ 2,000 " + (depSuccess ? "成功" : "失敗"));
        boolean depFail = userA.deposit(-500);
        System.out.println("王大陸 存款 NT$ -500 " + (depFail ? "成功" : "失敗"));
        System.out.println(userA);
        System.out.println();

        System.out.println("=== 提款測試 ===");
        boolean withSuccess = userA.withdraw(1500);
        System.out.println("王大陸 提款 NT$ 1,500 " + (withSuccess ? "成功" : "失敗"));
        boolean withFail = userA.withdraw(10000);
        System.out.println("王大陸 提款 NT$ 10,000 " + (withFail ? "成功" : "失敗") + " (因餘額不足)");
        System.out.println(userA);
        System.out.println();

        System.out.println("=== 成功轉帳測試 ===");
        boolean transSuccess = userA.transferTo(userB, 3000);
        System.out.println("王大陸 轉帳給 陳小明 NT$ 3,000 " + (transSuccess ? "成功" : "失敗"));
        System.out.println(userA);
        System.out.println(userB);
        System.out.println();

        System.out.println("=== 失敗轉帳測試 (餘額不足) ===");
        boolean transFail = userA.transferTo(userB, 5000);
        System.out.println("王大陸 轉帳給 陳小明 NT$ 5,000 " + (transFail ? "成功" : "失敗") + " (餘額不足保護)");
        System.out.println(userA);
        System.out.println(userB);
        System.out.println();

        System.out.println("=== 失敗轉帳測試 (無效金額) ===");
        boolean transInvalidAmount = userA.transferTo(userB, -100);
        System.out.println("王大陸 轉帳給 陳小明 NT$ -100 " + (transInvalidAmount ? "成功" : "失敗"));
        System.out.println(userA);
        System.out.println(userB);
    }
}