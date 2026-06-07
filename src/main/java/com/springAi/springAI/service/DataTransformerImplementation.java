package com.springAi.springAI.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;


@Service
public class DataTransformerImplementation implements DataTransformer{

	@Override
	public List<Document> transform(List<Document> documents) {
		var splitter = new TokenTextSplitter(300,400,10,5000,true,List.of('.', '?', '!', '\n'));
		List<Document> transformed = splitter.transform(documents);
		return transformed;
	}

}
