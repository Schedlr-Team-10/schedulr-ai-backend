package com.schedule_ai_backend;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GPTService {


    static String generateResponse(Map.Entry<String,String> input){
        OpenAiService service = new OpenAiService("sk-3zvlYkAtMR1TvCuKVDHBwLdvKgHTauY99WtJ2zyHM7T3BlbkFJ0JkveS-aHxOdFyNab81839MItJaKtY-OadYDiOKdEA");

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "Enter a prompt here: ");
        messages.add(systemMessage);

        String message = "Make a image caption using the information provided and add gun emojis: PHOTO: " + input.getKey() + "DESCRIPTION: " + input.getValue();
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
