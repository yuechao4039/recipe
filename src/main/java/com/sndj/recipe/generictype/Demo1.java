package com.sndj.recipe.generictype;

import java.io.Console;

/**
 * @author yuechao 2018/5/3
 */
public class Demo1 {

    public static void main(String[] args) {

        Comparable comparable = new NumericValue((byte) 0);
        comparable.compareTo(comparable); // OK
        comparable.compareTo("abc");    // OK at compile time, throws ClassCastException at run time

        NumericValue value = new NumericValue((byte) 0);
        value.compareTo(value); // OK
//        value.compareTo("abc"); // error


    }
}

interface Comparable<A> {
    public int compareTo(A that);
}

final class NumericValue implements Comparable<NumericValue> {
    private byte value;

    public NumericValue(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public int compareTo(NumericValue that) {
        return this.value - that.value;
    }
}
