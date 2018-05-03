package com.sndj.recipe.innerclssss;

import javafx.scene.Parent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuechao 2018/4/20
 */

public class TestClass {
    @Test
    public void test() throws Exception {
        // generic type to raw type
        Box<String> stringBox = new Box<>();
        Box rawBox = stringBox;               // OK
        rawBox.set(8);  // warning: unchecked invocation to set(T)

        // raw type to generic type
        Box rawBox1 = new Box();           // rawBox is a raw type of Box<T>
        Box<Integer> intBox = rawBox1;     // warning: unchecked conversion


        List<String> list = new ArrayList<>();
        list.add("aaa");
        List rawList = list;
        rawList.add(12);
        rawList.forEach(System.out::println);
    }
}

class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}

class WildcardFixed{
    void foo(List<?> i){
        fooHelper(i);
    }

    // Helper method created so that the wildcard can be captured
    // through type inference.
    private <T> void fooHelper(List<T> l){
        l.set(0, l.get(0)); // 传入参数也为 T，编译器推断为 CAP#1
    }
}





