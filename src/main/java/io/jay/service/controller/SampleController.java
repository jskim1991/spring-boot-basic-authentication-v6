package io.jay.service.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/test")
    public String hi(Authentication user) {
        return "Hello from authenticated endpoint " + user.getName();
    }
}
