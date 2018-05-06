package com.sndj.recipe.time;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author yuechao 2018/5/6
 */
public class InstantDemo {

    public static void main(String[] args) {
        new InstantDemo().test2();
    }

    public void test2(){
        Instant ins = Instant.now();  //默认使用 UTC 时区
        System.out.println(ins);

        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(ins.getNano());

        Instant ins2 = Instant.ofEpochSecond(5);
        System.out.println(ins2);
    }
}
