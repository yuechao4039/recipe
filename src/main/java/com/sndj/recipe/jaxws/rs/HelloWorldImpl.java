package com.sndj.recipe.jaxws.rs;

import javax.jws.WebService;

//Service Implementation  
@WebService(endpointInterface = "com.sndj.recipe.jaxws.rs.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}  