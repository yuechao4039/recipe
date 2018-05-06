# java.lang.reflect.GenericArrayType
范型数组，组成数组的元素中有范型则实现了该接口；
它的组成元素是 ParameterizedType 或 TypeVariable 类型

```java
package com.sndj.recipe.innerclssss;

import com.sndj.recipe.ognl.User;
import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuechao 2018/4/20
 */

class GenericArrayTypeBean<T> {

    // 属于 GenericArrayType
    List<String>[] pTypeArray;
    // 属于 GenericArrayType
    T[] vTypeArray;

    // 不属于 GenericArrayType
    List<String> list;
    // 不属于 GenericArrayType
    String[] strings;
    // 不属于 GenericArrayType
    User[] users;

    public void test(List<String>[] pTypeArray, T[] vTypeArray,
                     List<String> list, String[] strings, User[] users) {
    }
}

public class TestClass {

    @Test
    public void test() {
        Method method = GenericArrayTypeBean.class.getDeclaredMethods()[0];
        Type[] types = method.getGenericParameterTypes();           // 这是 Method 中的方法
        for (Type type : types) {
            System.out.println(type instanceof GenericArrayType);   // 依次输出true，true，false，false，false
        }
        System.out.println("=====");
        Arrays.asList(GenericArrayTypeBean.class.getDeclaredFields()).forEach(x ->{
            System.out.println(x.getName() + " -> " + (x.getGenericType() instanceof  GenericArrayType));
        });

    }
}


```