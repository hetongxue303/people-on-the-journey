package com.journey.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.journey.config.properties.RSAProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 加密工具类
 *
 * @author hy
 * @version 1.0
 */
@Component
public class RSAUtil {

    @Resource
    private RSAProperties rsa;

    public String encrypt(@NotNull String plaintext) {
        return SecureUtil.rsa(null, rsa.getPublicKey())
                .encryptBase64(plaintext, StandardCharsets.UTF_8, KeyType.PublicKey);
    }

    public String decrypt(@NotNull String ciphertext) {
        return SecureUtil.rsa(rsa.getPrivateKey(), null).decryptStr(ciphertext, KeyType.PrivateKey);
    }

    public Boolean check(@NotNull String plaintext, @NotNull String ciphertext) {
        return Objects.equals(plaintext, decrypt(ciphertext));
    }

}