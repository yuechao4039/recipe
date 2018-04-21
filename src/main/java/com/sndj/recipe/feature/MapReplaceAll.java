package com.sndj.recipe.feature;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 需求：假设有一个数字到对应英文单词的Map，请将原来映射关系中的单词都转换成大写．
 */
public class MapReplaceAll {

    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
//        for(Map.Entry<Integer, String> entry : map.entrySet()){
//            entry.setValue(entry.getValue().toUpperCase());
//        }

//        map.replaceAll(new BiFunction<Integer, String, String>(){
//            @Override
//            public String apply(Integer k, String v){
//                return v.toUpperCase();
//            }
//        });

        map.replaceAll((k, v) -> v.toUpperCase());

        map.forEach((x, y) -> System.out.println(x + "=" + y));
    }
}
