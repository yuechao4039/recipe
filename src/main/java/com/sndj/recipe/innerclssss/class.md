CLASS
================


Filed 相关
------------
#####getFields()
1. 获取一个类及其所有父类和所有接口的所有 public 成员.
2. 获取一个接口及其所有父接口的 public 成员.
3. 如果当前Class对象为数组类型或原生数据类型或void类型，则返回一个空数组.
4. 如果类或接口没有任何public成员，则返回空数组.

<code>
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
</code>
>Result
public java.lang.String com.sndj.recipe.innerclssss.TestClass$BeanA.name
public static final java.lang.String com.sndj.recipe.innerclssss.TestClass$BeanC.name
public java.lang.String com.sndj.recipe.innerclssss.TestClass$BeanB.name




#####getField(String name)
1.先在当前Class里面查找此名字对应的public属性
2.如果1没有找到，则去实现的接口中去查询public属性,按接口的申明顺序
3.如果2也没有找到，则去父类去查询public属性.如果还没有则抛出NoSuchFieldException.
4.不能通过此方法去查询数组的length属性.

#####getDeclaredField(String name)
通过名字在当前Class里面查找对应属性
属性可以是private, protected, default (package) access, public
如果没有找到则抛NoSuchFieldException

#####getDeclaredFields()
查找当前Class里面所有成员 private protected default public
如果没有找到则返回空数组
不能通过此方法获取数组的length属性.

Constructor相关
---------------------
######getConstructor(Class<?>... parameterTypes)
1.根据Class参数数组parameterTypes查找当前Class里面的对应的public构造器
2.如果当前Class是一个成员内部类,参数数组第一个入参是外部类的Class对象.
3.如果没有找到则抛NoSuchMethodException
<code>
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
</code>
>Result:
public com.sndj.recipe.innerclssss.TestClass$BeanA(com.sndj.recipe.innerclssss.TestClass)
public com.sndj.recipe.innerclssss.TestClass$BeanB(com.sndj.recipe.innerclssss.TestClass,java.lang.String)


######getConstructors()
返回当前Class的由Constructor组成的构造器数组。
构造器全是public的。如果没有public构造器，则数组长度为零。
如果当前Class对象是一个数组或原生数据类型或void类型，数组长度也为零。


#####getDeclaredConstructor(Class<?>... parameterTypes)
1.根据数组参数返回当前Class对象的对应构造器,构造器修饰符可以是private protected default public
2.如果没有找到则抛NoSuchMethodException
3.如果是成员内部类，数组的第一个参数必须是外部类的Class对象

#####getDeclaredConstructors()
1.返回当前对象的所有构造器，构造器修饰符可以是private protected default public
2.如果没有找到，则数组长度为零.
3.如果当前Class对象是一个接口或原生数据类型或一个数组或为void，数组长度为零。



#####public Class<?>[] getClasses()
1.返回当前Class及父类的所有public成员内部类和public成员内部接口
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

>Result
interface com.sndj.recipe.innerclssss.SonClass$BeanB
class com.sndj.recipe.innerclssss.SonClass$BeanA
interface com.sndj.recipe.innerclssss.TestClass$BeanB
class com.sndj.recipe.innerclssss.TestClass$BeanA


2.返回当前Class(表示interface)及父接口的所有(public成员内部类)和()public成员内部接口)
3.当Class对象为数组或原生数据类型或void，则数组为空
4.如果当前Class没有内部类或内部接口，则数组为空

#####public Class<?>[] getDeclaredClasses()
1.返回当前类中的所有内部类和内部接口,修饰符可以是private public default protected
2.如果当前类中没有则返回数组长度为零
3.如果当前类是数组或原生数据类型或为空，返回数组长度为零

#####public Class<?> getDeclaringClass()
获取一个内部类或内部接口的外部内.