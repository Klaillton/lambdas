package pt.primeit.workshop.stream.exercise;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Repository {

    private final List<Order> orders;
    private final List<Product> products;
    private final List<Customer> customers;

    public Repository() {
        var product1 = new Product(1L, "Livro I", "Books", 15.0);
        var product2 = new Product(2L, "Livro II", "Books", 22.0);
        var product3 = new Product(3L, "PS5", "Electronics", 450.0);
        var product4 = new Product(4L, "Mac Book Pro", "Electronics", 3500.0);
        var product5 = new Product(5L, "Barbie", "Toys", 15.50);
        var product6 = new Product(6L, "RC Car", "Toys", 55.0);
        var product7 = new Product(7L, "Electronics For dummies", "Books", 150.0);
        var product8 = new Product(7L, "Electronics For dummies", "Books", 150.0);
        products = List.of(product1, product2, product3, product4, product5, product6, product7);


        var customer1 = new Customer(1L, "customer 1", 1);
        var customer2 = new Customer(2L, "customer 2", 2);
        var customer3 = new Customer(3L, "customer 3", 2);
        customers = List.of(customer1, customer2, customer3);


        var order1 = new Order(1L, LocalDate.of(2021, 3, 5), LocalDate.of(2021, 3, 25), "Deliveried", customer1, Set.of(product1, product2));
        product1.getOrders().add(order1);
        product2.getOrders().add(order1);

        var order2 = new Order(2L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 25), "Deliveried", customer2, Set.of(product3, product7));
        product3.getOrders().add(order2);
        product7.getOrders().add(order2);

        var order3 = new Order(3L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 16), "Deliveried", customer3, Set.of(product4, product5));
        product4.getOrders().add(order3);
        product5.getOrders().add(order3);

        var order4 = new Order(4L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 20), "Deliveried", customer1, Set.of(product6));
        product6.getOrders().add(order4);

        var order5 = new Order(5L, LocalDate.of(2021, 3, 20), LocalDate.of(2021, 3, 25), "Deliveried", customer1, Set.of(product3, product4));
        product3.getOrders().add(order5);
        product4.getOrders().add(order5);

        var order6 = new Order(6L, LocalDate.of(2021, 3, 16), LocalDate.of(2021, 3, 18), "Deliveried", customer3, Set.of(product5, product8));
        product5.getOrders().add(order6);
        product8.getOrders().add(order6);

        var order7 = new Order(8L, LocalDate.of(2021, 3, 18), LocalDate.of(2021, 3, 25), "Deliveried", customer1, Set.of(product2, product4));
        product2.getOrders().add(order7);
        product4.getOrders().add(order7);

        orders = List.of(order1, order2, order3, order4, order5, order6, order7);
    }

    public List<Product> findAllProducts() {
        return products;
    }

    public List<Order> findAllOrders() {
        return orders;
    }

    public List<Customer> findAllCustomers() {
        return customers;
    }
}
