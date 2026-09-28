public class Problem2Main {

    public static void main(String[] args) {

        PackageBox p = new PackageBox("TRK-88");
        Invoice i = new Invoice("INV-42");

        System.out.println(p.printLabel());
        System.out.println(i.printLabel());

        Printable[] items = {p, i};

        Printable.printAll(items);
    }
}

interface Printable {

    String printLabel();

    static void printAll(Printable[] items) {

        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }
}

class PackageBox implements Printable {

    private String trackingId;

    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class Invoice implements Printable {

    private String invoiceNumber;

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}