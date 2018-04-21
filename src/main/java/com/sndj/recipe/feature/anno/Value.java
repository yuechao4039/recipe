package com.sndj.recipe.feature.anno;


/**
 * 更佳的类型引用
 */
public class Value<T> {
    public static <E> E defaultValue() {
        return null;
    }

    public T getOrDefault(T value, T defaultValue) {
        return (value != null) ? value : defaultValue;
    }

    public static void main(String[] args) {
        Value<String> value = new Value<>();
        value.getOrDefault("22", Value.<String>defaultValue()); // 1.7
        value.getOrDefault("22", Value.defaultValue()); // 1.8
    }
}  