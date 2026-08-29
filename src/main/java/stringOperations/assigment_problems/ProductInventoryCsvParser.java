package stringOperations.assigment_problems;

import java.util.Scanner;

public class ProductInventoryCsvParser {

    public static void parseInventoryRecord(String csvLine) {

        String[] data = csvLine.split(",");

        if (data.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + data[0]);
        System.out.println("SKU: " + data[1]);
        System.out.println("Qty: " + data[2]);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product record: ");
        String csvLine = scanner.nextLine();

        parseInventoryRecord(csvLine);

        scanner.close();
    }
}