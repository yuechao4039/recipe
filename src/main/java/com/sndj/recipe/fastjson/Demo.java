package com.sndj.recipe.fastjson;

import java.util.Arrays;

public class Demo {


    public void say(String... str) {
        Arrays.asList(str).forEach(x -> System.out.println(x));
    }

    public static void main(String[] args) {
        Demo demo = new Son();
        System.out.println(demo.getClass());
        System.out.println(demo.getClass().isAssignableFrom(Demo.class));
        System.out.println(Demo.class.isAssignableFrom(demo.getClass()));

    }
}

class Son extends  Demo{

}
