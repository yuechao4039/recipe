package com.sndj.recipe.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yuechao 2018/5/6
 */
public class DateUtil {


    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");


    public static java.sql.Timestamp getTimestamp() {
        return java.sql.Timestamp.valueOf(LocalDateTime.now());
    }

    public static java.sql.Date getSqlDate() {
        return java.sql.Date.valueOf(LocalDate.now());
    }



    public static void main(String[] args) {
        System.out.println(getTimestamp());
        System.out.println(getSqlDate());
    }
}
