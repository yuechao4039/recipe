package com.sndj.recipe.innerclssss;

import com.sndj.recipe.fastjson.A;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author yuechao 2018/4/20
 */

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







