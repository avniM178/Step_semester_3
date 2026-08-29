package oopsFundamentals.class_problems;
class MessWallet {

    private double balance;

    public MessWallet(double balance) {

        if (balance < 0) {
            System.out.println("Opening balance cannot be negative.");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void topUp(double amount) {

        if (amount <= 0) {
            System.out.println("Top-up rejected");
        } else {
            balance = balance + amount;
            System.out.println("Balance after top-up: " + balance);
        }
    }

    public void deduct(double amount) {

        if (amount > balance) {
            System.out.println("Deduct rejected: insufficient balance");
        } else if (amount <= 0) {
            System.out.println("Invalid amount");
        } else {
            balance = balance - amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class MessWalletMain {

    public static void main(String[] args) {

        MessWallet wallet = new MessWallet(500);

        wallet.topUp(200);
        wallet.deduct(1000);

        System.out.println("Final balance: " + wallet.getBalance());
    }
}