package pt.primeit.workshop.stream.exercise;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


public class Order {

    Set<Product> products;
    private Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;
    private Customer customer;

    public Order(Long id, LocalDate orderDate, LocalDate deliveryDate, String status, Customer customer, Set<Product> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customer = customer;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}