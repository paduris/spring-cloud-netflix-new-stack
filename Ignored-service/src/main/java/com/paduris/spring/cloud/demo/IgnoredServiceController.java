package com.paduris.spring.cloud.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class IgnoredServiceController {

    @GetMapping
    public String test(){
        return "Ignore Service Called";
    }
    @GetMapping("/allowed")
    public String allowed(){
        return "Allowed endpoint called";
    }
}
