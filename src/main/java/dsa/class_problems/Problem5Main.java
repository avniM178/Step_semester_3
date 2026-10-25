import java.util.*;

public class Problem5Main {
    static class Payslip {
        String name, type;
        int pay;

        Payslip(String name, String type, int pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }

        @Override
        public String toString() {
            return "Payslip[name=" + name
                    + ", type=" + type
                    + ", pay=" + pay + "]";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Payslip> payslips = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();
            int pay;

            switch (type) {
                case "FullTime":
                    pay = sc.nextInt();
                    break;

                case "PartTime":
                    int hours = sc.nextInt();
                    int rate = sc.nextInt();
                    pay = hours * rate;
                    break;

                case "Intern":
                    pay = sc.nextInt();
                    break;

                default:
                    continue;
            }

            payslips.add(new Payslip(name, type, pay));
        }

        sc.close();

        int total = 0;
        Payslip topEarner = null;

        for (Payslip payslip : payslips) {
            System.out.println(payslip);
            total += payslip.pay;

            if (topEarner == null || payslip.pay > topEarner.pay) {
                topEarner = payslip;
            }
        }

        System.out.println("Total " + total);

        if (topEarner != null) {
            System.out.println("top earner " + topEarner.name);
        }
    }
}
