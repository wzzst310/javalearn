package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

/**
 * @author: Wjj
 * @create: 2020/9/19 12:16 下午
 * @Description
 */
public class RabbitMqQueueCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(MyConditonAnnotaion.class.getName());

        String propertyName = String.valueOf(attributes.get("name"));

        String[] matchValues = (String[]) attributes.get("matchValues");
        return Arrays.asList(matchValues).contains(propertyName);
    }
}
