package com.sndj.recipe.feature;

import java.util.Arrays;
import java.util.function.Supplier;

public class Demo1 {


    public static void main(String[] args) {


//        Arrays.asList("what", "are", "you", "doing")
//                .forEach(x -> System.out.println(x));

//        Arrays.asList("what", "are", "you", "doing")
//                .forEach((String x) -> {
//                    System.out.println(x);
//                    System.out.println(x);
//                });


//        final String separator = ",";
//        Arrays.asList("a", "b", "d", "C").forEach((String x) -> System.out.println(x + separator));


//        Arrays.asList("a", "b", "d", "c").sort((a, b) -> {
//            return a.compareTo(b);
//        });
//
//        Arrays.asList("a", "b", "D", "C").sort((String a, String b) -> a.compareTo(b));


        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());

    }


    private interface Defaulable {
        default String notRequired() {
            return "Default Implementation";
        }
    }

    private static class DefaultableImpl implements Defaulable {

    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaulable create(Supplier<Defaulable> supplier) {
            return supplier.get();
        }
    }
}

