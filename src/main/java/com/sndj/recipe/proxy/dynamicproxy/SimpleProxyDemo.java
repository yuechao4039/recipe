package com.sndj.recipe.proxy.dynamicproxy;

public class SimpleProxyDemo {
      public static void main(String[] args) throws SecurityException, NoSuchMethodException {
          CoordinateImpl c = new CoordinateImpl();
          DynamicProxyHandler proxyHandler = new DynamicProxyHandler(c);

          Coordinate proxyClass = (Coordinate)proxyHandler.newProxyInstance();
          System.out.println(proxyClass.getClass().getName());
          proxyClass.coordinate();
      }
}