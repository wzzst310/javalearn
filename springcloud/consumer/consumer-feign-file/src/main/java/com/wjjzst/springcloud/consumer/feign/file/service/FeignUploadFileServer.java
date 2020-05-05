package com.wjjzst.springcloud.consumer.feign.file.service;

import com.wjjzst.springcloud.consumer.feign.file.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 2:40 下午
 * @desc:
 */
// @FeignClient(value = "provider-feign-file",configuration = FeignMultipartSupportConfig.class)
@FeignClient(value = "provider-feign-file")
public interface FeignUploadFileServer {
    /**
     * 1.produces和consumes必填
     * 2.注意@RequestPart和@RequestParam
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadFile/server",
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUploadServer(@RequestPart(value = "file") MultipartFile file);
}
