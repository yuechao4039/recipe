内部类
==================

接口中的内部类

public interface InterDemo {
    
    class Aa {

    }
    
    public static class A {

    }

    interface Bb {

    }

    public abstract  static interface  B {

    }
}

1.接口中的内部class默认修饰符是public static
2.接口中的内部interface默认修饰符是public abstract static, 所以类A和接口B前面的修饰符是redundant.


内部中不能含有静态属性.


Let's consider the following example:

List<Integer> test = new ArrayList<Integer>();
Class<? extends List> clazz = test.getClass();
the static type of test (the expression on which getClass() is called) is List<Integer> of which the erasure is List (see type erasure). Note that the dynamic (or runtime) type of test is ArrayList, and the runtime type of clazz will be Class<ArrayList>.

So, Class<? extends |X|> in this case is Class<? extends List>.

> - 默认左对齐 
  :- 左对齐 
  -:右对齐 
  :-:居中

>  -表示列的宽度权重，比如如下，--、-，表示第一列的宽度是第二列的俩倍：
  
  |id|name|
  |:--|:-|
  |1|A1|