package com.springAi.springAI.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Service
public class ChatServiceImplementation implements ChatService{

	private ChatClient chatClient;
	
	@Value("classpath:/prompts/user-prompt-1.txt")
	private Resource userMessage;
	
	@Value("classpath:/prompts/system-prompt-1.txt")
	private Resource systemMessage;
	
	public ChatServiceImplementation(ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	
	@Override
	public String chat(String prompt) {
		return chatClient
                .prompt()
                .user(prompt)
                .system("You are an expert as a top backend architect")
                .call()
                .content();
		
	}
	
	public String chatTemplate() {
//		PromptTemplate promptTemplate = PromptTemplate
//				.builder()
//				.template("What is {techName}? tell me an example like {exampleName}")
//				.build();
//		String renderedMessage = promptTemplate.render(
//				Map.of(
//						"techName", "spring",
//						"exampleName", "spring security"
//						)
//				);
//		SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate
//				.builder()
//				.template("You are a helpful coding assistant. You are an expert in coding.")
//				.build();
//		var systemMessage = systemPromptTemplate.createMessage();
//		
//		var userPromptTemplate = PromptTemplate
//				.builder()
//				.template("What is {techName}? tell me an example like{exampleName}")
//				.build();
//		var userMessage = userPromptTemplate.createMessage(
//			Map.of(
//					"techName", "Spring",
//					"techExample", "Spring Exception"
//					)
//			);
//		
//		Prompt prompt = new Prompt(systemMessage, userMessage);
		
		
		String response = chatClient
				.prompt()
				.advisors(new SimpleLoggerAdvisor())
				.system(system->system.text(this.systemMessage))
				.user(user->user.text(this.userMessage).params(Map.of("concept", "Graph","subject","DSA")))
				.call()
				.content();
		return response;
	}
}
