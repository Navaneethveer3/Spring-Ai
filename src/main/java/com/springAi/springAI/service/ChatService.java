package com.springAi.springAI.service;

import reactor.core.publisher.Flux;

public interface ChatService {

	public String chat(String prompt);
	
	public Flux<String> streamChat(String prompt);
}
