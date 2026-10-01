import java.util.*;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {
    Product product;
    int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotal() {
        return product.price * quantity;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    boolean processPayment(double amount) {
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    boolean processPayment(double amount) {
        return false;
    }
}

class BankTransferPayment implements PaymentMethod {
    boolean processPayment(double amount) {
        return true;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Order {
    Customer customer;
    ArrayList<OrderItem> items = new ArrayList<>();
    String status = "Pending";

    Order(Customer customer) {
        this.customer = customer;
    }

    void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    double getTotal() {
        double total = 0;

        for (OrderItem item : items)
            total += item.getTotal();

        return total;
    }

    void pay(PaymentMethod paymentMethod, String methodName) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via " + methodName + " for Order " + customer.name + ".");

        if (paymentMethod.processPayment(getTotal())) {
            status = "Paid";
            System.out.println("Payment for Order " + customer.name + " successful.");
        } else {
            System.out.println("Payment for Order " + customer.name + " failed.");
        }

        System.out.println("Order status: " + status);
    }
}

public class PaymentProcessingSystem {
    public static void main(String[] args) {
        Product productA = new Product("Product A", 100);
        Product productB = new Product("Product B", 200);
        Product productC = new Product("Product C", 300);

        Customer customerX = new Customer("X");
        Order orderX = new Order(customerX);
        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println("Order created for Customer X.");
        orderX.pay(new CreditCardPayment(), "Credit Card");

        Customer customerY = new Customer("Y");
        Order orderY = new Order(customerY);
        System.out.println("Order created for Customer Y.");
        orderY.pay(new CreditCardPayment(), "Credit Card");

        Customer customerZ = new Customer("Z");
        Order orderZ = new Order(customerZ);
        orderZ.addProduct(productC, 1);

        System.out.println("Order created for Customer Z.");
        orderZ.pay(new PayPalPayment(), "PayPal");
    }
}
