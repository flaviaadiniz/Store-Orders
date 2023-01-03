package Entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private LocalDateTime moment;
    private OrderStatus orderStatus;
    private Client client;
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(OrderStatus orderStatus, Client client) {
        this.moment = LocalDateTime.now();
        this.orderStatus = orderStatus;
        this.client = client;

    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public Double total() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.subTotal();
        }
        return total;
    }

    public void saveOrder() {
        String path = "C:\\Users\\ffdin\\IdeaProjects\\Store-Orders\\resources\\orders.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(String.valueOf(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append("----------------------------------------------------------");
        stb.append("\nRESUMO DO PEDIDO:");
        stb.append("\nPedido feito em: " + formatter.format(moment));
        stb.append("\nOrder status: " + this.getOrderStatus());
        stb.append("\n" + client);
        stb.append("\nItens do pedido:\n");
        for (OrderItem item : orderItems) {
            stb.append(item + "\n");
        }
        stb.append("Preço total: R$" + total());
        stb.append("\n----------------------------------------------------------");
        return stb.toString();
    }
}
