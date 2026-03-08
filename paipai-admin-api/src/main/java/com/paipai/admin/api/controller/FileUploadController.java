package com.paipai.admin.api.controller;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.paipai.core.service.FileStorageService;
import com.paipai.security.res.RestRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/file")
@RestController
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<RestRes<String>> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestRes.error("文件为空！"));
        }
        String originalFilename = file.getOriginalFilename();
        if (Objects.isNull(originalFilename)) {
            originalFilename = "";
        }
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String fileName = new Date().getTime() + "";
        if (lastIndexOf > 0) {
            fileName += originalFilename.substring(lastIndexOf);
        }
        try {
            String addrStr = fileStorageService.uploadFile(BinaryData.fromBytes(file.getBytes()), fileName);
            return ResponseEntity.ok(RestRes.success(addrStr));
        } catch (Exception e) {
            log.error("文件上传失败！", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(RestRes.error("文件上传失败，请联系管理员！"));
        }
    }

}
