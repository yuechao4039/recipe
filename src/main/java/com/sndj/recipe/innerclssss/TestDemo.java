package com.sndj.recipe.innerclssss;

import lombok.Data;
import org.apache.tools.ant.taskdefs.Java;

import java.util.Arrays;

/**
 * @author yuechao 2018/4/20
 */
@Data
public class TestDemo<E>{
//    Java 泛型中问号  ? 是通配符，表示未知类型。
//    通配符可以用于参数、属性、局部变量或返回值的类型，
//    但是不能用于泛型方法调用或创建泛型类实例的类型参数。


    public static void main(String[] args) {
        Arrays.asList(TestDemo.class.getClasses()).forEach(x -> System.out.println(x));
    }
}
