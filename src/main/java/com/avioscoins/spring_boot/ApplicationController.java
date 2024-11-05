package com.avioscoins.spring_boot;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    
    @GetMapping("coins/{departureCode}/{arrivalCode}")
    public Map<String, Double> getAviosCoins(
        @RequestParam(required = false) String cabin,
        @PathVariable String departureCode,
        @PathVariable String arrivalCode

    ) {
        return applicationService.getCoinsForRoute(departureCode, arrivalCode, cabin);
    };
    
}
