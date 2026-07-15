public class BankAccount {

    private String accountNumber;
    private String ownerName;
    private int balance;

    public BankAccount(String accountNumber, String ownerName, int initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public boolean deposit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(int amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transferTo(BankAccount target, int amount) {
        if (target != null && amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            target.balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("帳號: %s | 戶名: %s | 餘額: NT$ %,d 元", 
            accountNumber, ownerName, balance);
    }
}