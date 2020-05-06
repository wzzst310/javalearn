package com.wjjzst.springcloud.provider.feign.file.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 2:30 下午
 * @desc:
 */
@RestController
public class FeignUploadFileController {
    @PostMapping(value = "/uploadFile/server", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadServer(MultipartFile file) throws Exception {
        return file.getOriginalFilename();
    }

    @RequestMapping(value = "/createImageCode")
    public byte[] createImageCode(@RequestParam("imageKey") String imageKey) throws Exception {
        // InputStream inputStream = this.getClass().getResourceAsStream("numberImageCode.png");
        // ClassPathResource classPathResource = new ClassPathResource("numberImageCode.png");
        // InputStream inputStream =classPathResource.getInputStream();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("numberImageCode.png");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        return bytes;
    }
}
