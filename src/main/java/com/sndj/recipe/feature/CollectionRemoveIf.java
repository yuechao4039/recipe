package com.sndj.recipe.feature;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 需求：假设有一个字符串列表，需要删除其中所有长度大于3的字符串。
 */
public class CollectionRemoveIf {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>(Arrays.asList("what", "are", "you", "doing"));

//        Iterator<String> it = list.iterator();
//        while (it.hasNext()) {
//            if (it.next().length() > 3) {
//                it.remove();
//            }
//        }

//        list.removeIf(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.length() > 3;
//            }
//        });

        list.removeIf(s -> s.length() > 3);

        list.forEach((x) -> System.out.println(x));
    }
}
