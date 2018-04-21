package com.sndj.recipe.feature;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Demo2 {


    public static class Car {
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }

        @Override
        public String toString() {
            return "haha";
        }
    }

    public static void main(String[] args) {
        // 第一种方法引用类型是构造器引用，使用Class::new 语法或针对泛型的Class<T>::new，请注意构造函数没有参数。
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );

        // 第二种是静态方法引用，使用Class:static_method语法。需要注意的是该方法接收了一个Car类型的参数。
        cars.forEach(Car::collide);

        // 第三种是针对任意对象的实例方法引用，使用Class:method语法。请注意该方法没有接收任何参数。
        cars.forEach(Car::repair);

        // 最后一种是特定类实例的实例方法引用，使用instance::method语法。注意该方法接收一个Car类型的参数。
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);

        System.out.println("=================");
        System.out.println(Car.create(new Supplier<Car>() {
            @Override
            public Car get() {
                return new Car();
            }
        }));;

        System.out.println(Car.create(() -> {return new Car();}));
        System.out.println(Car.create(() ->  new Car()));
        System.out.println(Car.create(Car::new));
    }

    // 1005 0036 0818
}
