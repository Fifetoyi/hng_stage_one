package com.fifetoyi.hng_stage_one.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LocationService {

    @Value("${weatherapi.api.key}")
    private String weatherApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, String> getLocation(String ip) {
        String url = "http://api.weatherapi.com/v1/ip.json?key=" + weatherApiKey + "&q=auto:ip";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        String city = (String) response.get("city");
        String region = (String) response.get("region");
        String country = (String) response.get("country_name");
        return Map.of("city", city, "region", region, "country", country);
    }
}
