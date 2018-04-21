package com.sndj.recipe.feature;


@FunctionalInterface
public interface FuncationalIn {
    public static final int a = 3;
   <T> void accept(T t);

   default void sayHello() {
       System.out.println("Hello World");
   }

   static void say() {

   }
}
