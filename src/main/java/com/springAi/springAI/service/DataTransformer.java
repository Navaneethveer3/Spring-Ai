package com.springAi.springAI.service;

import java.util.*;

import org.springframework.ai.document.Document;

public interface DataTransformer {
	List<Document> transform(List<Document> documents);
}
