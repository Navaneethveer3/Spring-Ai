 package com.springAi.springAI.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.springAi.springAI.service.ChatServiceImplementation;

import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {
	
	@Autowired
	private ChatServiceImplementation chatService;
	
	
	@GetMapping(value = "/ask")
    public ResponseEntity<Map<String, String>> ask(@RequestParam String prompt, @RequestHeader("userId") String userId) {
        String response = chatService.chat(prompt, userId);
        return new ResponseEntity<>(Map.of("response", response),HttpStatus.OK);
    }
	
	@GetMapping("/streams")
	public ResponseEntity<Flux<String>> streamChat(@RequestParam String prompt, @RequestHeader String userId){
		return new ResponseEntity<>(this.chatService.streamChat(prompt, userId), HttpStatus.OK);
	}
}