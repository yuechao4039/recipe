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
        System.out.println(TestClass.class.getName());  // com.sndj.recipe.innerclssss.TestClass
        System.out.println(Integer.class.getName()); // java.lang.Integer
        System.out.println(float.class.getName()); // float
        System.out.println(void.class.getName()); // void
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




