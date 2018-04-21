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
