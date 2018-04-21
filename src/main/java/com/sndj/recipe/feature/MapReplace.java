package com.sndj.recipe.feature;


/**
 * 在Java7及以前，要想替换Map中的映射关系可通过put(K key, V value)方法实现，
 * 该方法总是会用新值替换原来的值．为了更精确的控制替换行为，Java8在Map中加入了两个replace()方法，分别如下：

 replace(K key, V value)，只有在当前Map中**key的映射存在时**才用value去替换原来的值，否则什么也不做．
 replace(K key, V oldValue, V newValue)，只
 有在当前Map中**key的映射存在且等于oldValue时**才用newValue去替换原来的值，否则什么也不做．
 */
public class MapReplace {
}
