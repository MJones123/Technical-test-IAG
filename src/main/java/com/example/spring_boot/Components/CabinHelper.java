package com.example.spring_boot.Components;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

// Needs to be a component that is a helper that can be autowired into the application controller with a function that gets the cabin 
// bonuses for all and another that calculates the final total
@Component
public class CabinHelper {
    private static final Map<String, Double> cabinToBonus = new HashMap<String, Double>();
    static {
        cabinToBonus.put("M", 1.0);
        cabinToBonus.put("W", 1.2);
        cabinToBonus.put("J", 1.5);
        cabinToBonus.put("F", 2.0);
    }

    public Map<String, Double> getCabinBonus(String cabinCode, Double coins) throws InvalidParameterException {
        if(cabinCode == null) {
            final Map<String, Double> allCoinsForCabins = new HashMap<String, Double>();
            for(Map.Entry<String, Double> entry : cabinToBonus.entrySet()) {
                allCoinsForCabins.put(entry.getKey(), coins*entry.getValue());
            }
            return allCoinsForCabins;
        } else if(cabinToBonus.containsKey(cabinCode)) {
            Double coinValue = cabinToBonus.get(cabinCode) * coins;
            return Collections.singletonMap(cabinCode, coinValue);
        } else {
            throw new InvalidParameterException("Invalid cabin code input: " + cabinCode);
        }
    }

    public Map<String, Double> getCabinBonus(Double coins) throws InvalidParameterException {
        return getCabinBonus(null, coins);
    }


}
