package com.kivicms.catalogue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomError {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello, world";
    }
}
