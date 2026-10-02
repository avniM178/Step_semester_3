import java.util.*;

public class Problem5Main {

    public static void main(String[] args) {

        Customer customerX = new Customer("Customer X");

        Product productA =
                new Product("Product A", 100);

        Product productB =
                new Product("Product B", 50);

        Order orderX =
                new Order(customerX);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        PaymentMethod creditCard =
                new CreditCardPayment("1234");

        orderX.pay(creditCard);


        // Empty order

        Customer customerY =
                new Customer("Customer Y");

        Order orderY =
                new Order(customerY);

        orderY.pay(creditCard);


        // PayPal failure

        Customer customerZ =
                new Customer("Customer Z");

        Product productC =
                new Product("Product C", 200);

        Order orderZ =
                new Order(customerZ);

        orderZ.addProduct(productC, 1);

        PaymentMethod paypal =
                new PayPalPayment("customer@example.com");

        orderZ.pay(paypal);
    }
}


// ---------------- Customer ----------------

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;

        System.out.println(
                "Order customer: " + name
        );
    }

    public String getName() {
        return name;
    }
}


// ---------------- Product ----------------

class Product {

    private String name;
    private double price;

    public Product(
            String name,
            double price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}


// ---------------- Order Item ----------------

class OrderItem {

    private Product product;
    private int quantity;

    public OrderItem(
            Product product,
            int quantity) {

        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}


// ---------------- Order ----------------

class Order {

    private static int orderCounter = 0;

    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(Customer customer) {

        orderCounter++;

        this.orderId = "ORD-" + orderCounter;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;

        System.out.println(
                "Order created for " +
                customer.getName() +
                "."
        );
    }

    public void addProduct(
            Product product,
            int quantity) {

        if (quantity <= 0) {
            return;
        }

        items.add(
                new OrderItem(product, quantity)
        );
    }

    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public void pay(PaymentMethod paymentMethod) {

        if (items.isEmpty()) {

            System.out.println(
                    "Cannot process payment for an empty order."
            );

            return;
        }

        System.out.println(
                "Payment initiated via " +
                paymentMethod.getName() +
                " for " +
                orderId +
                "."
        );

        boolean success =
                paymentMethod.processPayment(
                        calculateTotal()
                );

        if (success) {

            status = OrderStatus.PAID;

            System.out.println(
                    "Payment for " +
                    orderId +
                    " successful."
            );

        } else {

            System.out.println(
                    "Payment for " +
                    orderId +
                    " failed."
            );
        }

        System.out.println(
                "Order status: " +
                status
        );
    }
}


// ---------------- Order Status ----------------

enum OrderStatus {
    PENDING,
    PAID
}


// ---------------- Payment Method ----------------

interface PaymentMethod {

    boolean processPayment(double amount);

    String getName();
}


// ---------------- Credit Card ----------------

class CreditCardPayment implements PaymentMethod {

    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {

        return true;
    }

    @Override
    public String getName() {
        return "Credit Card";
    }
}


// ---------------- PayPal ----------------

class PayPalPayment implements PaymentMethod {

    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean processPayment(double amount) {

        return false;
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}


// ---------------- Bank Transfer ----------------

class BankTransferPayment implements PaymentMethod {

    private String accountNumber;

    public BankTransferPayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean processPayment(double amount) {

        return true;
    }

    @Override
    public String getName() {
        return "Bank Transfer";
    }
}