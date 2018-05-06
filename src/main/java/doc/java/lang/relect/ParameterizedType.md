# java.lang.reflect.ParameterizedType
1. Type 接口简介
简单来说：Type 是所有类型的父接口, 如 参数化类型(parameterized types 对应 ParameterizedType)、 数组类型(array types 对应 GenericArrayType)、 类型变量(type variables 对应 TypeVariable )和基本(原生)类型(primitive types 对应 Class),。

子接口有 ParameterizedType、TypeVariable、GenericArrayType、WildcardType、实现类有Class。

Type 接口

2. ParameterizedType(参数化类型)
下面的这些都是 parameterizedType

Map<String, Person> map;
Set<String> set1;
Class<?> clz;
Holder<String> holder;
List<String> list;

static class Holder<V>{
}
而类似于这样的不是 ParameterizedType.

Set set;
List aList;
ParameterizedType 的几个主要方法
public interface ParameterizedType extends Type {
    Type[] getActualTypeArguments();
    Type getRawType();
    Type getOwnerType();
}
getActualTypeArguments
###### 返回当前类型的实际类型参数的类型数组，如果没有，数组长度则为零
返回这个 Type 类型的参数的实际类型数组。 如 Map<String, Person> map 这个 ParameterizedType 返回的是 String 类、Person 类的全限定类名的 Type。

getRawType
####### 返回申明当前类型的类或的接口的类型。 
返回的是当前这个 ParameterizedType 的类型。 如 Map<String, Person> map 这个 ParameterizedType 返回的是 Map 类的全限定类名的 Type。

###### 返回一个类型，当前类型是他的内部类成员，如果没有则返回null
getOwnerType 这个比较少用到。返回的是这个 ParameterizedType 所在的类的 Type (注意当前的 ParameterizedType 必须属于所在类的 member)。比如 Map<String,Person> map 这个 ParameterizedType 的 getOwnerType() 为 null，而 Map.Entry<String, String>entry 的 getOwnerType() 为 Map 所属于的 Type。
```java
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
                // ownerType.rawType<>
                // java.util.Map .java.util.Map$Entry <java.lang.String, java.lang.String>
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
```
