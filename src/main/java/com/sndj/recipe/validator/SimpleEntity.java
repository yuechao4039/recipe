//package com.sndj.recipe.validator;
//
//import lombok.Data;
//import org.hibernate.validator.constraints.Length;
//
//import javax.validation.constraints.*;
//import java.util.Date;
//
//
//@Data
//public class SimpleEntity {
//
//    @NotBlank(message = "名字不能为空或者空串")
//    @Length(min = 2, max = 10, message = "名字必须由2~10个字组成")
//    private String name;
//
//    @Past(message = "时间不能晚于当前时间")
//    private Date date;
//
//    @Email(message = "邮箱格式不正确")
//    private String email;
//
//    @Pattern(regexp = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,10}", message = "密码必须是5~10位数字和字母的组合")
//    private String password;
//
//    @AssertTrue(message = "字段必须为真")
//    private boolean valid;
//
//}