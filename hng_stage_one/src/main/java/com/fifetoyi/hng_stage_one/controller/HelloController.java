package com.fifetoyi.hng_stage_one.controller;

import com.fifetoyi.hng_stage_one.service.LocationService;
import com.fifetoyi.hng_stage_one.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private WeatherService weatherService;

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/api/hello")
    public Map<String, String> hello(@RequestParam String visitor_name, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        log.info("The client IP: " + clientIp);

        Map<String, String> locationInfo = locationService.getLocation(clientIp);
        String city = locationInfo.get("city");

        String temperature = weatherService.getTemperature(city);
        return Map.of(
                "client_ip", clientIp,
                "location", city,
                "greeting", String.format("Hello, %s! The temperature is %s degrees Celsius in %s", visitor_name, temperature, city)
        );
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || remoteAddr.isEmpty()) {
            remoteAddr = request.getRemoteAddr();
        }
        return remoteAddr;
    }
}
