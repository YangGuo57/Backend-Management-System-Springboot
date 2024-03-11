package com.yguo57.controller;

import com.yguo57.pojo.Result;
import com.yguo57.utils.OssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        // save content to local
        String originalFilename = file.getOriginalFilename();
        // ensure file name unique so that preventing file being overwritten
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //file.transferTo(new File("/Users/guoyang/Desktop/SpringBoot/files/" + filename));
        String url = OssUtil.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }
}
