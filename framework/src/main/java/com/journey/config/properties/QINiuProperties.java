package com.journey.config.properties;

import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hy
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "qiniu.config")
public class QINiuProperties {

    /**
     * 密钥
     */
    private String accessKey;

    /**
     * 公钥
     */
    private String secretKey;

    /**
     * 桶
     */
    private String bucket;

    /**
     * 地区
     */
    private Configuration configuration = new Configuration(Region.huadongZheJiang2());

    /**
     * 回显地址
     */
    private String endpoint;

}