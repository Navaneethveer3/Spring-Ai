package com.springAi.springAI;


import org.junit.jupiter.api.Test;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springAi.springAI.service.DataLoaderImplementation;
import com.springAi.springAI.service.DataTransformer;

@SpringBootTest
public class DataLoaderTests {
	
	@Autowired
	DataLoaderImplementation dataLoader;
	
	@Autowired
	DataTransformer dataTransformer;
	
	@Autowired
	VectorStore vectorStore;

	
	@Test
	void testDataLoaderJson() {
		var documents = dataLoader.loadDocumentsFromJson();
		System.out.print(documents.size());
		documents.forEach(item->System.out.println(item));
	}
	
	@Test
	void testDataLoaderPdf() {
		var documents = dataLoader.loadDocumentsFromPdf();
		System.out.println(documents.size());
//		documents.forEach(item->{
//			System.out.println(item);
//			System.out.print("_________\n");
//		});
		
		var transformedDocuments = this.dataTransformer.transform(documents);
		System.out.println(transformedDocuments.size());
		
		this.vectorStore.add(transformedDocuments);
		System.out.println("saved successfully!");
	}
}
