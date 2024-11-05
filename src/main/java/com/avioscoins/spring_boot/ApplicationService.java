package com.avioscoins.spring_boot;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avioscoins.spring_boot.Components.AirportRoutesData;
import com.avioscoins.spring_boot.Components.CabinHelper;
import com.avioscoins.spring_boot.Types.Airports;
import com.avioscoins.spring_boot.Types.Route;

@Service
public class ApplicationService {
    
    @Autowired
    private CabinHelper cabinHelper;

    @Autowired
    private AirportRoutesData airportRoutesData;

    public Map<String, Double> getCoinsForRoute(String departureCode, String arrivalCode, String cabin) {
        Route route;

        //Handles if route is LHR/LGW to a different airport or the other way around
        route = airportRoutesData.getRoute(Airports.valueOf(departureCode), Airports.valueOf(arrivalCode));
        
        //If missing cabinCode request parameter
        if(cabin == null || cabin.equals("") ) {
            return cabinHelper.getCabinBonus(route.coins);
        } else {
            return cabinHelper.getCabinBonus(cabin, route.coins);
        }
    }
}
