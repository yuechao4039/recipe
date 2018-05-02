//package com.sndj.recipe.spring;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
//
//@RestController
//@RequestMapping(value = {"/homepage"})
//public class HomeController {
//
//    @Autowired
//    HomeService homeService;
//
//    @RequestMapping(method = GET, value = {"/home"})
//    public ModelAndView home(HomeRequest req) {
//
//        System.out.println(homeService.home(req));
//        return new ModelAndView("home");
//    }
//}
