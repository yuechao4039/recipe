package com.sndj.recipe.chain;

public class HtmlFilter implements Filter {
    @Override  
    public void doFilter(Request request, Response response, FilterChain chain) {  
        request.setRequestStr(request.getRequestStr().replace('<', '[').replace('>', ']') + "---HtmlFilter");  
        chain.doFilter(request, response, chain);  
        response.setResponseStr(response.getResponseStr() + "---HtmlFilter");  
    }  
}  