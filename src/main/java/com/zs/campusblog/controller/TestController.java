package com.zs.campusblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zs
 * @date 2019/12/29
 */

@RestController
public class TestController {
    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }
}
