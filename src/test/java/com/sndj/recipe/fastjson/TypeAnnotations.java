package com.sndj.recipe.fastjson;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

interface T {
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

public class TypeAnnotations {

    public <@A1 S extends @A2 T> @A3 S myMethod() {
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method myMethod = TypeAnnotations.class.getDeclaredMethod("myMethod");

        variant1(myMethod);
        variant2(myMethod);
    }

    private static void variant1(Method myMethod) {
        AnnotatedType art = myMethod.getAnnotatedReturnType();
        System.out.print(Arrays.toString(art.getAnnotations()) + " " + art.getType().getTypeName() + " -> ");
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

    private static void variant2(Method myMethod) {
        List<TypeVariable<?>> typeParameters = Arrays.asList(myMethod.getTypeParameters());

        for (TypeVariable<?> tv : typeParameters) {
            System.out.print("< " + Arrays.toString(tv.getAnnotations()) + " " + tv.getName());
            AnnotatedType[] annotatedBounds = tv.getAnnotatedBounds();
            if (annotatedBounds.length > 0) {
                System.out.print(" extends ");
                for (AnnotatedType aBound : annotatedBounds) {
                    System.out.print(Arrays.toString(aBound.getAnnotations()) + " ");
                    System.out.print(aBound.getType().getTypeName() + ", ");
                }
            }
            System.out.print("> ");
        }

        AnnotatedType art = myMethod.getAnnotatedReturnType();
        System.out.print(Arrays.toString(art.getAnnotations()) + " ");
        int ix = typeParameters.indexOf(art.getType());
        if (ix >= 0) System.out.print("[ref to type parameter #" + ix + "] ");
        System.out.println(art.getType().getTypeName());
    }
}
