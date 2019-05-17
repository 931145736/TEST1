package com.ws.apigateway.filter;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * zuul过滤器的核心处理器，将过滤器类型加入ctx,方便后面判断是否使用过滤器
 */
public class DidiFilterProcesser extends FilterProcessor {

    @Override
    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
        try{
            return super.processZuulFilter(filter);
        }catch (Exception e){
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set("failed.filter",filter); //添加参数
            throw e;
        }
    }
}
