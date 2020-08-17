package com.wjjzst.springcloud.consumer.feign.dynamictarget.config;

import feign.Feign;
import feign.Request;
import feign.RequestTemplate;
import feign.Target;
import org.springframework.cloud.openfeign.FeignClientFactoryBean;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.cloud.openfeign.Targeter;

/**
 * @author: Wjj
 * @create: 2020/8/17 10:08 上午
 * @Description
 */
public class RouteTargeter implements Targeter {
    /**
     * 服务名以本字符串结尾的，会被置换为实现定位到的单元号
     */
    public static final String CLUSTER_ID_SUFFIX = "CLUSTER-ID";
    public static final String[] CLUSTERS = {"aa", "bb", "cc"};

    @Override
    public <T> T target(FeignClientFactoryBean factory, Feign.Builder feign, FeignContext context,
                        Target.HardCodedTarget<T> target) {

        return feign.target(new RouteTarget<>(target));
    }

    public static class RouteTarget<T> implements Target<T> {
        private Target<T> realTarget;

        public RouteTarget(Target<T> realTarget) {
            super();
            this.realTarget = realTarget;
        }

        @Override
        public Class<T> type() {
            return realTarget.type();
        }

        @Override
        public String name() {
            return realTarget.name();
        }

        @Override
        public String url() {
            String url = realTarget.url();
            if (url.contains(CLUSTER_ID_SUFFIX)) {
                url = url.replace(CLUSTER_ID_SUFFIX, locateCusterId());
            }
            return url;
        }

        /**
         * @return 定位到的实际单元号
         */
        private String locateCusterId() {
            // TODO 你的路由算法在这里
            int num = (int) (Math.random() * 3);
            return CLUSTERS[num];
        }

        @Override
        public Request apply(RequestTemplate input) {
            if (input.url().indexOf("http") != 0) {
                input.target(url());
            }
            return input.request();

        }

    }
}
