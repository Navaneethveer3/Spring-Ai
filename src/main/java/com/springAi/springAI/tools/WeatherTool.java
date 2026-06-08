package com.springAi.springAI.tools;

import java.util.*;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
public class WeatherTool {
	
	private RestClient restClient;
	
	public WeatherTool(RestClient restClient) {
		this.restClient = restClient;
	}


	@Value("${app.weather.api-key}")
	private String weatherApiKey;
	
	
	@Tool(description="Get the weather details by the city name")
	public String getWeatherByCity(@ToolParam(description="city value to fetch weather") String city) {
		var response = restClient
							.get()
							.uri(
									builder->builder.path("/current.json")
									.queryParam("key", weatherApiKey)
									.queryParam("q", city)
									.build()
									)
							.retrieve()
							.body(new ParameterizedTypeReference<Map<String,Object>>() {
							});
		return (response==null || response.isEmpty())?"Weather data not found!":response.toString();
	}
}
