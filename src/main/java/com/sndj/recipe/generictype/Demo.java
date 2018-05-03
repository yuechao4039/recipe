package com.sndj.recipe.generictype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 声明有界类型参数（Bounded Type Parameters），格式为  T extends Class & Interface1 & ... & InterfaceN
 * 有界类型参数同样可以用于泛型类和泛型接口中，而且支持多个边界，
 * 例如  <T extends A & B & C> ，只允许最多一个 Class 边界，而且如果有一个 Class 边界，Class 边界必须在最前面。
 * @author yuechao 2018/5/2
 */
public class Demo<T> {
    public static void main(String[] args) {
        Demo<Integer> a = new Demo<>();
        Demo<Number> b = new Demo<>();
        System.out.println();

        List<Integer> li = Arrays.asList(1, 2, 3);
        List<Double>  ld = Arrays.asList(10.10, 20.20, 30.30);
//        swapFirst(li, ld);


//        List<? extends Number> list = new ArrayList<Integer>();
//        Number first = list.get(0); // OK
//        list.add(null); // OK
//        Number number = 1;
//        list.add(number); // 错误: 不兼容的类型: Number无法转换为CAP#1
//        list.clear(); // OK
//        list.remove(0); // OK

//        List<? super Number> list = new ArrayList<Object>();
//        Number first = list.get(0); // 错误: 不兼容的类型: CAP#1无法转换为Number
//        list.add(null); // OK
//        Number number = 1;
//        list.add(number); // OK
//        list.clear(); // OK
//        list.remove(0); // OK

    }

//    void foo(List<?> i) {
//        i.set(0, i.get(0)); // 错误: 不兼容的类型: Object无法转换为CAP#1
//    }

    void foo(List<?> i) {
        fooHelper(i);
    }

    // Helper method created so that the wildcard can be captured
    // through type inference.
    private <T> void fooHelper(List<T> l){
        l.set(0, l.get(0)); // 传入参数也为 T，编译器推断为 CAP#1
    }


//    static void swapFirst(List<? extends Number> l1, List<? extends Number> l2){
//        Number temp = l1.get(0);
//        l1.set(0, l2.get(0)); // 错误: 不兼容的类型: Number无法转换为CAP#1
//        l2.set(0, temp);     // 错误: 不兼容的类型: Number无法转换为CAP#1
//    }
}

class A {

}

interface B {

}

interface  C {

}

