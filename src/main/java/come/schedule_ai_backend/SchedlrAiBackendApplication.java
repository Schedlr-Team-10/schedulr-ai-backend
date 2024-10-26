package come.schedule_ai_backend;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.theokanning.openai.utils.TikTokensUtil.ModelEnum.GPT_3_5_TURBO_0301;

@SpringBootApplication
public class SchedlrAiBackendApplication {

	@Autowired
	private static PostRepository postRepository = null;

	public SchedlrAiBackendApplication(PostRepository postRepository) {
		SchedlrAiBackendApplication.postRepository = postRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(SchedlrAiBackendApplication.class, args);

		String token = System.getenv("OPENAI_TOKEN");
		OpenAiService service = new OpenAiService(token);

		List<ChatMessage> messages = new ArrayList<>();
		ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "Enter a prompt here: ");
		messages.add(systemMessage);

		System.out.print("Hi I am your AI assistant chatbot. Ask me a question: ");
		Scanner scanner = new Scanner(System.in);
		ChatMessage firstMsg = new ChatMessage(ChatMessageRole.USER.value(), scanner.nextLine());
		messages.add(firstMsg);

		while (true) {
			ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
					.builder()
					.model("gpt-4o-mini")
					.messages(messages)
					.build();
			ChatCompletionResult result = service.createChatCompletion(chatCompletionRequest);
			long usedTokens = result.getUsage().getTotalTokens();
			ChatMessage response = result.getChoices().get(0).getMessage();

			messages.add(response);

			System.out.println(response.getContent());
			System.out.println("Total tokens used: " + usedTokens);
			System.out.print("Anything else?\n");
			String nextLine = scanner.nextLine();
			if (nextLine.equalsIgnoreCase("exit")) {
				System.exit(0);
			}
			messages.add(new ChatMessage(ChatMessageRole.USER.value(), nextLine));
		}

	}

}
