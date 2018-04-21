package com.sndj.recipe.innerclssss;

import lombok.Data;

/**
 * @author yuechao 2018/4/20
 */
@Data
public class ParentDemo extends  Top{

    private String pname1;

    String pname2;

    protected  String pname3;

    public String pname4;


    public class ParentBean {

        public void sayHello() {
            System.out.println("Parent Bean");
        }

    }
}
