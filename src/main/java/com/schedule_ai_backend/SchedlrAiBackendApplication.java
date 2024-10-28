package com.schedule_ai_backend;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
public class SchedlrAiBackendApplication {

	@Autowired
	private static PostRepository postRepository = null;

	public SchedlrAiBackendApplication(PostRepository postRepository) {
		SchedlrAiBackendApplication.postRepository = postRepository;
	}

	public static void main(String[] args) throws Exception {

		SpringApplication.run(SchedlrAiBackendApplication.class, args);

		while(true)
		{
			System.out.println("Enter the id # of the post you would like to create a caption for: ");


			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt();
			sc.nextLine();

			Map.Entry<String, String> requested = getPhotoAndDescription(num);

			String response = generateResponse(requested);

			System.out.println("You response: " + response);

			addCaption(num,response);

			System.out.println("Another id you would like to check?(y,n)");
			sc.reset();
			String exit = sc.nextLine();

			if(exit.equalsIgnoreCase("n")) {
				break;
			}
			else if(exit.equalsIgnoreCase("y")){
				throw new Exception("This is not a supported option");
			}
		}

		System.out.println("The application has finished...");
	}

	static Map.Entry<String,String> getPhotoAndDescription(int id) throws Exception {

		Post thispost;

		if(postRepository.findById(id).isPresent())
		{
			thispost = postRepository.findById(id).get();
		}
		else {
			throw new Exception("The id you are looking for doesn't exist!!!");
		}

		String image = thispost.getImage();
		String description = thispost.getDescription();

        return new AbstractMap.SimpleEntry<>(image,description);
	}

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

	static void addCaption(int id, String caption) throws Exception {
		Post thispost;

		if(postRepository.findById(id).isPresent())
		{
			thispost = postRepository.findById(id).get();
		}
		else {
			throw new Exception("The id you are looking for doesn't exist!!!");
		}

		thispost.setCaption(caption);

		postRepository.save(thispost);
	}
}
