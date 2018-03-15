package com.sndj.recipe.jaxws;

import javax.xml.ws.Endpoint;


public class Main {

    /**
     * 发布WebService
     * 简单
     */
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/testjws/service/sayHi", new HelloService());
    }

}