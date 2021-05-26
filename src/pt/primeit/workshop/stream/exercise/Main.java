package pt.primeit.workshop.stream.exercise;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Repository repo = new Repository();

        //System.out.println(repo.findAllCustomers());
        //System.out.println();
        //System.out.println(repo.findAllOrders());
        //System.out.println();
        //System.out.println(repo.findAllProducts());

        // Exercise 1 — Obtain a list of products belongs to category “Books” with price > 100
        List<Product> books = repo.findAllProducts().stream()
                .filter(i -> i.getPrice() > 100)
                .filter(i -> i.getCategory().equalsIgnoreCase("books"))
                .collect(Collectors.toList());

        //System.out.println(books);

        // Exercise 2 — Obtain a list of orders with products belong to category “Electronics”

        List<Order> electronics = repo.findAllOrders().stream()
                .filter(order -> order.getProducts().stream()
                                .anyMatch(i -> i.getCategory().equalsIgnoreCase("electronics"))
                        /* or .filter(i -> i.getCategory().equalsIgnoreCase("electronics")).count() > 0 */)
                .collect(Collectors.toList());
        //System.out.println(electronics);

        // Exercise 3 — Obtain a list of product with category = “Toys” and then apply 10% discount
        List<Product> toys = repo.findAllProducts().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("toys"))
                .map(product -> {
                    product.setPrice(product.getPrice() * .9);
                    return product;
                })
                .collect(Collectors.toList());
        //System.out.println(toys);

        // Exercise 4 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
        List<Product> collect = repo.findAllOrders().stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> order.getOrderDate().isAfter(LocalDate.of(2021, 2, 1))
                        && order.getOrderDate().isBefore(LocalDate.of(2021, 4, 1)))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
        //System.out.println(collect);


        // Exercise 5 — Get the cheapest products of “Books” category
        Optional<Product> books1 = repo.findAllProducts().stream()
                .filter(i -> i.getCategory().equalsIgnoreCase("books"))
                .min(Comparator.comparing(Product::getPrice))
                /* or .sorted(Comparator.comparing(Product::getPrice))
                .findFirst() */;

        //books1.ifPresent(System.out::println);


        // Exercise 6 — Get the 3 most recent placed order
        List<Order> collect1 = repo.findAllOrders().stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
        //System.out.println(collect1);

        // Exercise 7 — Get a list of orders which were ordered on 15-Mar-2021,
        //              log the order records to the console and then return its product list
        Set<Product> collect2 = repo.findAllOrders().stream()
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                //.peek(System.out::println)
                .flatMap(order -> order.getProducts().stream())
                //.distinct()
                .collect(Collectors.toSet());
        //System.out.println(collect2);

        // Exercise 8 — Calculate total lump sum of all orders placed in March 2021
        double sum = repo.findAllOrders().stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> order.getOrderDate().isAfter(LocalDate.of(2021, 3, 1))
                        && order.getOrderDate().isBefore(LocalDate.of(2021, 4, 1)))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
        // or .map(Product::getPrice)
        // .reduce(0.0, Double::sum);
        //System.out.println(sum);


        // Exercise 9 — Calculate order average payment placed on 15-Mar-2021
        double average = repo.findAllOrders().stream()
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .average().getAsDouble();

        //System.out.printf("%.2f", average);

        // Exercise 10 — Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
        DoubleSummaryStatistics statistics = repo.findAllProducts().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("books"))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();

        //System.out.println(String.format("count = %1$d, average = %2$f, max = %3$f, min = %4$f, sum = %5$f",
        //        statistics.getCount(), statistics.getAverage(), statistics.getMax(), statistics.getMin(), statistics.getSum()));

        // Exercise 11 — Obtain a data map with order id and order’s product count
        /*Map<Long, Integer> map = new HashMap<>();

        for(Order order : repo.findAllOrders()){
           map.put(order.getId(), order.getProducts().size());
        }

        System.out.println(map);*/

        Map<Long, Integer> productCountByOrder = repo.findAllOrders().stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        order -> order.getProducts().size()
                ));

        //System.out.println(productCountByOrder);

        // Exercise 12 — Produce a data map with order records grouped by customer

        Map<Customer, List<Order>> ordersByConsumerOldVersion = new HashMap<>();
        for (Order o : repo.findAllOrders()) {
            /*if (ordersByConsumerOldVersion.containsKey(o.getCustomer())) {
                ordersByConsumerOldVersion.get(o.getCustomer()).add(o);
            } else {
                List<Order> orders = new ArrayList<>();
                orders.add(o);
                ordersByConsumerOldVersion.put(o.getCustomer(), orders);
            }*/

            ordersByConsumerOldVersion.compute(o.getCustomer(), (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }

                v.add(o);
                return v;
            });
        }

        Map<Customer, List<Order>> collect3 = repo.findAllOrders().stream()
                .collect(
                        Collectors.groupingBy(Order::getCustomer)
                );
        //System.out.println(collect3);


        // Exercise 13 — Produce a data map with order record and product total sum
        Map<Order, Double> collect4 = repo.findAllOrders().stream()
                .collect(
                        Collectors.toMap(
                                //order -> order, or
                                Function.identity(),
                                order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum()
                        )
                );

        //collect4.forEach((k,v) -> System.out.println("Order [id="+ k.getId() + "] => " + v));

        // Exercise 14 — Obtain a data map with list of product name by category
        Map<String, List<String>> collect5 = repo.findAllProducts().stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.mapping(Product::getName, Collectors.toList())
                        )
                );
        //System.out.println(collect5);

        // Exercise 15 — Get the most expensive product by category
        Map<String, Optional<Product>> collect6 = repo.findAllProducts().stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.maxBy(Comparator.comparing(Product::getPrice))
                        )
                );

//        System.out.println(collect6);
    }
}
