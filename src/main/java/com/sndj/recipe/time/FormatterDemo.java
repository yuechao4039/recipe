package com.sndj.recipe.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yuechao 2018/5/6
 */
public class FormatterDemo {

    public static void main(String[] args) {
        DateTimeFormatter dtf;

        //dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);

        System.out.println(strDate);

        LocalDateTime newLdt = ldt.parse(strDate, dtf);
        System.out.println(newLdt);
    }
}
