package com.sndj.recipe.proxy.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo {

    public static void main(String[] args) {

//        Class cls = Proxy.getProxyClass(Foo.class.getClassLoader(), new Class[]{Foo.class});
//
//        try {
//            Foo foo = (Foo)cls.getConstructor(new Class[]{InvocationHandler.class}).newInstance(new InvocationHandler() {
//                @Override
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    System.out.println(proxy.getClass());
//                    System.out.println(method.getName());
//                    System.out.println(args);
//                    return "xxx";
//                }
//            });
//
//            String aa = foo.dosth("upfront payment");
//            System.out.println(aa);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        Foo foo = (Foo)Proxy.newProxyInstance(Foo.class.getClassLoader(), new Class[]{Foo.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return "xx";
            }
        });
        System.out.println(foo.dosth("yy"));
    }
}
