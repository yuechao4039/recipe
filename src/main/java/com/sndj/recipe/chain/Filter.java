package com.sndj.recipe.chain;

public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);  
}  