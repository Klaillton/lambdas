package pt.primeit.workshop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
interface Action {
    int op(int i);
}

@FunctionalInterface
interface I {
    int max(int a, int b);

    default void m() {

    }
}

public class LambdasMain {


    public static void main(String[] args) {

        I ix = (a, b) -> {
            if (a > b) {
                return a;
            }
            return b;
        };

        //System.out.println(ix.max(10, 15));


        List<Integer> lst = List.of(1, 2, 3, 4, 5, 6);

        //Action a = v -> v * 450;

        Stream<Integer> stream = lst
                .stream()
                .map(i -> i + 10)
                .peek(System.out::println);

        List<Integer> collect = stream.collect(Collectors.toList());


        run(lst, v -> v * 50);





        /*run(lst, v -> v + 50);
        run(lst, v -> v - 100);
        run(lst, a);

        Consumer<String> c = s -> System.out.println("String " + s);

        Predicate<Integer> p = ix -> ix > 10;
        System.out.println(p.test(1));

        Supplier<Integer> s = () -> new Random().nextInt();
        System.out.println(s.get());

        c.accept("String to accept");


        run(lst, new Action() {
            @Override
            public int op(int i) {
                return i * 400;
            }
        });
*/
    }

    public static void run(List<Integer> lst, Action action) {

        List<Integer> collect = lst.stream()
                .filter(i -> i > 3)
                .map(action::op)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);


        /*for (Integer i : lst) {
            System.out.println(action.op(i));
        }*/

    }

}