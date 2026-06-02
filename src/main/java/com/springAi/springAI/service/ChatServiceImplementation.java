package com.springAi.springAI.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImplementation implements ChatService{

	private ChatClient chatClient;
	
	public ChatServiceImplementation(ChatClient.Builder builder) {
		this.chatClient = builder.build();
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
				.system(system->system.text("You are a helpful coding assistant. You are an expert in coding."))
				.user(user->user.text("What is {techName}? tell me an example like{exampleName}").params(Map.of("techName","Spring","exampleName","Spring Exception")))
				.call()
				.content();
		return response;
	}
}
