package com.wjjzst.springcloud.provider.feign.file.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 2:30 下午
 * @desc:
 */
@RestController
public class FeignUploadFileController {
    @PostMapping(value = "/uploadFile/server",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadServer(MultipartFile file) throws Exception{
        return file.getOriginalFilename();
    }
}
