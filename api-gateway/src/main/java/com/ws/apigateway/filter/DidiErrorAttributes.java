package com.ws.apigateway.filter;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * 自定义异常返回信息
 */

public class DidiErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map =super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.remove("exception");
        map.remove("status");

        return map;

    }
}
