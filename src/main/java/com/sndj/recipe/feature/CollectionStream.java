package com.sndj.recipe.feature;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class CollectionStream {

    public static void main(String[] args) {

        // 使用Stream.forEach()迭代
//        Stream<String> s = Stream.of("what", "are", "you", "doing");
//        s.forEach(x -> System.out.println(x));


        // 保留长度等于3的字符串
//        Stream<String> s = Stream.of("what", "are", "you", "doing");
//        s.filter((x) -> x.length() == 3).forEach(x -> System.out.println(x));

//        Stream<String> s = Stream.of("what", "are", "you", "doing", "you");
//        s.distinct().forEach(x -> System.out.println(x));

//        Stream<String> s = Stream.of("what", "are", "you", "doing");
//        s.sorted().forEach(x -> System.out.println(x));
//        s.sorted((x, y) -> x.length() - y.length()).forEach(x -> System.out.println(x));

//        Stream<String> s = Stream.of("what", "are", "you", "doing");
////        s.map(new Function<String, String>() {
////            @Override
//            public String apply(String s) {
//                return s.toUpperCase();
//            }
//        }).forEach(x -> System.out.println(x));
//        s.map(x -> x.toUpperCase()).forEach(x -> System.out.println(x));

        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1,2), Arrays.asList(3, 4, 5));
        stream.flatMap(new Function<List<Integer>, Stream<?>>() {
            @Override
            public Stream<?> apply(List<Integer> integers) {
                return integers.stream();
            }
        });
        stream.flatMap(list -> list.stream())
                .forEach(i -> System.out.println(i));
    }
}
