package com.sndj.recipe.jndi;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(name = "LookUpServlet", value = {"/lookup"})
public class LookUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context context = ContextUtil.getInitialContext();
        try {
            Map<String, String> map = (Map<String, String>) context.lookup("map");
            map.forEach((k, v) -> System.out.println(k + "=" + v));


            NamingEnumeration<NameClassPair> en = context.list("map");
            while (en.hasMore()) {

                NameClassPair ncp = (NameClassPair) en.next();

                System.out.println("JNDI name is:" + ncp.getName());

            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
