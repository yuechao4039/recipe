package com.sndj.recipe.proxy.staticproxy;

public class BenefitProxy implements Benefit {

    private Benefit benefit = new BenefitImpl();

    @Override
    public void benefit() {
        System.out.println("BenefitProxy");
        benefit.benefit();
    }
}
