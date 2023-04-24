package com.journey.controller;

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
    public String test() {
        return "hello word";
    }

}