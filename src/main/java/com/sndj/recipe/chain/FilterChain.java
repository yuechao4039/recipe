package com.sndj.recipe.chain;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {
    private List<Filter> filters = new ArrayList<>();
    private int index = 0;

    public FilterChain addFilter(Filter f) {
        this.filters.add(f);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        if (index == filters.size()) return;

        Filter f = filters.get(index);
        index++;
        f.doFilter(request, response, chain);
    }
}  