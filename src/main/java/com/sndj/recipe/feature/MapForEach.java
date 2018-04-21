package com.sndj.recipe.feature;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 需求：假设有一个数字到对应英文单词的Map，请输出Map中的所有映射关系．
 */
public class MapForEach {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");


        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println("==============");


        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                System.out.println(integer + "=" + s);
            }
        });

        System.out.println("==============");

        map.forEach((x, y) -> System.out.println(x + "=" + y));
    }
}
