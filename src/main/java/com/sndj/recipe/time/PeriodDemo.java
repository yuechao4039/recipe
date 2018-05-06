package com.sndj.recipe.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;

/**
 * @author yuechao 2018/5/6
 */
public class PeriodDemo {

    public static void main(String[] args) {
        try {
            new PeriodDemo().test3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test3() throws InterruptedException {
        Instant ins1 = Instant.now();
        Thread.sleep(1000);
        Instant ins2 = Instant.now();
        System.out.println("所耗费时间为：" + Duration.between(ins1, ins2));

        System.out.println("----------------------------------");

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2011, 1, 1);

        Period pe = Period.between(ld2, ld1);
        System.out.println(pe.getYears());
        System.out.println(pe.getMonths());
        System.out.println(pe.getDays());
    }
}
