package com.springAi.springAI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springAi.springAI.service.ChatServiceImplementation;

import reactor.core.publisher.Flux;

@SpringBootTest
class SpringAiApplicationTests {

	@Autowired
	ChatServiceImplementation chatService;
	
	
	@Test
	void contextLoads() {
	}
	
	
	
	@Test
	void chat() {
		String prompt = "What is Spring Boot?";
		String response = this.chatService.chat(prompt,"john");
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
		Flux<String> response = this.chatService.streamChat(prompt,"john");
		System.out.println(response);
	}
	
	@Test
	void checkChatMemory() {
		String prompt1 = "a = 5";
		String response1 = this.chatService.chat(prompt1,"john");
		System.out.println(response1);
		String prompt2 = "What is the value of a?";
		String response2 = this.chatService.chat(prompt2,"john");
		System.out.println(response2);
	}
	
	@Test
	void checkPostgresChatMemory() {
		String prompt1 = "Hi, I am lucifer";
		String response1 = this.chatService.chat(prompt1, "lucifer");
		System.out.println(response1);
		String prompt2 = "I am interested and talented in punishing people";
		String response2 = this.chatService.chat(prompt2, "lucifer");
		System.out.println(response2);
		String prompt3 = "Can you tell me what I am interested in?";
		String response3 = this.chatService.chat(prompt3, "lucifer");
		System.out.println(response3);
	}
}
