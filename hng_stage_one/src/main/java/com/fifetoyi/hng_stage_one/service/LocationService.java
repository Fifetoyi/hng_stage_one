package com.fifetoyi.hng_stage_one.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class LocationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger log = LoggerFactory.getLogger(LocationService.class);

    public Map<String, String> getLocation() {
        String url = "http://ip-api.com/json/";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        log.info("Location API response: " + response);
        String ip = (String) response.get("query");
        String city = (String) response.get("city");
        String region = (String) response.get("regionName");
        String country = (String) response.get("country");
        return Map.of("ip", ip, "city", city, "region", region, "country", country);
    }
}
