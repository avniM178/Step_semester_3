class StudentAccount {

    private String regNo;
    private double totalFees;

    public StudentAccount(String regNo, double totalFees) {
        this.regNo = regNo;
        this.totalFees = totalFees;
    }

    public final double calculateLateFee(int daysLate) {
        return totalFees * 0.01 * daysLate;
    }

    public final void printSummary(int daysLate) {

        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            double lateFee = calculateLateFee(daysLate);

            System.out.println(
                regNo + " | Total Fee: Rs " + totalFees
                + " | Late Fee: Rs " + lateFee
            );
        }
    }
}

public class LateFeeMain {

    public static void main(String[] args) {

        String[] regNos = {
            "RA001",
            "RA002",
            "RA003",
            "RA004"
        };

        double[] totalFees = {
            200000,
            150000,
            180000,
            220000
        };

        int[] daysLate = {
            10,
            0,
            -2,
            5
        };

        for (int i = 0; i < regNos.length; i++) {

            StudentAccount account =
                new StudentAccount(regNos[i], totalFees[i]);

            account.printSummary(daysLate[i]);
        }
    }
}