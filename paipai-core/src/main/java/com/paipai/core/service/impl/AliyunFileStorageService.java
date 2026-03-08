package com.paipai.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.StaticCredentialsProvider;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.paipai.core.AliyunProperties;
import com.paipai.core.service.FileStorageService;

@Service
public class AliyunFileStorageService implements InitializingBean, DisposableBean, FileStorageService {

    private OSSClient ossClient;

    @Autowired
    private AliyunProperties aliyunProperties;

    /**
     * 上传文件
     * 
     * @param fileData 文件数据
     * @param fileName 文件名称
     * @return 文件访问地址
     */
    @Override
    public String uploadFile(BinaryData fileData, String fileName) {
        String key = this.aliyunProperties.getOssFolder() + "/" + fileName;
        PutObjectRequest putObjectRequest = PutObjectRequest.newBuilder()
                .bucket(this.aliyunProperties.getOssBucketName())
                .key(key)
                .contentType("image/png")
                .body(fileData)
                .build();
        this.ossClient.putObject(putObjectRequest);
        return this.aliyunProperties.getOssBucketUrl() + "/" + key;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CredentialsProvider credentialsProvider = new StaticCredentialsProvider(
                aliyunProperties.getAccesskeyId(),
                aliyunProperties.getAccesskeySecret());

        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(credentialsProvider)
                .endpoint(aliyunProperties.getEndpoint())
                .region(aliyunProperties.getOssRegion());

        this.ossClient = clientBuilder.build();
    }

    @Override
    public void destroy() throws Exception {
        this.ossClient.close();
    }

}
