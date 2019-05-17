package com.ws.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

public class PreFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PreFilter.class);

    /**
     * 过滤器的类型，决定过滤器在请求的哪个生命周期执行
     * pre：代表请求被路由之前
     * routing: 代表在路由请求时被调用
     * post: 在routing和error过滤器之后被调用
     * error: 处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "post";
    }

    /**
     * 过滤器的执行顺序，当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值依次执行，值越小顺序越靠前
     * @return
     */

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行 TRUE代表是
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        /*RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("send{} request to {}",request.getMethod(),request.getRequestURI().toString());
        Object accessToken = request.getParameter("accessToken");
        if(accessToken==null){
            logger.warn("token is null");
            ctx.setSendZuulResponse(false); //如果没有token，不进行路由，返回401
            ctx.setResponseStatusCode(401);
            return null;
        }
        logger.info("accessToken ok");
        return null;*/
        //在过滤器中处理异常
        logger.info("this is a pre filter ,it will throw a RuntimeException");
       // RequestContext ctx = RequestContext.getCurrentContext();
         //try{
             doSomeThing();
         //}catch (Exception e){
         //    ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
         //    ctx.set("error.exception",e);
        //     ctx.set("error.message","自定义异常");
        // }
        return null;
    }

    public void doSomeThing(){
        throw new RuntimeException("this is a test Exception");
    }
}
