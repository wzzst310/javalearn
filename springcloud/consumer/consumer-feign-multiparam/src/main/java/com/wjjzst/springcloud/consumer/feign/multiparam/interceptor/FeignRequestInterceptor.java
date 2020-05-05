package com.wjjzst.springcloud.consumer.feign.multiparam.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 11:15 上午
 * @desc: 统一将Feign的GET请求的实体类参数拆分成?key1=value1&key2=value2
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate template) {
        // feign 不支持 GET 方法传POJO,json body转query
        // http://provider-feign-multiparam/user/add?name=wjj&id=1&age=10
        if (template.method().equals("GET") && template.body() != null) {
            try {
                JsonNode jsonNode = objectMapper.readTree(template.body());
                // Request.Body body = null;
                // template.body(body);
                System.out.println(template.body());
                // 不把body设置为空的还是发的post请求
                template.body(null, Charset.defaultCharset());
                Map<String, Collection<String>> queries = new HashMap<>();
                buildQuery(jsonNode, "", queries);
                template.queries(queries);
            } catch (IOException e) {
                // 实际应为记录错误日志等
                e.printStackTrace();
            }
        }
    }

    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        if (!jsonNode.isContainerNode()) { // 叶子节点
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> values = queries.get(path);
            if (null == values) {
                values = new ArrayList<>();
                queries.put(path, values);
            }
            values.add(jsonNode.asText());
        }
        if (jsonNode.isArray()) {    // 数组节点
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else {   // 根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }

        }
    }

}
