package com.sndj.recipe.innerclssss;

import javafx.scene.Parent;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author yuechao 2018/4/20
 */

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

interface A extends C {

}

interface B {

}

interface C extends B {

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


interface Face {
    class InnerA {

    }
}

@interface AnnoA {

}

class Son extends Parent implements Face {

}




