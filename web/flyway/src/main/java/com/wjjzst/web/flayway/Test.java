package com.wjjzst.web.flayway;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.lang.model.element.VariableElement;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: Wjj
 * @create: 2020/5/9 12:24 下午
 * @Description
 */
public class Test {
    static class A{
        private Date realOverTime;

        public A(Date realOverTime) {
            this.realOverTime = realOverTime;
        }

        public Date getRealOverTime() {
            return realOverTime;
        }

        public void setRealOverTime(Date realOverTime) {
            this.realOverTime = realOverTime;
        }
    }
    public static void main(String[] args) {
        TimeUnit[] values = TimeUnit.values();
        System.out.println(JSON.toJSONString(values));
    }

}
