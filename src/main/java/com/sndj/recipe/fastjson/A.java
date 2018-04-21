package com.sndj.recipe.fastjson;

public class A {
    private int age;  
    private String name;  
      
    public int getAge() {  
        return age;  
    }  
    public void setAge(int age) {  
        this.age = age;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
      
    public void showInfo(){  
        System.out.println(this.name + ":" + this.age);  
    }  
}  