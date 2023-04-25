package com.journey.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * psa properties
 *
 * @author hy
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "rsa.config")
public class RSAProperties {

    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;

}