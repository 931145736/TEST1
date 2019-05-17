package com.ws.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * 继承并重写post阶段处理异常信息的过滤器的过滤器类型以及对应的生命周期
 */

@Component
public class ErrorExtFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 30;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter filter = (ZuulFilter) ctx.get("failed.filter");
        if(filter!=null&&filter.filterType().equals("post")){
            return true;
        }
        return false;
    }
}
