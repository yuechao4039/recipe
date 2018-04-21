package com.sndj.recipe.innerclssss;

import com.sndj.recipe.fastjson.A;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author yuechao 2018/4/20
 */

/**
 * #####getFields()
 1.获取一个类及其所有父类和所有接口的所有 public 成员.
 2.获取一个接口及其所有父接口的 public 成员.
 3.如果当前Class对象为数组类型或原生数据类型或void类型，则返回一个空数组.
 4.如果类或接口没有任何public成员，则返回空数组.
 */
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
