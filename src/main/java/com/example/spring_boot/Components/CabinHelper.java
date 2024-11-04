package com.example.spring_boot.Components;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.spring_boot.Types.Cabins;

// Needs to be a component that is a helper that can be autowired into the application controller with a function that gets the cabin 
// bonuses for all and another that calculates the final total
@Component
public class CabinHelper {
    private static final Map<String, Double> cabinToBonus = new HashMap<String, Double>();
    static {
        cabinToBonus.put(Cabins.M.name(), 1.0);
        cabinToBonus.put(Cabins.W.name(), 1.2);
        cabinToBonus.put(Cabins.J.name(), 1.5);
        cabinToBonus.put(Cabins.F.name(), 2.0);
    }

    public Map<String, Double> getCabinBonus(String cabinCode, Double coins) throws InvalidParameterException {
        if(cabinToBonus.containsKey(cabinCode)) {
            Double coinValue = cabinToBonus.get(cabinCode) * coins;
            return Collections.singletonMap(cabinCode, coinValue);
        } else {
            throw new InvalidParameterException("Invalid cabin code input: " + cabinCode);
        }
    }

    public Map<String, Double> getCabinBonus(Double coins) throws InvalidParameterException {
        final Map<String, Double> allCoinsForCabins = new HashMap<String, Double>();
        for(Map.Entry<String, Double> entry : cabinToBonus.entrySet()) {
            allCoinsForCabins.put(entry.getKey(), coins*entry.getValue());
        }
        return allCoinsForCabins;
    }


}
