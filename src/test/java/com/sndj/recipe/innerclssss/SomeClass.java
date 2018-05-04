package com.sndj.recipe.innerclssss;


import com.sndj.recipe.fastjson.TypeAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class SomeClass<T> {

    public <@A1 S extends @A2 T> @A3 S myMethod() {

        return null;
    }




    public static void main(String[] args) throws NoSuchMethodException {
        Method myMethod = TypeAnnotations.class.getDeclaredMethod("myMethod");
        AnnotatedType art = myMethod.getAnnotatedReturnType();
        System.out.print(
                Arrays.toString(art.getAnnotations()) + " " + art.getType().getTypeName() + " -> ");
        final boolean typeVariable = art instanceof AnnotatedTypeVariable;
        if (typeVariable) System.out.print('<');
        System.out.print(Arrays.toString(((AnnotatedElement) art.getType()).getAnnotations()) + " ");
        System.out.print(art.getType().getTypeName());
        if (typeVariable) {
            AnnotatedTypeVariable atv = (AnnotatedTypeVariable) art;
            AnnotatedType[] annotatedBounds = atv.getAnnotatedBounds();
            if (annotatedBounds.length > 0) {
                System.out.print(" extends ");
                for (AnnotatedType aBound : annotatedBounds) {
                    System.out.print(Arrays.toString(aBound.getAnnotations()) + " ");
                    System.out.print(aBound.getType().getTypeName() + ", ");
                }
            }
            System.out.println(">");
        }


    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@interface A1 {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@interface A2 {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@interface A3 {
}