package com.example.spring_boot;

import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot.Components.CabinHelper;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ApplicationController {
    
    private static final Map<String, Double> LHRRoutes = new HashMap<String, Double>();
    static {
        LHRRoutes.put("LAX", 4500.0);
        LHRRoutes.put("SFO", 4400.0);
        LHRRoutes.put("JFK", 3200.0);
    }

    private static final Map<String, Double> LGWRoutes = new HashMap<String, Double>();
    static {
        LGWRoutes.put("YYZ", 3250.0);
    }
    
    @Autowired
    private CabinHelper cabinHelper;
    
    @GetMapping("coins/{departureCode}/{arrivalCode}")
    public Map<String, Double> getAviosCoins(
        @RequestParam(value = "cabinCode", required = false) String cabinCode,
        @PathVariable String departureCode,
        @PathVariable String arrivalCode,
        HttpServletRequest request
    ) {
        Double routeValue;
        if ("LHR".equals(departureCode)) {
            routeValue = LHRRoutes.get(arrivalCode);
        } else if ("LHR".equals(arrivalCode)) {
            routeValue = LHRRoutes.get(departureCode);
        } else {
            throw new InvalidPathException(request.getRequestURI(), "invalid codes input");
        }

        if(cabinCode == "") {
            return cabinHelper.getCabinBonus(routeValue);
        } else {
            return cabinHelper.getCabinBonus(cabinCode, routeValue);
        }
    }
    
}
