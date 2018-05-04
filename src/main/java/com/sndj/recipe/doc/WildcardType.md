#WildcardType(通配符的类型)
extends 用来指定上边界，没有指定的话上边界默认是 Object，
super 用来指定下边界，没有指定的话为 null。

#### Type[] getUpperBounds();
得到上边界 Type 的数组


#### Type[] getLowerBounds();
得到下边界 Type 的数组

```java
package com.sndj.recipe.innerclssss;

import com.sndj.recipe.ognl.User;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuechao 2018/4/20
 */
class WildcardTypeBean {
    private List<? extends Number> a;  // a 没有下界,
    //  没有指定的话，上边界默认是 Object ,下边界是  String
    private List<? super String> b;

    private Class<?> clazz;

    // 没有通配符
    private List<String> c;

    
}

public class TestClass {

    @Test
    public void test() {
        try {
            Field[] fields = WildcardTypeBean.class.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Type type = field.getGenericType();
                String nameString = field.getName();
                System.out.println("下面开始打印" + nameString + "是否具有通配符");
                //1. 先拿到范型类型
                if (!(type instanceof ParameterizedType)) {
                    System.out.println("---------------------------");
                    continue;
                }

                //2. 再从范型里拿到通配符类型
                ParameterizedType parameterizedType = (ParameterizedType) type;
                type = parameterizedType.getActualTypeArguments()[0];
                if (!(type instanceof WildcardType)) {
                    System.out.println("---------------------------");
                    continue;
                }

                WildcardType wildcardType = (WildcardType) type;
                Type[] lowerTypes = wildcardType.getLowerBounds();
                if (lowerTypes != null) {
                    System.out.println("下边界：" + Arrays.toString(lowerTypes));
                }
                Type[] upTypes = wildcardType.getUpperBounds();
                if (upTypes != null) {
                    System.out.println("上边界：" + Arrays.toString(upTypes));
                }
                System.out.println("---------------------------");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }
}

/**
下面开始打印a是否具有通配符
下边界：[]
上边界：[class java.lang.Number]
---------------------------
下面开始打印b是否具有通配符
下边界：[class java.lang.String]
上边界：[class java.lang.Object]
---------------------------
下面开始打印clazz是否具有通配符
下边界：[]
上边界：[class java.lang.Object]
---------------------------
下面开始打印c是否具有通配符
---------------------------
*/
```