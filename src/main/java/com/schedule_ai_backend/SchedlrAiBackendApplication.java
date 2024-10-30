package com.schedule_ai_backend;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SchedlrAiBackendApplication {


	public static void main(String[] args) throws Exception {

		SpringApplication.run(SchedlrAiBackendApplication.class, args);
	}

}
