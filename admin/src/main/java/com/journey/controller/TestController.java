package com.journey.controller;

import com.journey.annotation.LogPrint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author hy
 * @version 1.0
 */
@RestController
public class TestController {

    @GetMapping("test")
    @LogPrint("测试接口")
    public String test() {
        return "hello admin";
    }

}