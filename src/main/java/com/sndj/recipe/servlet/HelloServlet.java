package com.sndj.recipe.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello", "/helloservlet"})
public class HelloServlet extends HttpServlet {
    public static final String ss = "aa";
    public final String bb = "bb";
    private String cc = "cc";
    String dd = "dd";

    public HelloServlet() {
        System.out.println("HelloServlet invoked !!!");
    }
    @Override
    public void init() throws ServletException {
        System.out.println("init invoked !!!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long lastModified = getLastModified(request);
        if (lastModified == -1) {
            // servlet doesn't support if-modified-since, no reason
            // to go through further expensive logic
            //doGet(req, resp);
        } else {
            long ifModifiedSince = request.getDateHeader("If-Modified-Since");
            if (ifModifiedSince < lastModified) {
                // If the servlet mod time is later, call doGet()
                // Round down to the nearest second for a proper compare
                // A ifModifiedSince of -1 will always be less


            } else {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            }
        }
        try (PrintWriter out = response.getWriter();) {
            out.println("Hello world");
            out.print(System.currentTimeMillis());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("destroy invoked !!!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected long getLastModified(HttpServletRequest req) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTimeInMillis();
    }
}
