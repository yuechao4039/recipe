转 https://meta.tn/a/2836707cd09abb166241903f3f6ff7632426b1e2204bff06391b2e824bef655d
Java 泛型
2018-05-02 / VIEWS: 8
Java 泛型是 Java 5 引入的一个重要特性，相信大多数 Java 开发者都对此不陌生，但是泛型背后的实现原理和类型擦除还是许多人依然不是很清楚。本文将介绍 Java 泛型的原理和使用，重点阐述容易产生困惑的通配符、类型擦除等问题。

1. Java 泛型
1.1 Java 泛型是什么？
Java 泛型，提供了参数化类型，并且提供了编译时强类型检查。泛型可以让我们很简单地支持不同类型，在 Java 集合框架中泛型广泛用以对类型的抽象。

1.2 Java 泛型的好处
提供编译时的强类型检查。可以在编译时发现类型安全问题，不用等到运行时。

避免类型转换。

看下面一段代码：

List list = new ArrayList();
list.add("hello");
String s = (String) list.get(0); // type cast to String
如果使用泛型的话，不需要类型转换：

List<String> list = new ArrayList<String>();
list.add("hello");
String s = list.get(0);   // no cast
可以实现通用的算法。通用算法可以处理不同类型的集合，可以进行自定义，并且类型安全且易于阅读。
2. 泛型类型与泛型方法
2.1 泛型类型
泛型类型是指泛型类或泛型接口。为了理解泛型类型的概念，看下面这个例子。

先定义一个简单的 Box 类：

public class Box{
    private String object;

    public void set(String object){ this.object = object; }
    public String get(){ return object; }
}
上面代码中的 Box 只能存放 String 类型的元素，如果想存放 Integer 等其他类型的元素，则必须重写另外一个 Box，代码不能复用。下面再看使用泛型后的 Box：

public class Box<T>{
    // T stands for "Type"
    private T t;

    public void set(T t){ this.t = t; }
    public T get(){ return t; }
}
现在 Box 可以存放除基本类型外的任何类型了。使用 T 类型代替 String 类型，按照惯例，类型参数名是一个大写字母，常见的类型参数名如下：

E - Element（在 Java 集合框架中广泛运用）

K - Key

N - Number

T - Type

V - Value

2.2 泛型类型的原始类型（Raw Types）
原始类型（Raw Types）是没有指定参数类型的泛型类或泛型接口。例如，对于上面提到的  Box<T> 泛型类：

Box rawBox = new Box();
Box 就是  Box<T> 的原始类型，原始类型一般出现在旧版代码中，因为大量的 API 在 Java 5 之前不是通用的。原始类型和泛型类型也可以转换：

// generic type to raw type
Box<String> stringBox = new Box<>();
Box rawBox = stringBox;               // OK
rawBox.set(8);  // warning: unchecked invocation to set(T)

// raw type to generic type
Box rawBox = new Box();           // rawBox is a raw type of Box<T>
Box<Integer> intBox = rawBox;     // warning: unchecked conversion
2.3 泛型方法
泛型方法是指有它们自己参数化类型的方法。类型参数在一对尖括号之间，并且在方法返回类型之前。

下面 Util 类有一个泛型方法：

public class Util{
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2){
        return p1.getKey().equals(p2.getKey()) &&
               p1.getValue().equals(p2.getValue());
    }
}

public class Pair<K,V>{

    private K key;
    private V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){ return key; }
    public V getValue(){ return value; }
}
通常调用泛型方法的方式如下：

Pair<Integer, String> p1 = new Pair<>(1, "apple");
Pair<Integer, String> p2 = new Pair<>(2, "pear");
boolean same = Util.<Integer, String>compare(p1, p2);

// 如果在 Java 7 以上版本，利用类型推断可以简写为
boolean same = Util.compare(p1, p2);
3. 有界类型参数 (Bounded Type Parameters)
很多时候我们都想限制参数类型的边界，例如在对比两个对象的方法中，想确保方法参数都是 Comparable 的。声明有界类型参数（Bounded Type Parameters

），格式为  T extends Class & Interface1 & ... & InterfaceN 。

public static <T extends Comparable<T>> int compare(T t1, T t2){
 return t1.compareTo(t2);
}
这样当我们传递的参数没有实现 Comparable 接口，会有编译时错误。有界类型参数同样可以用于泛型类和泛型接口中，而且支持多个边界，例如  <T extends A & B & C> ，只允许最多一个 Class 边界，而且如果有一个 Class 边界，Class 边界必须在最前面。

4. 通配符
Java 泛型中问号  ? 是通配符，表示未知类型。通配符可以用于参数、属性、局部变量或返回值的类型，但是不能用于泛型方法调用或创建泛型类实例的类型参数。

4.1 无界通配符 (Unbounded Wildcards)
单独使用  ? 表示无界通配符，例如  List<?> ，表示未知类型的 list。下面两个场景适合使用无界通配符：

如果想写一个方法，只用到 Object 类中的功能，即用  List<?> 代替  List<Object> 。

当使用到的泛型类型的方法不依赖参数类型时，例如只用到 List.size 或 List.clear 方法。事实上，  Class<?> 非常常见也是因为  Class<T> 中的大多数方法都不依赖 T。

4.2 上限通配符 (Upper Bounded Wildcards)
上限通配符可以放宽对变量的限制。语法为  ? extends SuperType ，SuperType 可以是类或接口。例如，如果想写一个对  List<Integer> ,  List<Double> ,  List<Number> 都适用的方法，可以用  List<? extends Number> ：

public static double sumOfList(List<? extends Number> list){
    double s = 0.0;
    for (Number n : list)
        s += n.doubleValue();
    return s;
}
4.3 下限通配符 (Lower Bounded Wildcards)
下限通配符可以限制为特定类型或该类型的父类型。语法为  ? super SubType 。例如，想写一个方法添加 Integer 对象到 list 中，可以是  List<Integer> ,  List<Number> 和 List<Object> ，可以用  List<? super Integer> ：

public static void addNumbers(List<?super Integer> list){
    for (int i = 1; i <= 10; i++) {
        list.add(i);
    }
}
4.4 泛型的继承与子类型
泛型有个常见的误解：Integer 是 Number 的子类型，所以  Box<Integer> 也是 Box<Number> 的子类型。 但是其实  Box<Integer> 和  Box<Number> 并没有直接关系。

public void boxTest(Box<Number> n){ /* ... */ }
// 如果传 Box<Integer> 会出现编译错误

泛型类的继承，可以看 Collection 的关系，  ArrayList<String> 是  List<String> 的子类型：


上面提到  Box<Integer> 和  Box<Number> 都是 Object 子类，但是它们还有个共有的父类型  Box<?> ，同理  List<Number> 和  List<Integer> 的父类型为  List<?> 。


至于上限通配符和下限通配符间的关系，见下图：


4.5 通配符捕获 (Wildcard Capture)
有些时候，编译器会推测通配符的类型，例如  List<?> 类型，在某些代码中编译器从代码推断出具体的类型，这种场景就是通配符捕获。大多数情况下，我们都不需要关心通配符捕获，除非看到错误信息中包含“CAP#”。

下面代码会产生捕获错误：

public class WildcardError{
    void foo(List<?> i){
        i.set(0, i.get(0)); // 错误: 不兼容的类型: Object无法转换为CAP#1
    }
}
上面代码中的错误一开始可能觉得莫名其妙，先看看错误信息：i.set(int, capture<?>) 需要的参数类型为  int,CAP#1 ，而实际传入的为  int,Object ，编译器将 i.get(0) 返回的类型推断为 Object。当调用  List.set(int, E) 时，编译器无法确认传入的类型与 List 的元素类型一致，虽然我们人为知道这处调用的类型是一致的。

我们可以额外加一个泛型方法来避免编译错误：

public class WildcardFixed{
    void foo(List<?> i){
        fooHelper(i);
    }

    // Helper method created so that the wildcard can be captured
    // through type inference.
    private <T> void fooHelper(List<T> l){
        l.set(0, l.get(0)); // 传入参数也为 T，编译器推断为 CAP#1
    }
}
下面再看一个更复杂的例子：

public class WildcardErrorBad{
    void swapFirst(List<? extends Number> l1, List<? extends Number> l2){
      Number temp = l1.get(0);
      l1.set(0, l2.get(0)); // 错误: 不兼容的类型: Number无法转换为CAP#1
      l2.set(0, temp);     // 错误: 不兼容的类型: Number无法转换为CAP#1
    }
}
这个例子试图执行一个不安全的操作，看下面调用的场景：

List<Integer> li = Arrays.asList(1, 2, 3);
List<Double>  ld = Arrays.asList(10.10, 20.20, 30.30);
swapFirst(li, ld);
虽然  List<Integer> 和  List<Double> 都符合  List<? extends Number> 类型，但是 List<Integer> 列表中存放 Double 类型的元素显然不正确，所以也无法添加其他泛型方法来解决这个问题。

4.6 PECS 原则
在学习泛型的过程，一个容易困惑的问题是如何什么时候用上限通配符或下限通配符。下面先分析两者的具体使用区别。

对于  List<? extends Number> 类型，可以执行哪些操作呢？

List<? extends Number> list = new ArrayList<Integer>();

Number first = list.get(0); // OK
list.add(null); // OK
Number number = 1;
list.add(number); // 错误: 不兼容的类型: Number无法转换为CAP#1
list.clear(); // OK
list.remove(0); // OK
List<? extends Number> 类型可以添加 null 值，也可以通过泛型方法写入从本身 list 读取的值，但是无法添加新的元素。无法添加新元素的原因，是对于  List<? extends Number> 类型来说，可能是  List<Number> 、  List<Integer> 或  List<Double> 等类型，无法确定新元素的类型与集合里的类型一致，所以编译器会提示报错。所以可以将  List<? extends Number> 类型的列表看作非严格意义上的只读列表。

而对于  List<? super Number> 类型呢：

List<? super Number> list = new ArrayList<Object>();

Number first = list.get(0); // 错误: 不兼容的类型: CAP#1无法转换为Number
list.add(null); // OK
Number number = 1;
list.add(number); // OK
list.clear(); // OK
list.remove(0); // OK
List<? super Number> 类型可以添加 null 值、添加新的元素，也可以删除元素，但是无法读取列表中的值。无法读取列表的原因，是对于  List<? super Number> 类型来说，可能是 List<Number> 也可能是  List<Object> 类型，读取列表元素时不能确定元素类型。所以可以将  List<? super Number> 类型的列表看作只写列表。

上面两个例子中，只读类型相当于生产者（Producer），生产 T，就使用  ? extends T ，只写类型相当于消费者（Consumer），消费 T，就使用  ? super T 。也就是“Producer Extends, Consumer Super”，简称 PECS 原则。

Collections.copy 方法就用到了这个原则，  copy(List<? super T> dest, List<? extends T> src) ，src 列表是只读的，dest 列表是只写的。

通配符的使用建议如下：
只读类型使用上限通配符  ? extends T

只写类型使用下限通配符  ? super T

如果只读类型只用到 Object 的方法，即  List<? extends Object> ，可以用  List<?> 无界通配符

对于同时需要读取和写入的类型，不要使用通配符

上面四条建议都不适用于方法返回值类型。应该避免在返回值中使用通配符，因为这样会强制要求调用者调用时处理通配符。

5. 类型擦除
类型擦除是 Java 泛型中最容易产生困惑的地方，举个很简单的例子，许多人误以为 List<String> 与  List<Integer> 的 Class 类型不一致：

List<String> strList = new ArrayList<>();
List<Integer> intList = new ArrayList<>();
System.out.println(strList.getClass().getName());   // java.util.ArrayList
System.out.println(intList.getClass().getName());   // java.util.ArrayList
System.out.println(strList.getClass() == intList.getClass());   // true
在编译时  List<String> 和  List<Integer> 的类型是不一样的，但是在运行时两者的类型又是一样的，背后的原因就是类型擦除。

Java 泛型添加是为了提供编译时的类型检查和支持泛型编程，并没有运行时的支持。所以 Java 编译器会用类型擦除来删除所有泛型类型检查代码，并在必要时插入强制类型转换。类型擦除确保不为参数化类型创建新类，所以  ArrayList<E> 的 Class 类型还是 java.util.ArrayList ，相应的，泛型也不会增加运行时开销。Java 编译器在应用泛型类型擦除时有以下行为：

将泛型中所有参数化类型替换为泛型边界，如果参数化类型是无界的，则替换为 Object 类型。字节码中没有任何泛型的相关信息。

为了类型安全，在必要时插入类型转换代码。

生成桥接方法来保持泛型类型的多态性。

5.1 参数化类型替换
对于无解参数化类型，类型擦除时会替换为 Object。

下面看单链表中节点类：

public class Node<T>{

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    }

    public T getData(){ return data; }
    // ...
}
经过类型擦除后：

public class Node{

    private Object data;
    private Node next;

    public Node(Object data, Node next){
        this.data = data;
        this.next = next;
    }

    public Object getData(){ return data; }
    // ...
}
对于有界参数化类型，类型擦除时会替换为第一个边界。

如果节点类使用有界参数化类型：

public class Node<Textends Comparable<T>>{

    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    }

    public T getData(){ return data; }
    // ...
}
经过类型擦除后：

public class Node{

    private Comparable data;
    private Node next;

    public Node(Comparable data, Node next){
        this.data = data;
        this.next = next;
    }

    public Comparable getData(){ return data; }
    // ...
}
5.2 类型转换
经过参数化类型替换后，在使用泛型相关内容时，通常需要添加类型转换代码，看下面代码：

Node<String> node = new Node<>("Hello", null);
String data = node.getData();   // 实际上 node.getData() 返回的是 Object 类型
所以编译器还会插入类型转换代码，编译后如下：

Node node = new Node("Hello", null);
String data = (String) node.getData();
5.3 桥接方法
当编译一个类继承泛型类或泛型接口，在类型擦除的过程中编译器会生成一个合成方法，也称为桥接方法。

看下面代码：

interface Comparable<A>{ 
    public int compareTo( A that); 
} 
final class NumericValueimplements Comparable<NumericValue>{ 
    priva te byte value;  
    public NumericValue(byte value){ this.value = value; } 
    public byte getValue(){ return value; }  
    public int compareTo( NumericValue t hat){ return this.value - that.value; } 
}
经过参数化类型替换后，Comparable 接口的 compareTo 方法的参数类型为 Object，而 NumericValue 也需要实现  compareTo(Object) 方法，经过类型擦除后：

interface Comparable{ 
    public int compareTo( Object that); 
} 
final class NumericValueimplements Comparable{ 
    priva te byte value;  
    public NumericValue(byte value){ this.value = value; }  
    public byte getValue(){ return value; }  
    public int compareTo( NumericValue t hat){ return this.value - that.value; } 

    // 新合成的桥接方法
    public int compareTo(Object that){ return this.compareTo((NumericValue)that);  } 
}
类型擦除后  NumericValue.compareTo(NumericValue) 方法不再是接口的实现方法，这是类型擦除的一个副作用：两个方法（在接口和实现类中）在类型擦除之前具有相同的签名，而在类型擦除之后具有不同的签名。

为了让 NumericValue 依然正确地实现 Comparable 接口，编译器添加了一个桥接方法，和接口的签名相同，桥接方法委托给实现类中的原始方法。

虽然存在桥接方法，但是一般情况下，编译器不允许我们调用桥接方法：

NumericValue value = new NumericValue((byte) 0);
value.compareTo(value); // OK
value.compareTo("abc"); // error
但是，还有两种方式可以调用桥接方法：使用原始类型（Raw Types）或反射。但是桥接方法中有类型转换，所以传其他类型会有运行时报错。下面是使用原始类型的例子：

Comparable comparable = new NumericValue((byte) 0);
comparable.compareTo(comparable); // OK
comparable.compareTo("abc");    // OK at compile time, throws ClassCastException at run time
6. 泛型的限制
6.1 不能用基本类型实例化泛型
class Pair<K,V>{
    private K key;
    private V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }
    // ...
}

Pair<int, char> p = new Pair<>(8, 'a');  // compile-time error

Pair<Integer, Character> p = new Pair<>(8, 'a'); // ok，because of autoboxing
6.2 不能创建参数化类型的实例
不能创建参数化类型的实例，但是可以用反射创建：

public static <E> void append(List<E> list, Class<E> cls)throws Exception {
    E e1 = new E(); // error
    E e2 = cls.newInstance(); // ok
    list.add(e2);
}
6.3 不能将静态属性声明为泛型类型
类的静态属性是类级别的属性，被该类所有实例共享，所以不允许静态属性是参数化类型：

public class MobileDevice<T>{
    private static T os; // compile-time error, if has MobileDevice<Phone> and MobileDevice<Pc> instance, can not confirm the type of os.
}
6.4 不能对参数化类型使用 Casts 或 instanceof
不能 Casts 为参数化类型，除非是无界通配符类型：

List<Integer> li = new ArrayList<>();
List<?> list = li;
List<Number> ln = (List<Number>) li;    // compile-time error
但是有些场景，编译器知道参数化类型是合法的，也会运行类型转换：

List<String> l1 = new ArrayList<>();
ArrayList<String> l2 = (ArrayList<String>)l1;  // OK
因为类型擦除，无法确定运行时参数化类型具体是什么类型，所以无法使用 instanceof 校验类型：

public static <E> void rtti(List<E> list){
    if (list instanceof ArrayList<Integer>) {  // compile-time error
        // ...
    }
    if (list instanceof ArrayList<?>) {  // OK; instanceof requires a reifiable type
        // ...
    }
}
6.5 不能创建参数化类型的数组
ArrayList<String>[] arrayOfList = new ArrayList<String>[3]; // compile-time error
6.6 不能创建、捕捉或抛出参数化类型的对象
泛型类不能直接或间接地继承  Throwable 类：

// Extends Throwable indirectly
class MathException<T>extends Exception{ /* ... */ }    // compile-time error

// Extends Throwable directly
class QueueFullException<T>extends Throwable{ /* ... */ } // compile-time error
也无法捕捉参数化类型的异常：

public static <T extends Exception, J> void execute(List<J> jobs){
    try {
        for (J job : jobs)
            // ...
    } catch (T e) {   // compile-time error
        // ...
    }
}
但是，可以在 throws 语句中使用参数化类型：

class Parser<Textends Exception>{
    public void parse(File file)throws T {     // OK
        // ...
    }
}
6.7 不能重载参数类型为相同原始类型的方法
不能有两个重载方法，当他们的方法签名在类型擦除后是一样的。

public class Example{
    public void print(List<String> list){}
    public void print(List<Integer> list){}
}