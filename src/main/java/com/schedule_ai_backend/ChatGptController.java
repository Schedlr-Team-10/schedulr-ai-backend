package com.schedule_ai_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedlrai")
@CrossOrigin("*")
public class ChatGptController {

    private final GPTService gptService;

    @Autowired
    public ChatGptController(GPTService gptService) {
        this.gptService = gptService;
    }

    @GetMapping("/generate")
    public String generate(@RequestParam String description) {
        try {
            return GPTService.generateResponse(description);
        } catch (Exception e) {
            return "Error generating description: " + e.getMessage();
        }
    }

    @GetMapping("/health")
    public String health(){
        return "Good";
    }
}
