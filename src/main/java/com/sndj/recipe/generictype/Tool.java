package com.sndj.recipe.generictype;

/**
 * @author yuechao 2018/5/2
 */
public class Tool {

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        boolean same = Util.<Integer, String>compare(p1, p2);
    }
}

class Util{
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2){
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public static <T extends Comparable<T>> int compare(T t1, T t2){
        return t1.compareTo(t2);
    }
}

class Pair<K,V>{

    private K key;
    private V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){ return key; }
    public V getValue(){ return value; }
}