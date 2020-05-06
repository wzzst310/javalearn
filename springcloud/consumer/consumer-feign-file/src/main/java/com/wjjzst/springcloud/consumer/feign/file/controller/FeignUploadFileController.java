package com.wjjzst.springcloud.consumer.feign.file.controller;

import com.wjjzst.springcloud.consumer.feign.file.service.FeignUploadFileServer;
import feign.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @Author: Wjj
 * @Date: 2020/5/6 2:12 上午
 * @desc:
 */
@RestController
@Api("文件上传")
@RequestMapping("/feign")
public class FeignUploadFileController {
    @Autowired
    private FeignUploadFileServer feignUploadFileServer;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "文件上传", notes = "请选择文件上传")
    public String imageUpload(@ApiParam(value = "文件上传", required = true) MultipartFile file) throws Exception {
        return feignUploadFileServer.fileUploadServer(file);
    }

    @GetMapping(value = "/createImageCode")
    @ApiOperation(value = "获取验证码图片", notes = "获取验证码图片")   // controller层响应二维码图片
    public void createImageCode(@RequestParam("imageKey") String imageKey, HttpServletResponse servletResponse) throws Exception {
        Response response = feignUploadFileServer.createImageCode(imageKey);
        Response.Body body = response.body();
        InputStream fileInputStream = null;
        OutputStream outStream;
        try {
            fileInputStream = body.asInputStream();
            servletResponse.setContentType("image/jpeg"); // 响应图片格式
            outStream = servletResponse.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outStream.write(bytes, 0, len);
            }
            fileInputStream.close();
            outStream.close();
            outStream.flush();
        } catch (Exception e) {

        }
    }
}
