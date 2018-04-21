package com.sndj.recipe.feature;


import java.util.HashMap;

/**
 * 需求；假设有一个数字到对应英文单词的Map，输出4对应的英文单词，如果不存在则输出NoValue
 */
public class MapGetOrDefault {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        if (map.containsKey(4)) {
            System.out.println(map.get(4));
        } else  {
            System.out.println("NoValue");
        }

        System.out.println(map.getOrDefault(4, "NoValue"));

    }


}
