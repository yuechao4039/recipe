package com.sndj.recipe.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextUtil {

    public static Context getInitialContext()  {
        InitialContext context = null;
        try {
            context = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return context;
    }
}
