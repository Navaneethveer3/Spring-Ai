package com.springAi.springAI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springAi.springAI.service.ChatServiceImplementation;

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
		String response = chatService.chatTemplate();
		System.out.println(response);
	}

}
