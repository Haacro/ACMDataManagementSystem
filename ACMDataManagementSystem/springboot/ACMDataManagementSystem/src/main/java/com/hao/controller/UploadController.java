package com.hao.controller;

import com.hao.pojo.Result;
import com.hao.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.font.MultipleMaster;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        log.info("文件上传，文件名:{}", file.getOriginalFilename());

        //调用阿里云OSS工具类进行文件上传
        String url = aliOSSUtils.upload(file);
        log.info("文件上传完成，文件访问的url:{}",url);

        return Result.success(url);
    }

    //本地存储文件
    /*@PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传:{},{},{}", name, age, image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();
        //构造唯一的文件名 - uuid(通用唯一识别码)
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名:{}", newFileName);
        //将文件存储在服务器的磁盘目录中
        image.transferTo(new File("C:\\Users\\26833\\Desktop\\upload\\" + newFileName));
        return Result.success();
    }*/
}
