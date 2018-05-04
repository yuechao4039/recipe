package com.sndj.recipe.innerclssss;

import javafx.scene.Parent;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author yuechao 2018/4/20
 */

public class TestClass {


    @Test
    public void testParameterizedType() {
        Field f = null;
        try {
            Field[] fields = ParameterizedTypeBean.class.getDeclaredFields();
            // 打印出所有的 Field 的 Type 是否属于 ParameterizedType
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];
                System.out.println((f.getName()
                        + " getGenericType() instanceof ParameterizedType "
                        + (f.getGenericType() instanceof ParameterizedType)));
            }
            System.out.println("=========================");
            Arrays.asList(ParameterizedTypeBean.class.getDeclaredFields())
                    .stream()
//                    .filter(x -> x.getName().equals("str"))
                    .forEach(x -> {
                x.getGenericType().toString();
                System.out.println(x.getGenericType());
            });
            System.out.println("=========================");
            getParameterizedTypeMes("map");
            System.out.println("=========================");
            getParameterizedTypeMes("entry");
            System.out.println("=========================");
            getParameterizedTypeMes("holder");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getParameterizedTypeMes(String fieldName) throws Exception {
        Field f;
        f = ParameterizedTypeBean.class.getDeclaredField(fieldName);
        f.setAccessible(true);
        System.out.println(f.getGenericType());
        if (f.getGenericType() instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) f.getGenericType();
            System.out.println(pType.getRawType());
            for (Type type : pType.getActualTypeArguments()) {
                System.out.println(type);
            }
            System.out.println(pType.getOwnerType()); // null
        }
    }
}

class ParameterizedTypeBean {
    // 下面的 field 的 Type 属于 ParameterizedType
    Map<String, User> map;
    Set<String> set1;
    Class<?> clz;
    Holder<String> holder;
    List<String> list;

    // Map<String,Person> map 这个 ParameterizedType 的 getOwnerType() 为 null，
    // 而 Map.Entry<String, String> entry 的 getOwnerType() 为 Map 所属于的 Type。
    Map.Entry<String, String> entry;

    // 下面的 field 的 Type 不属于ParameterizedType
    String str;
    Integer i;
    Set set;
    List aList;

    static class Holder<V> {
    }
}

class User {

}







