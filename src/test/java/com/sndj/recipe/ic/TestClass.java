package com.sndj.recipe.ic;


import java.io.FilterInputStream;
import java.io.Serializable;
import java.util.List;

public class TestClass<A extends List & Serializable, C> {

    public static void main(String[] args) throws Exception {

        System.out.println(FilterInputStream.class.getConstructors());
    }


}

