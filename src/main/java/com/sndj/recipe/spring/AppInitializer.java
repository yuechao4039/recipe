//package com.sndj.recipe.spring;
//
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import javax.persistence.criteria.Root;
//import javax.servlet.Filter;
//
//public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{RootConfig.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{WebConfig.class};
//    }
//
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[] {new CharacterEncodingFilter("utf-8", true)};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//}
