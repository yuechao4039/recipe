package com.sndj.recipe.feature;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * 需求：假设有一个字符串列表，将其中所有长度大于3的元素转换成大写，其余元素不变。
 */
public class ListReplaceAll {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(Arrays.asList("what", "are", "you", "doing"));
//        for (int i = 0; i < list.size(); i++) {
//            list.set(i, list.get(i).length() > 3 ? list.get(i).toUpperCase() : list.get(i));
//        }

//        list.replaceAll(new UnaryOperator<String>() {
//            @Override
//            public String apply(String s) {
//                if (s.length() > 3) {
//                    return s.toUpperCase();
//                }
//                return s;
//            }
//        });

        list.replaceAll(x -> {
            if (x.length() > 3) {
                return x.toUpperCase();
            }
            return x;
        });

        list.forEach(x -> System.out.println(x));
    }
}
