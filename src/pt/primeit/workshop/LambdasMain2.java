package pt.primeit.workshop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdasMain2 {

    public static void main(String[] args) {
        List<Integer> lst = List.of(1, 2, 3, 4);

        List<String> collect = lst.parallelStream()
                .skip(2)
                .limit(2)
                .map(i -> {

                    try {
                        m("");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    return "Str" + i;
                })
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(collect);

        Stream<String> str1 = Stream.of("Str1", "Str2", "str3");

        System.out.println(IntStream.of(1, 2, 3, 4).sum());

        System.out.println(lst.stream().count());

        System.out.println(lst.stream().reduce(0, (a, b) -> a + b));
    }


    static void m(String str) throws Exception {
        throw new Exception("");
    }
}
