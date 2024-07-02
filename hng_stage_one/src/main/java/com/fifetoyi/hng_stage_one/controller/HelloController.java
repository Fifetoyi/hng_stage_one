package com.fifetoyi.hng_stage_one.controller;

import com.fifetoyi.hng_stage_one.service.LocationService;
import com.fifetoyi.hng_stage_one.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/api/hello")
    public Map<String, String> hello(@RequestParam String visitor_name, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        Map<String, String> location = locationService.getLocation(clientIp);
        String temperature = weatherService.getTemperature(location.get("city"));
        return Map.of(
                "client_ip", clientIp,
                "location", location.get("city"),
                "greeting", String.format("Hello, %s!, The temperature is %s degrees Celsius in %s", visitor_name, temperature, location.get("city"))
        );
    }
}
