java.lang.reflect.AnnotatedElement
===

### <T extends Annotation> T getAnnotation(Class<T> annotationClass)
#### Present 根据指定注解类型去查找对应注解
1. 注解直接出现在对应元素上。
2. 如果元素是一个类并且注解是是可继承注解。
3. 没有则返回null

### Annotation[] getAnnotations()
#### Present 
1. 同上，只是会返回当前元素上面所有符合的注解，没有注解类型条件进行约束
2. 如果子类和父类有同样注解，将返回子类的注解。
```java
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@interface AnnoA {
    String value();
}

@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@interface AnnoB {
    String value();
}

@AnnoB(value = "DDD")
class BeanA {

}


@AnnoA(value = "CCC")
class BeanB extends BeanA {

}
```

### default boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)
#### Present
返回true，当指定类型的注解在当前元素上。

### default <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass)
#### Directly Present
根据指定注解类型查找注解，注解必须直接出现当前元素上面，否则返回null
````java
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@interface AnnoA {

}

@AnnoA
class BeanA {

}
````

### Annotation[] getDeclaredAnnotations();
#### Directly Present
返回所有直接出现在当前元素上面的注解，如果没有，则数组长度为0



### getDeclaredAnnotationsByType(Class<T> annotationClass)
#### Directly Present   Indirectly Present
1. 既可以获得直接注解，也可以获得间接注解
2. 直接注解同上，所以下面代码运行时，AnnoA, AnnoC都可以直接获取到，但AnnoB获取不到
3. Container就是一个间接注解，是一个容器注解
4. 与`getDeclaredAnnotation(Class)`不同的是他会检测重复注解

```java
public class TestClass {
    @Test
    public void getAnnotation() throws NoSuchMethodException {
//        Arrays.asList(BeanB.class.getDeclaredAnnotations()).forEach(x -> {
//            System.out.println(x);
//        });
        Arrays.asList(BeanB.class.getDeclaredAnnotationsByType(AnnoA.class)).forEach(x -> {
            System.out.println(x);
        });
//        @com.sndj.recipe.innerclssss.AnnoA(value=CCC)
        Arrays.asList(BeanB.class.getDeclaredAnnotationsByType(AnnoB.class)).forEach(x -> {
            System.out.println(x);
        });

        Arrays.asList(BeanB.class.getDeclaredAnnotationsByType(AnnoC.class)).forEach(x -> {
            System.out.println(x);
        });
//                @com.sndj.recipe.innerclssss.AnnoC(value=1111)
//                @com.sndj.recipe.innerclssss.AnnoC(value=2222)

        Arrays.asList(BeanB.class.getDeclaredAnnotationsByType(Container.class)).forEach(x -> {
            System.out.println(x);
        });
//        @com.sndj.recipe.innerclssss.Container(value=[@com.sndj.recipe.innerclssss.AnnoC(value=1111), @com.sndj.recipe.innerclssss.AnnoC(value=2222)])
    }
}

@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@interface AnnoA {
    String value();
}

@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@interface AnnoB {
    String value();
}

@AnnoB(value = "DDD")
class BeanA {

}

@AnnoA(value = "CCC")
@AnnoC("1111")
@AnnoC("2222")
class BeanB extends BeanA {

}

@Repeatable(value = Container.class)
@interface AnnoC {
    String value();
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface Container {
    AnnoC[] value();
}

```

### default <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass)
#### Associated (Directly Present	Indirectly Present	Present)
