package com.sndj.recipe.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private Validator validator;

    @Override
    public String home(HomeRequest req) {
        req.setName("x");
        Map<String,String> map = new HashMap<String,String>();
        MapBindingResult err = new MapBindingResult(map, HomeRequest.class.getName());
        validator.validate(req, err);
        List<ObjectError> list = err.getAllErrors();
        for(ObjectError objErr : list){
            System.out.println(objErr.getDefaultMessage());
        }
        return "string";
    }


}
