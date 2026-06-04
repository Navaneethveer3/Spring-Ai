package com.springAi.springAI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springAi.springAI.service.ChatServiceImplementation;

import reactor.core.publisher.Flux;

@SpringBootTest
class SpringAiApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	ChatServiceImplementation chatService;
	
	@Test
	void chat() {
		String prompt = "What is Spring Boot?";
		String response = this.chatService.chat(prompt);
		System.out.println(response);
	}
	
	@Test
	void chatTemplate() {
		String response = this.chatService.chatTemplate();
		System.out.println(response);
	}
	
	@Test
	void streamChat() {
		String prompt = "What is a Graph?";
		Flux<String> response = this.chatService.streamChat(prompt);
		System.out.println(response);
	}

}
