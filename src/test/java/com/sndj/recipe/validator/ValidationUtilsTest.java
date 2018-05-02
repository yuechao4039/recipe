//package com.sndj.recipe.validator;
//
//import junit.framework.TestCase;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Date;
//
//@Slf4j
//public class ValidationUtilsTest {
//
//    @Test
//    public void validateSimpleEntity() {
//        SimpleEntity se = new SimpleEntity();
//        se.setDate(new Date());
//        se.setEmail("123");
//        se.setName("123");
//        se.setPassword("123");
//        se.setValid(false);
//        ValidationResult result = ValidationUtils.validateEntity(se);
//        System.out.println("--------------------------");
//        System.out.println(result);
//        Assert.assertTrue(result.isHasErrors());
//    }
//
//    @Test
//    public void validateSimpleProperty() {
//        SimpleEntity se = new SimpleEntity();
//        ValidationResult result = ValidationUtils.validateProperty(se, "name");
//        System.out.println("--------------------------");
//        System.out.println(result);
//        Assert.assertTrue(result.isHasErrors());
//    }
//
//    @Test
//    public void validateExtendEntity() {
//        ExtendEntity ee = new ExtendEntity();
//        ee.setPassword("212");
//        ValidationResult result = ValidationUtils.validateEntity(ee);
//        System.out.println("--------------------------");
//        System.out.println(result);
//        Assert.assertTrue(result.isHasErrors());
//    }
//}