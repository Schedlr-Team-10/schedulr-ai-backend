package com.schedule_ai_backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/schedlrai")
public class ChatGptController {

    @Autowired
    GPTService gptService;

    @GetMapping("/generate")
    public String generate(@RequestParam String photo, @RequestParam String description) {
        // Prepare the input map for the service method
        Map.Entry<String, String> input = Map.entry(photo, description);

        // Call the service method and return the result
        return gptService.generateResponse(input);
    }

    @GetMapping("/health")
    public String health(){
        return "Good";
    }
}
