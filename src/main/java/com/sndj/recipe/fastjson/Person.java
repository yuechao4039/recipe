package com.sndj.recipe.fastjson;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
public class Person {

    @JSONField(name = "AGE", serialize = false)
    private int age;

    @JSONField(name = "FULL NAME", ordinal = 2)
    private String fullName;

    @JSONField(name = "DATE OF BIRTH", ordinal = 1)
    private Date dateOfBirth;
}
