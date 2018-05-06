package com.sndj.recipe.time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @author yuechao 2018/5/6
 */
public class AjusterDemo {
    public static void main(String[] args) {
        new AjusterDemo().test4();
    }

    public void test4(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if(dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if(dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else{
                return ldt4.plusDays(1);
            }
        });

        System.out.println(ldt5);
    }
}
