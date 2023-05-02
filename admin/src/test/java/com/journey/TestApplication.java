package com.journey;

import com.journey.utils.RSAUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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
        System.out.println(rsaUtil.encrypt("74ce4a21f159e81638334cbe243cd2cf"));
        System.out.println(rsaUtil.decrypt("XEoOIweIJPHIyjZI6QnS1hoFu7uf3jMieqwI7XPHgOr9hJBv761UQE+W8kNd1htdJGNeLbOHu9" + "/RgmqKrn1dg8KfQuzDTq5SKP27MDTNwwOO47wLaF2OEpj4/En0kqvmETOoKQZ6WHnxm6ht6++dT7qNZ/9Ry9+UyBpi8un5MtA="));
    }

}