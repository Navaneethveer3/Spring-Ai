package com.springAi.springAI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.springAi.springAI.tools.WeatherTool;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherToolTests {

	@Autowired
	WeatherTool weatherTool;

	@Test
	void checkWeatherByCity() {
		var response = weatherTool.getWeatherByCity("Kondapur, Hyderabad");
		System.out.println(response);
	}
}
