package com.fifetoyi.hng_stage_one.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WeatherService {
    @Value("${weatherapi.api.key}")
    private String weatherApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getTemperature(String city) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + weatherApiKey + "&q=" + city + "&aqi=no";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Object> current = (Map<String, Object>) response.get("current");
        return current.get("temp_c").toString();
    }
}
