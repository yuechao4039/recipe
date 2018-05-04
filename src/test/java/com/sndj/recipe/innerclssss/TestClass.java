package com.sndj.recipe.innerclssss;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

public class TestClass<A extends List & Serializable, C> {

    public static void main(String[] args) throws Exception {
        // 类的范型获取
        TypeVariable[] types = TestClass.class.getTypeParameters();
        for(TypeVariable type : types){
            type.getName();     // 获取变量名字，返回A、B
            type.getGenericDeclaration();       // 获取变量被定义在什么GenericDeclaration上，这里返回GenericTest.class
            type.getBounds()[0].getTypeName();  // 变量上边界数组，这里返回List.class
        }

        // 方法的范型获取
        TypeVariable<Method>[] type2s = TestClass.class.getMethod("test").getTypeParameters();
        for(TypeVariable type : type2s){
            type.getName();     // T、E
            type.getGenericDeclaration();   // test
            System.out.println(Arrays.toString(type.getBounds()));

        }
    }

    public <T, E>T test() {
        return null;
    }
}

