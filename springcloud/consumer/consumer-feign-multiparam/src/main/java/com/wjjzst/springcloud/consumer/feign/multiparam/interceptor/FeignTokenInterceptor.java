package com.wjjzst.springcloud.consumer.feign.multiparam.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 10:40 上午
 * @desc: Feign统一Token拦截器, 类似灰度中 不停的从上一个服务的请求Header拿gray参数 传递到下一个微服务 确保所有的灰度请求都是转发到灰度服务
 */
@Component
public class FeignTokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (null ==getHttpServletRequest()){
            // 实际应为记录错误日志等
            return;
        }
        // 将获取的Token对应的值往下面传
        requestTemplate.header("oauthToken",getHeaders(getHttpServletRequest()).get("oauthToken"));
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }


    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
