package com.springAi.springAI.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;


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
	public String chat(String prompt, String userId) {
		return chatClient
                .prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, userId))
                .user(prompt)
                .system("You are an expert as a top backend architect. \n")
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
				.system(system->system.text(this.systemMessage+"\n"))
				.user(user->user.text(this.userMessage+"\n").params(Map.of("concept", "Graph","subject","Data Structures and Algorithms")))
				.call()
				.content();
		return response;
	}

	
	@Override
	public Flux<String> streamChat(String prompt, String userId) {
		return this.chatClient
				.prompt()
				.system(system->system.text(this.systemMessage))
				.user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, userId))
				.stream()
				.content();
	}
}
