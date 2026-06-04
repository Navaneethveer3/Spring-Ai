package com.springAi.springAI.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springAi.springAI.advisors.TokenPrintAdvisor;

@Configuration
public class AiConfig {

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder
				.defaultAdvisors(new TokenPrintAdvisor(), new SafeGuardAdvisor(List.of("game")))
				.defaultSystem("You are a helpful coding assistant. You are expert in coding")
				.build();
	}
}
