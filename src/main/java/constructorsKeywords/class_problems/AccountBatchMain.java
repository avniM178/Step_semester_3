class FeeAccount {

    public void processPayment(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {

    @Override
    public void processPayment(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

class PaymentProcessor {

    private int hostelCount = 0;
    private int dayScholarCount = 0;

    public void processPayment(FeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {

            account.processPayment(amount);
            hostelCount++;

        } else {

            account.processPayment(amount);
            dayScholarCount++;
        }
    }

    public void printCounts() {
        System.out.println(
            "Hostel accounts processed: " + hostelCount
            + " | Day-scholar accounts processed: " + dayScholarCount
        );
    }
}

public class AccountBatchMain {

    public static void main(String[] args) {

        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double amount = 60000;

        PaymentProcessor processor = new PaymentProcessor();

        for (FeeAccount account : accounts) {
            processor.processPayment(account, amount);
        }

        processor.printCounts();
    }
}