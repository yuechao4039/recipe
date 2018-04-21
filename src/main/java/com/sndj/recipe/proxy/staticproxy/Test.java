package com.sndj.recipe.proxy.staticproxy;

public class Test {

    public static void main(String[] args) {


        Benefit benefit = new BenefitProxy();
        benefit.benefit();
    }
}
