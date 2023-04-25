package com.journey;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.journey.utils.RSAUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author hy
 * @version 1.0
 */
@SpringBootTest
public class TestApplication {

    @Resource
    private RSAUtil rsaUtil;

    @Test
    void test() {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNIonIhc/DA+y20jUukQ9sji5zxrUcviBXau6VNE29UPwHi795cMlnRc01tfOzEJ1YQJXuYsr9EdJB2kAcYK+s37Q1malMDbIR+7xx+Gku3c7YUabWfHQtXmQZ8EyRJb068Nd2pJwwbenRrqFLZS2UQiF3+D9KYy2rDtVIuNuwPwIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI0iiciFz8MD7LbSNS6RD2yOLnPGtRy+IFdq7pU0Tb1Q/AeLv3lwyWdFzTW187MQnVhAle5iyv0R0kHaQBxgr6zftDWZqUwNshH7vHH4aS7dzthRptZ8dC1eZBnwTJElvTrw13aknDBt6dGuoUtlLZRCIXf4P0pjLasO1Ui427A/AgMBAAECgYA5TG9h4KmjxAEjjB4ZRL1dVjAXwvGetLXOp9Qq8SwqkylvKtZWyGqMKfORPcitxy4KOVZljOhabBTPtXrXyo0naOMKT2Hh1crX919BsCnvl1C5BSSU+flBAyC1GQU+EAPobNe/PLctZrkSut0WSyRoCs7NqE0VAcO6BWjB8QlDYQJBAMDzWz+TjoL2pGnncrsr4PcGjWuSgcxqH64rUWJv4W1Bu7oVXuy8tngjA7qvXzlA1yTS+PXjW0ke02rnug0BGTECQQC7QLYm0c9w67EhRIKMvusTjGBHcYiNTaeK5Jo9HWtUDZ1mJvRQ8o4Fm+rAMUxMTdjXdyb4ol7HxIIrxsUBjQRvAkEAq/GxpQw3eVmV1pJcWZOWnEwIho4+gQ0pQip0EgalmplzUzZsKwdYi+KYFirl7r1re/NNzMFYveZsfN4XnDMYYQJAb4XC9qvO2HoHLMEY36p1Z1cboHFF2qJ9PoOYOP3khlL1g3l/2RYc78pEreKuhmN22hzk4QATYvbucBQaESpjkQJAUCwVGH/mkqVDnevfFgbkRC2FIWfF0BTvMcImA9X/uUDT8c5bWGPfvax6JrxfgGrKhrF31GIHeUZw12y5kFA3LQ==";

//        RSA rsa_o = SecureUtil.rsa();
//        String priKey = rsa_o.getPrivateKeyBase64();
//        String pubKey = rsa_o.getPublicKeyBase64();
//        System.out.println("pubKey = " + pubKey);
//        System.out.println("priKey = " + priKey);
        RSA rsa1 = SecureUtil.rsa(null, publicKey);
        String base64 = rsa1.encryptBase64("123456", StandardCharsets.UTF_8, KeyType.PublicKey);
        System.out.println(base64);

        RSA rsa2 = SecureUtil.rsa(privateKey, null);
        String result = rsa2.decryptStr("DGQ2S4dk1cUqTt6HuUGVhuVNGtFYF43ds/B4rLQqI30cog0n7sCkJxKm1nYf+VBzgJx6DXESqxYcLtVVwvulPasLaG6ltjyjA433a3mgGSb+xDSiWB5YCrkc+Sr3bwiAnebOJ3/7ggqQBgvwIk294SdbM3OcYhtmsGHh6g809cQ=", KeyType.PrivateKey);
        System.out.println(result);
    }

}