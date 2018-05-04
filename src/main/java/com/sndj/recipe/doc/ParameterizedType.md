# Type
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

3. TypeVariable 变量
TypeVariable 描述所谓范型变量，也就是 <E extends List> 或者 <E>

public interface TypeVariable<D extends GenericDeclaration> extends Type, AnnotatedElement {
    // 变量上边界数组，没有指定的话是 Object
    Type[] getBounds();

    // 获取变量被定义在什么 GenericDeclaration 上
    D getGenericDeclaration();

    // 获取变量名字
    String getName();

    // jdk1.8
    AnnotatedType[] getAnnotatedBounds();
}
getBounds 得到上边界的 Type 数组，如 K 的上边界数组是 InputStream 和 Serializable。V 没有指定的话，上边界是 Object

getGenericDeclaration 返回的是声明这个 Type 所在的类 的 Type

getName 返回的是这个 type variable 的名称

class TypeVariableBean<K extends InputStream & Closeable, V> {
    // K 的上边界是 InputStream
    K key;
    // 没有指定的话 ，V 的上边界 属于 Object
    V value;

    // 不属于 TypeTypeVariable
    V[] values;
    String str;
    List<K> kList;
}

public class TypeVariableTest {

    @Test
    public void test() throws NoSuchFieldException {
        TypeVariableBean bean = new TypeVariableBean<FileInputStream, String>();
        Field field = TypeVariableBean.class.getDeclaredField("value");

        if (field.getGenericType() instanceof TypeVariable) {
            TypeVariable t = (TypeVariable) field.getGenericType();
            System.out.println(field.getName());            // value
            System.out.println(t.getGenericDeclaration());  // TypeVariableBean
        }
    }
}
4. GenericArrayType(范型数组)
范型数组，组成数组的元素中有范型则实现了该接口；它的组成元素是 ParameterizedType 或 TypeVariable 类型

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
下面我们一起来看一下例子：

class GenericArrayTypeBean<T> {
    public void test(List<String>[] pTypeArray, T[] vTypeArray,
                     List<String> list, String[] strings, User[] users) {
    }
}

public class GenericArrayTypeTest {

    @Test
    public void test() {
        Method method = GenericArrayTypeBean.class.getDeclaredMethods()[0];
        Type[] types = method.getGenericParameterTypes();           // 这是 Method 中的方法
        for (Type type : types) {
            System.out.println(type instanceof GenericArrayType);   // 依次输出true，true，false，false，false
        }
    }
}
5. WildcardType(通配符的类型)
extends 用来指定上边界，没有指定的话上边界默认是 Object，super 用来指定下边界，没有指定的话为 null。

几个主要方法介绍：

public interface WildcardType extends Type {
    Type[] getUpperBounds();
    Type[] getLowerBounds();
}
getLowerBounds 得到上边界 Type 的数组

getUpperBounds 得到下边界 Type 的数组

下面一起来看一下例子：

class WildcardTypeBean {
    private List<? extends Number> a;  // a 没有下界,
    //  没有指定的话，上边界默认是 Object ,下边界是  String
    private List<? super String> b;

    private Class<?> clazz;

    // 没有通配符
    private List<String> c;
}

public class WildcardTypeTest {

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
6. GenericDeclaration
GenericDeclaration 该接口用来定义哪些对象上是可以声明范型变量，目前实现 GenericDeclaration 接口的类包括 Class、Method、Constructor。

```java
public interface GenericDeclaration extends AnnotatedElement {
// 用来获取该GenericDeclaration的范型变量声明
public TypeVariable<?>[] getTypeParameters();
}

public class GenericTest<A extends List & Serializable, C> {

    public static void main(String[] args) throws Exception {
        // 类的范型获取
        TypeVariable[] types = GenericTest.class.getTypeParameters();
        for(TypeVariable type : types){
            type.getName();     // 获取变量名字，返回A、B
            type.getGenericDeclaration();       // 获取变量被定义在什么GenericDeclaration上，这里返回GenericTest.class
            type.getBounds()[0].getTypeName();  // 变量上边界数组，这里返回List.class
        }

        // 方法的范型获取
        TypeVariable<Method>[] type2s = GenericTest.class.getMethod("test").getTypeParameters();
        for(TypeVariable type : type2s){
            type.getName();     // T、E
            type.getGenericDeclaration();   // test
            type.getBounds();
        }
    }

    public <T, E>T test() {
        return null;
    }
}
参考：