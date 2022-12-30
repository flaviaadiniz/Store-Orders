package Application;

import Entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite os dados do cliente: ");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Data de nascimento (DD/MM/YYYY): ");
        Date birthDate = format.parse(scanner.next());

        Client client1 = new Client(nome, email, birthDate);

        System.out.println("\nDigite os dados do pedido: ");
        System.out.print("Status do pedido: ");
        OrderStatus status = OrderStatus.valueOf(scanner.next().toUpperCase());
        scanner.nextLine();

        Order order = new Order(status, client1);

        System.out.print("Quantos itens terá este pedido? ");
        int itens = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < itens; i++ ) {
            System.out.println("\nDigite os dados do item #" + (i+1) + ": ");

            System.out.print("Nome do produto: ");
            String name = scanner.nextLine();
            System.out.print("Preço do produto: ");
            double price = scanner.nextDouble();
            Product product = new Product(name, price);

            System.out.print("Quantidade: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            OrderItem orderItem = new OrderItem(quantity, price, product);
            order.addOrderItem(orderItem);
        }

        System.out.println("\n" + order);

        scanner.close();
    }
}
