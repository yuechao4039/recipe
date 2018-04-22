java.lang.Class
================


Filed 相关
------------
##### getFields()
1. 获取一个类及其所有父类和所有接口的所有 public 成员.
2. 获取一个接口及其所有父接口的 public 成员.
3. 如果当前Class对象为数组类型或原生数据类型或void类型，则返回一个空数组.
4. 如果类或接口没有任何public成员，则返回空数组.

```java
public class TestClass {

    public class BeanA extends BeanB implements  BeanC {
        public String name;

    }

    public class BeanB {
        public String name;
    }

    public interface BeanC {
         String name = "a";
    }

    @Test
    public void getFields() throws NoSuchMethodException {
        Arrays.asList(BeanA.class.getFields()).forEach(x -> {
            System.out.println(x);
        });
    }
}
```
> Result
public java.lang.String com.sndj.recipe.innerclssss.TestClass$BeanA.name
public static final java.lang.String com.sndj.recipe.innerclssss.TestClass$BeanC.name
public java.lang.String com.sndj.recipe.innerclssss.TestClass$BeanB.name




##### getField(String name)
1. 先在当前Class里面查找此名字对应的public属性
2. 如果1没有找到，则去实现的接口中去查询public属性,按接口的申明顺序
3. 如果2也没有找到，则去父类去查询public属性.如果还没有则抛出NoSuchFieldException.
4. 不能通过此方法去查询数组的length属性.

##### getDeclaredField(String name)
通过名字在当前Class里面查找对应属性
属性可以是private, protected, default (package) access, public
如果没有找到则抛NoSuchFieldException

##### getDeclaredFields()
查找当前Class里面所有成员 private protected default public
如果没有找到则返回空数组
不能通过此方法获取数组的length属性.

Constructor相关
---------------------
###### getConstructor(Class<?>... parameterTypes)
1. 根据Class参数数组parameterTypes查找当前Class里面的对应的public构造器
2. 如果当前Class是一个成员内部类,参数数组第一个入参是外部类的Class对象.
3. 如果没有找到则抛NoSuchMethodException
```java
public class TestClass {

    public class BeanA {

    }

    public class BeanB {
        public BeanB(String name) {

        }
    }

    @Test
    public void getConstructor() throws NoSuchMethodException {
        System.out.println(BeanA.class.getConstructor(BeanA.class.getDeclaringClass()));
        System.out.println(BeanB.class.getConstructor(BeanA.class.getDeclaringClass(), String.class));
    }
}
```
> Result:
public com.sndj.recipe.innerclssss.TestClass$BeanA(com.sndj.recipe.innerclssss.TestClass)
public com.sndj.recipe.innerclssss.TestClass$BeanB(com.sndj.recipe.innerclssss.TestClass,java.lang.String)


###### getConstructors()
* 返回当前Class的由Constructor组成的构造器数组。
* 构造器全是public的。如果没有public构造器，则数组长度为零。
* 如果当前Class对象是一个数组或原生数据类型或void类型，数组长度也为零。


##### getDeclaredConstructor(Class<?>... parameterTypes)
1. 根据数组参数返回当前Class对象的对应构造器,构造器修饰符可以是private protected default public
2. 如果没有找到则抛NoSuchMethodException
3. 如果是成员内部类，数组的第一个参数必须是外部类的Class对象

##### getDeclaredConstructors()
1. 返回当前对象的所有构造器，构造器修饰符可以是private protected default public
2. 如果没有找到，则数组长度为零.
3. 如果当前Class对象是一个接口或原生数据类型或一个数组或为void，数组长度为零。


内部类相关
---
##### getClasses()
1. 返回当前Class及父类的所有public成员内部类和public成员内部接口
```java
public class TestClass {

    public class BeanA {

    } 

    public interface BeanB {

    }

    @Test
    public void getFields() throws NoSuchMethodException {
        Arrays.asList(SonClass.class.getClasses()).forEach(x -> {
            System.out.println(x);
        });
    }
}

class SonClass extends  TestClass implements Face {
    public class BeanA {

    }

    public interface BeanB {

    }
}

interface Face {
    public class BeanA {

    }

    public interface BeanB {

    }
}
```

> Result
interface com.sndj.recipe.innerclssss.SonClass$BeanB
class com.sndj.recipe.innerclssss.SonClass$BeanA
interface com.sndj.recipe.innerclssss.TestClass$BeanB
class com.sndj.recipe.innerclssss.TestClass$BeanA


2. 返回当前Class(表示interface)及父接口的所有(public成员内部类)和()public成员内部接口)
3. 当Class对象为数组或原生数据类型或void，则数组为空
4. 如果当前Class没有内部类或内部接口，则数组为空

##### getDeclaredClasses()
1. 返回当前类中的所有内部类和内部接口,修饰符可以是private public default protected
2. 如果当前类中没有则返回数组长度为零
3. 如果当前类是数组或原生数据类型或为空，返回数组长度为零

##### getDeclaringClass()
获取一个内部类或内部接口的外部类.


### 常规动作
#### isArray
判断当前Class是不是一个数组
```java
public class TestClass {
    @Test
    public void getAnnotation() throws NoSuchMethodException {
        String[] arr = new String[]{"A"};
        System.out.println(arr.getClass().isArray()); // true
        System.out.println(String.class.isArray()); // false
    }
}
```
#### isInterface
判断当前Class是否是接口
```java
public class TestClass {
    @Test
    public void getAnnotation() throws NoSuchMethodException {

        System.out.println(TestClass.class.isInterface()); // false
        System.out.println(AnnotatedElement.class.isInterface()); // true
    }
}
```

#### isPrimitive()
判断当前Class对象是否原生数据类型
```java
public class TestClass {
    @Test
    public void isAssignableFrom() {

        System.out.println(int.class.isPrimitive());
        System.out.println(Integer.TYPE.isPrimitive());
        System.out.println(Integer.class.isPrimitive());
        System.out.println(void.class.isPrimitive());
        System.out.println(Void.class.isPrimitive());
//        true
//        true
//        false
//        true
//        false
    }
}
```

#### isAnnotation()
1. 判断当前Class是否是注解类型
2. 如果是注解类型，则`isInterface`也会返回true,因为注解也是接口。
```java
public class TestClass {
    @Test
    public void isAssignableFrom() {
        System.out.println(AnnoA.class.isAnnotation()); // true
        System.out.println(AnnoA.class.isInterface()); // true
    }
}

@interface AnnoA {

}
```

#### isSynthetic()


#### isAssignableFrom(Class<?> cls)
1. 判断当前Class对象是否和cls一样，或者是是cls的父类或接口的Class对象，则返回true
2. 如果当前Class对象是原生数据类型，只有在cls参数和当前Class对象一样的情况下，才返回true
3. 如果cls为空，则抛NullPointerException
4. 如果返回结果为true,cls对应对象可以强制转换为当前Class类型的对象
```java
public class TestClass {
    @Test
    public void isAssignableFrom() {

        Son son = new Son();
        System.out.println(Son.class.isAssignableFrom(son.getClass())); // true
        System.out.println(Parent.class.isAssignableFrom(son.getClass())); // true
        System.out.println(Face.class.isAssignableFrom(son.getClass())); // true
        System.out.println(Object.class.isAssignableFrom(int.class)); // false
        System.out.println(int.class.isAssignableFrom(int.class)); // true
    }
}

class Son extends Parent implements Face {

}

class Parent {

}

interface Face {

}
```

#### isInstance(Object obj)
1. 此方法和操作符 `instanceof` 相同。
2. obj是当前Class的实例或者子类的实例，则返回true。
3. 如果当前Class对象是一个接口，obj是实现此接口的类或子类实例，则返回true
4. 如果当前Class对象是原生数据类型，则返回false
```java
public class TestClass {
    @Test
    public void isAssignableFrom() {

        Son son = new Son();
        System.out.println(Son.class.isInstance(son));
        System.out.println(Parent.class.isInstance(son));
        System.out.println(Face.class.isInstance(son));
        System.out.println(Object.class.isInstance(int.class));
        System.out.println(int.class.isInstance(3));
//        true
//        true
//        true
//        true
//        false
    }
}

class Son extends Parent implements Face {

}

class Parent {

}

interface Face {

}
```

#### static native Class<?> getPrimitiveClass(String name);
1. 返回指定命名的原生数据类型的Class对象
2. 此方法是`protected`，只能在子类或包类进行调用，而Class对象是final的，所以只能在包类调用此方法。
```java
public class TestClass {
    @Test
    public void getPrimitiveClass() {
        System.out.println(int.class); // int
        // public static final Class<Integer>  TYPE = (Class<Integer>) Class.getPrimitiveClass("int");
        System.out.println(Integer.TYPE); // int
    }
}
```

#### getEnclosingClass()
1. 获取当前内部类或内部接口的第一层外部类或外部接口。
2. 如果非内部类或内部接口，直接返回`null`
```java
public class TestClass {
    @Test
    public void getEnclosingClass() {
        System.out.println(TestClass.class.getEnclosingClass()); // null
        System.out.println(InnerA.class.getEnclosingClass()); // class com.sndj.recipe.innerclssss.TestClass

        System.out.println(Face.class.getEnclosingClass()); // null
        System.out.println(Face.InnerA.class.getEnclosingClass()); // interface com.sndj.recipe.innerclssss.Face
    }

    class InnerA {

    }
}
interface Face {
    class InnerA {

    }
}
```

#### getEnumConstants()
1. 返回当前枚举`Class`对象的的所有元素，如果没有元素，则返回空数组
2. 如果当前`Class`不是枚举`Class`，则返回`null`
```java
public class TestClass {
    @Test
    public void getEnumConstants() throws Exception {
        System.out.println(TestClass.class.getEnumConstants());
        Arrays.asList(EnumA.class.getEnumConstants()).forEach(x -> {
            System.out.println(x.name());
            System.out.println(x);
        });

        Arrays.asList(EnumB.class.getEnumConstants()).forEach(x -> {
            System.out.println(x.name());
            System.out.println(x);
        });
    }
}

enum EnumA {
    GREEN, RED
}
enum EnumB {
        JACK("jack"), TOM("tom");

    private String name;
    EnumB(String name) {
        this.name = name;
    }
}
```

#### getInterfaces()
1. 如果当前Class对象代表一个类，但类没有实现任何接口，则返回一个空的Class数组。
2. 如果当前Class对象代表一个接口，但接口没有继承任何接口，则返回一个空的Class数组。
3. 如果当前Class对象代表原生数据类型或void，则返回一个空的Class数组。
4. 如果当前Class对象代表着一个数组类型，则返回一个数组 `[Cloneable, Serializable]`
```java
public class TestClass {
    @Test
    public void getInterfaces() throws Exception {
       Arrays.asList(new int[3].getClass().getInterfaces()).forEach(x -> {
           System.out.println(x);
       });
//        interface java.lang.Cloneable
//        interface java.io.Serializable
    }
}
```
5. 如果当前Class对象代表一个类，但类实现了一个或多个接口，则返回一个按接口申明顺序的Class数组。
```java
public class TestClass {
    @Test
    public void getInterfaces() throws Exception {
        Arrays.asList(S.class.getInterfaces()).forEach(System.out::println);
//        interface com.sndj.recipe.innerclssss.A
//        interface com.sndj.recipe.innerclssss.C
//        interface com.sndj.recipe.innerclssss.B
    }
}

interface A{

}
interface B{

}
interface C{

}
class S implements  A, C, B{

}
```
6. 如果当前Class对象代表一个接口，接口继承了其他接口，则返回一个你接口的Class数组。
```java
public class TestClass {
    @Test
    public void getInterfaces() throws Exception {
        Arrays.asList(A.class.getInterfaces()).forEach(System.out::println);
//        interface com.sndj.recipe.innerclssss.C
    }
}

interface A extends C {

}

interface B {

}

interface C extends B {

}
```


#### getComponentType()
1. 如果当前Class对象是一个数组，则返回他的组件Class对象
2. 如果当前Class对象非一个数组，则返回`null`
```java
public class TestClass {
    @Test
    public void getInterfaces() throws Exception {
        System.out.println(new int[3].getClass().getComponentType());  // int
        System.out.println(this.getClass().getComponentType()); // null
    }
}
```

#### getName()
1. 如果当前对象是引用类型，但非数组引用，类的二进制名称将被返回。
2. 如果当前对象是原生数据类型或void，对应的java关键字将被返回。
3. 如果当前对象是一个数组类型，由于`[`，打前缀表示数组的尝试，如果是一维数组则为`[`，
如果是二维数组则为`[[`，以此类推，然后到数组元素类型，元素类型如下表。
| Element Type | Encoding |
| :- | :-: |
| boolean | Z |
| byte | B |
| char | C |
| double | D |
| float | F |
| int | I |
| long | J |
| short | S |
| class or interface | Lclassname; |
> String.class.getName()
          returns "java.lang.String"
      byte.class.getName()
          returns "byte"
      (new Object[3]).getClass().getName()
          returns "[Ljava.lang.Object;"
      (new int[3][4][5][6][7][8][9]).getClass().getName()
          returns "[[[[[[[I"

#### toString()
1. 如果当前对象是类，则以class打头，接下来一个空格，再调用`getName()`方法
2. 如果当前对象是接口，则以 interface打头，接下来一个空格，再调用`getName()`方法
3. 如果当前对象是原生数据类型或void，则直接返回java对应关键字
4. 如果当前对象是数组，则以 class打头，同第一条.
```java
public class TestClass {
    @Test
    public void getInterfaces() throws Exception {
        System.out.println(new int[3].getClass());
        System.out.println(new int[3].getClass().getName());

        System.out.println(new int[3][4].getClass());
        System.out.println(new int[3][4].getClass().getName());

        System.out.println(new TestClass[3].getClass());
        System.out.println(new TestClass[3].getClass().getName());
//        class [I
//                [I
//        class [[I
//                [[I
//        class [Lcom.sndj.recipe.innerclssss.TestClass;
//        [Lcom.sndj.recipe.innerclssss.TestClass;
    }
}
```

#### getGenericSuperclass()
