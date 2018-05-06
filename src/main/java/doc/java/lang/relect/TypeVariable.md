# java.lang.reflect.TypeVariable
TypeVariable 描述所谓范型变量，也就是 <E extends List> 或者 <E

#### Type[] getBounds()
获取类型变量的上边界，如果没有则为Object


#### D getGenericDeclaration();
申明当前类型变量的类或接口的TYPE // 返回的是声明这个 Type 所在的类的 Type


#### String getName();
返回当前类型变量的名称


#### AnnotatedType[] getAnnotatedBounds();

```java
package com.sndj.recipe.innerclssss;

import org.junit.Test;

import java.io.Closeable;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuechao 2018/4/20
 */

class TypeVariableBean<K extends InputStream & Closeable, V> {
    // K 的上边界是 InputStream
    K key;
    // 没有指定的话 ，V 的上边界 属于 Object
    V value;

    // 不属于 TypeVariable
    V[] values;
    String str;
    List<K> kList;
}

public class TestClass {

    @Test
    public void test() throws NoSuchFieldException {
        Arrays.asList(TypeVariableBean.class.getDeclaredFields()).forEach(x -> {
            Type type = x.getGenericType();
            System.out.println(type + " " + (type instanceof TypeVariable));

            if (type instanceof TypeVariable) {
                System.out.println("GenericDeclaration -> " + ((TypeVariable) type).getGenericDeclaration());
                System.out.println("name -> " + ((TypeVariable) type).getName());
                System.out.println("BOUNDS -> " + Arrays.toString(((TypeVariable) type).getBounds()));
            }
        });
    }
}
```