package com.schedule_ai_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {

    @Value("${app.gpt:}")
    private String token;

    @GetMapping("/gpt")
    public String getToken() {
        return token;
    }
}
