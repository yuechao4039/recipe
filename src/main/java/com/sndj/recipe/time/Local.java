package com.sndj.recipe.time;

import java.time.LocalDateTime;

/**
 * @author yuechao 2018/5/6
 */
public class Local {

    public static void main(String[] args) {
        new Local().test1();
    }

    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ld2 = LocalDateTime.of(2016, 11, 21, 10, 10, 10);
        System.out.println(ld2);

        LocalDateTime ldt3 = ld2.plusYears(20);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ld2.minusMonths(2);
        System.out.println(ldt4);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }
}
