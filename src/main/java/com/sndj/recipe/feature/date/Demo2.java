package com.sndj.recipe.feature.date;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Demo2 {

    public static void main(String[] args) {

        // Predicate Function Supplier Consumer  Comparator
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }





         long t2 = System.nanoTime();
        for (String tmp : list) {
            System.out.println(tmp);
        }
         long t3 = System.nanoTime();

        long t0 = System.nanoTime();
        list.forEach(System.out::println);
        long t1 = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(t1 - t0));
        System.out.println(TimeUnit.NANOSECONDS.toMillis(t3 - t2));

    }



}




