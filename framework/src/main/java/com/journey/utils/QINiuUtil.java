package com.journey.utils;

import com.google.gson.Gson;
import com.journey.config.properties.QINiuProperties;
import com.journey.enums.QINiuPathEnum;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 七牛云工具
 *
 * @author hy
 * @version 1.0
 */
@Component
public class QINiuUtil {
    @Resource
    private QINiuProperties qiNiuProperties;

    /**
     * 上传文件
     *
     * @param file 文件
     */
    public String upload(MultipartFile file, QINiuPathEnum pathEnum) {
        try {
            Configuration configuration = qiNiuProperties.getConfiguration();
            configuration.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
            UploadManager uploadManager = new UploadManager(configuration);
            String key = FileUtil.generateFilePath(file, pathEnum.getPath(), null, null);
            Auth auth = Auth.create(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey());
            String upToken = auth.uploadToken(qiNiuProperties.getBucket());
            Response response = uploadManager.put(file.getInputStream(), key, upToken, null, null);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return qiNiuProperties.getEndpoint() + defaultPutRet.key;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}