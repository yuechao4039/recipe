package com.sndj.recipe.jndi;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.BiConsumer;

@WebServlet(name = "JndiServlet", value = {"/jndi"})
public class JndiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Context context = new InitialContext();

            Hashtable hashtable = context.getEnvironment();
            hashtable.forEach((k, v) -> System.out.println(k + "=" + v));

            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/test");
            Connection conn = ds.getConnection();
            out.println(conn);
            out.println(conn.isClosed());
            out.println("</br>");
            conn.close();

            NamingEnumeration enumss = context.list("");

            while (enumss.hasMore()) {

                NameClassPair ncp = (NameClassPair) enumss.next();

                System.out.println("JNDI name is:" + ncp.getName());

            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
