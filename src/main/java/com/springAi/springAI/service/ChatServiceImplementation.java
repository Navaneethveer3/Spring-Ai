package com.springAi.springAI.service;

import org.springframework.ai.chat.client.ChatClient;
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
                .call()
                .content();
		
	}
}
