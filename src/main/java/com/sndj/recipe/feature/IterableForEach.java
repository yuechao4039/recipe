package com.sndj.recipe.feature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
/**
 * 假设有一个字符串列表，需要打印出其中所有长度大于3的字符串.
 */
public class IterableForEach {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(Arrays.asList("what", "are", "you", "doing"));

        for (String str : list) {
            if (str.length() > 3) {
                System.out.println(str);
            }
        }

        System.out.println("============");

        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                if (s.length() > 3) {
                    System.out.println(s);
                }

            }
        });

        System.out.println("============");

        list.forEach((s -> {
            if (s.length() > 3) {
                System.out.println(s);
            }
        }));
    }
}
