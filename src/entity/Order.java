package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entity.enums.OrderStatus;

public class Order {

    private LocalDateTime moment;
    private OrderStatus status;

    private Client client;
    private List<OrderItem> items = new ArrayList<OrderItem>();

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Order(LocalDateTime moment, OrderStatus status, Client client) {
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    /*
     * MÉTODO TRADICIONAL SEM LAMBDA
     * 
     * public Double total() {
     * Double sum = 0.0;
     * for (OrderItem item : items) {
     * sum += item.getSubTotal();
     * }
     * return sum;
     * }
     */

    // MÉTODO COM LAMBDA
    public Double total() {
        return items.stream().mapToDouble(OrderItem::getSubTotal).sum();
        // return items.stream().mapToDouble(item -> item.getSubTotal()).sum(); 
        // Teste também válido com lambda completa
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nORDER SUMMARY:\n");
        sb.append("Order moment: " + dtf.format(moment) + "\n");
        sb.append("Order status: " + status + "\n");
        sb.append("Client: " + client + "\n");
        sb.append("Order items:\n");
        for (OrderItem item : items) {
            sb.append(item + "\n");
        }
        sb.append("Total price: $" + String.format("%.2f", total()));
        return sb.toString();
    }

}
