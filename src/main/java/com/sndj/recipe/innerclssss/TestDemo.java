package com.sndj.recipe.innerclssss;

import lombok.Data;

import java.util.Arrays;

/**
 * @author yuechao 2018/4/20
 */
@Data
public class TestDemo extends  ParentDemo implements  InterDemo {

    public TestDemo() {

    }


    private String name1;

    String name2;

    protected  String name3;

    public String name4;

    public class BeanOne {

        public void sayHello() {
            System.out.println("BeanOne");
        }
    }

    public static class BeanTwo {
        public void sayHello() {
            System.out.println("BeanTwo");
        }
    }


    public static void main(String[] args) {
        Arrays.asList(TestDemo.class.getClasses()).forEach(x -> System.out.println(x));
    }
}
