package com.sndj.recipe.innerclssss;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws Exception {
// 初始化Bean1
        Test.Bean1 bean1 = new Test().new Bean1();
        bean1.I++;
// 初始化Bean2

        Test.Bean2 bean2 = new Test.Bean2();
        bean2.J++;
//初始化Bean3
        Bean.Bean3 bean3 = new Bean().new Bean3();
        bean3.k++;

        System.out.println(Arrays.toString(Test.class.getDeclaredClasses()));;

        System.out.println(Modifier.toString(Test.Bean1.class.getDeclaredConstructors()[0].getModifiers()));;
        System.out.println(Modifier.toString(Test.Bean2.class.getDeclaredConstructors()[0].getModifiers()));;
    }

    class Bean1 {

        public int I = 0;
    }

    static class Bean2 {
        public int J = 0;
    }
}

class Bean {
    public class Bean3 {
        public int k = 0;
    }
}