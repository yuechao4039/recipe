package com.sndj.recipe.innerclssss;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author yuechao 2018/5/4
 */
public class ArraysTest {

    @Test
    public void test() {
        String[] arr = new String[]{"aaa", "bb"};
        System.out.println(arr);

        int[] intarr = null;
        System.out.println(Arrays.toString(intarr));
        System.out.println(Arrays.asList(intarr).toString());


        System.out.println(Arrays.toString(arr));

        System.out.println(Arrays.asList(arr).toString());
    }
}
