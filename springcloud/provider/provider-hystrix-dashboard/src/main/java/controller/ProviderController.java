package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/5/18 1:32 上午
 * @desc:
 */
@RestController
public class ProviderController {
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("getDashboard")
    List<String> getProviderData() {
        List<String> provider = new ArrayList<>();
        provider.add("hystrix dashboard");
        return provider;
    }

    @GetMapping("getHelloService")
    public String getHelloService() {
        return consumerService.getHelloServiceData();
    }
}
