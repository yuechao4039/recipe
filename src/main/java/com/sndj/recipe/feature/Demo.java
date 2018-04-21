package com.sndj.recipe.feature;

/**
 * merge()
 该方法签名为merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)，作用是：

 如果Map中key对应的映射不存在或者为null，则将value（不能是null）关联到key上；
 否则执行remappingFunction，如果执行结果非null则用该结果跟key关联，否则在Map中删除key的映射．
 参数中BiFunction函数接口前面已经介绍过，里面有一个待实现方法R apply(T t, U u)．

 merge()方法虽然语义有些复杂，但该方法的用方式很明确，一个比较常见的场景是将新的错误信息拼接到原来的信息上，比如：

 map.merge(key, newMsg, (v1, v2) -> v1+v2);


 compute()
 该方法签名为compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)，
 作用是把remappingFunction的计算结果关联到key上，如果计算结果为null，则在Map中删除key的映射．

 要实现上述merge()方法中错误信息拼接的例子，使用compute()代码如下：

 map.compute(key, (k,v) -> v==null ? newMsg : v.concat(newMsg));



 computeIfAbsent()
 该方法签名为V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)，
 作用是：只有在当前Map中不存在key值的映射或映射值为null时，才调用mappingFunction，
 并在mappingFunction执行结果非null时，将结果跟key关联．

 Function是一个函数接口，里面有一个待实现方法R apply(T t)．

 computeIfAbsent()常用来对Map的某个key值建立初始化映射．比如我们要实现一个多值映射，
 Map的定义可能是Map<K,Set<V>>，要向Map中放入新值，可通过如下代码实现：

 Map<Integer, Set<String>> map = new HashMap<>();
 // Java7及以前的实现方式
 if(map.containsKey(1)){
 map.get(1).add("one");
 }else{
 Set<String> valueSet = new HashSet<String>();
 valueSet.add("one");
 map.put(1, valueSet);
 }
 // Java8的实现方式
 map.computeIfAbsent(1, v -> new HashSet<String>()).add("yi");
 使用computeIfAbsent()将条件判断和添加操作合二为一，使代码更加简洁．



 computeIfPresent()
 该方法签名为V computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)，作用跟computeIfAbsent()相反，即，只有在当前Map中存在key值的映射且非null时，才调用remappingFunction，如果remappingFunction执行结果为null，则删除key的映射，否则使用该结果替换key原来的映射．

 这个函数的功能跟如下代码是等效的：

 // Java7及以前跟computeIfPresent()等效的代码
 if (map.get(key) != null) {
 V oldValue = map.get(key);
 V newValue = remappingFunction.apply(key, oldValue);
 if (newValue != null)
 map.put(key, newValue);
 else
 map.remove(key);
 return newValue;
 }
 return null;
 */
public class Demo {
}
