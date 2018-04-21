package com.sndj.recipe.feature;


import java.util.*;

/**
 * 需求：假设有一个字符串列表，按照字符串长度增序对元素排序。
 */
public class ListSort {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(Arrays.asList("what", "are", "you", "doing"));

//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        });


//        Collections.sort(list, (x, y) -> x.length() - y.length());

//        list.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        });


        list.sort((x, y) -> x.length() - y.length());

        list.forEach(x -> System.out.println(x));

    }
}
