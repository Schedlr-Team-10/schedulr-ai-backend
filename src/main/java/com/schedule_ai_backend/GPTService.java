package com.schedule_ai_backend;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GPTService {
    static String generateResponse(String input){
        OpenAiService service = new OpenAiService("");

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "Enter a prompt here: ");
        messages.add(systemMessage);

        String message = "Generate z description for a post based off these keywords: " + input;
        ChatMessage firstMsg = new ChatMessage(ChatMessageRole.USER.value(), message);
        messages.add(firstMsg);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-4o-mini")
                .messages(messages)
                .build();
        ChatCompletionResult result = service.createChatCompletion(chatCompletionRequest);
        long usedTokens = result.getUsage().getTotalTokens();
        ChatMessage response = result.getChoices().get(0).getMessage();

        messages.add(response);
        System.out.println("Total tokens used: " + usedTokens);

        return response.getContent();
    }
}
