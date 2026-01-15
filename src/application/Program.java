package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entity.Client;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import entity.enums.OrderStatus;

public class Program {

    public static void main(String[] args) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner scan = new Scanner(System.in);
        OrderStatus status = null;

        System.out.println("Enter client data:");
        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        LocalDate birthDate = LocalDate.parse(scan.nextLine(), dtf);

        Client client = new Client(name, email, birthDate);

        System.out.println("\nEnter order data:");
        while (status == null) {
            try {
                System.out.println("1 - PENDING_PAYMENT");
                System.out.println("2 - PROCESSING");
                System.out.println("3 - SHIPPED");
                System.out.println("4 - DELIVERED");
                System.out.print("Status: ");
                int statusCode = scan.nextInt();
                status = OrderStatus.valueOf(statusCode);

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please enter a valid order status.\n");
            }
        }
        System.out.print("\nHow many items to this order? ");
        int n = scan.nextInt();

        Order order = new Order(LocalDateTime.now(), status, client);

        for (int i = 1; i <= n; i++) {
            System.out.printf("Enter #%d item data:%n", i);
            System.out.print("Product name: ");
            scan.nextLine();
            String productName = scan.nextLine();
            System.out.print("Product price: ");
            double productPrice = scan.nextDouble();
            System.out.print("Quantity: ");
            int quantity = scan.nextInt();

            Product product = new Product(productName, productPrice);

            OrderItem orderItem = new OrderItem(quantity, productPrice, product);
            order.addItem(orderItem);
        }

        System.out.println(order);

        scan.close();
    }

}
