package com.paipai.core.service;

import com.aliyun.sdk.service.oss2.transport.BinaryData;

public interface FileStorageService {

    /**
     * 上传文件
     * 
     * @param fileData 文件数据
     * @param fileName 文件名称
     * @return 文件访问地址
     */
    String uploadFile(BinaryData fileData, String fileName);

}
