package com.springAi.springAI.service;

import java.util.*;

import org.springframework.ai.document.Document;


public interface DataLoader {

	List<Document> loadDocumentsFromJson();
	
	List<Document> loadDocumentsFromPdf();
	
	
}
