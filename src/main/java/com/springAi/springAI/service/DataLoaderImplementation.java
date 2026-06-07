package com.springAi.springAI.service;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Service;

@Service
public class DataLoaderImplementation implements DataLoader {

	@Value("classpath:sample_data.json")
	private Resource jsonResource;

	@Value("classpath:sample.pdf")
	private Resource pdfResource;

	
	private TokenTextSplitter splitter = new TokenTextSplitter();
	
	@Override
	public List<Document> loadDocumentsFromJson() {
		var jsonReader = new JsonReader(this.jsonResource);
		var listDocuments = jsonReader.read();
		return this.splitter.apply(listDocuments);
	}

	@Override
	public List<Document> loadDocumentsFromPdf() {
		var pdfReader = new PagePdfDocumentReader(pdfResource, PdfDocumentReaderConfig
				.builder()
				.withPageTopMargin(0)
				.withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
						.withNumberOfTopTextLinesToDelete(0)
						.build())
				// .withPagesPerDocument(1)
				.build());
		var listDocuments = pdfReader.read();
		return this.splitter.apply(listDocuments);
	}

}
