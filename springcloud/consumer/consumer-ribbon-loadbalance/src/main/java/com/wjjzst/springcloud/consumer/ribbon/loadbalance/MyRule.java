package com.wjjzst.springcloud.consumer.ribbon.loadbalance;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;

import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/5/14 1:41 上午
 * @desc:
 * 问题: 比方说 我和同事 同时都在开发user模块 提供新接口 并且均在各自电脑启动user微服务
 * dev环境使用的同一个eureka  调试时 我的其他服务会调用到同事user模块 导致获取不到我在user模块开发的新接口
 *
 * 解决办法: 开发环境自定义IRule规则 调用服务的Server的选择应该优先选择本机上启动的服务 (即和注册ip和本机ip一致)
 * 此处 自定义的IRule 继承的RoundRobinRule某些服务Server在本机没有启动 可以通过原本的RoundRobinRule策略去获取调用
 *
 * ps 开发环境 内外网同时存在 多网卡情况下 所有开发应该用同一个网段ip注册服务 方便相互调用
 * 设置如下
 * spring.cloud.inetutils.preferred-networks: 10.236
 * eureka.instance.prefer-ip-address:true
 */
public class MyRule extends RoundRobinRule {
    @Autowired
    private InetUtils InetUtils;

    public Server choose(ILoadBalancer lb, Object key) {
        List<Server> reachableServers = lb.getReachableServers();
        InetUtils.HostInfo firstNonLoopbackHostInfo = InetUtils.findFirstNonLoopbackHostInfo();
        String ipAddress = firstNonLoopbackHostInfo.getIpAddress();
        for (Server reachableServer : reachableServers) {
            ipAddress.equals(reachableServer.getHost());
            return reachableServer;
        }
        return super.choose(lb, key);
    }
}
