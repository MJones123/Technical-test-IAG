package com.avioscoins.spring_boot.Components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.avioscoins.spring_boot.Types.Airports;
import com.avioscoins.spring_boot.Types.Route;

@Component
public class AirportRoutesData {
    private List<Route> routesData = new ArrayList<Route>(
        Arrays.asList(
            new Route(Airports.LHR, Airports.LAX, 4500.0),
            new Route(Airports.LHR, Airports.SFO, 4400.0),
            new Route(Airports.LHR, Airports.JFK, 3200.0),
            new Route(Airports.LGW, Airports.YYZ, 3250.0)
        )
    );

    public Route getRoute(Airports departure, Airports destination) {
        return routesData.stream()
                         .filter(route -> (route.departure == departure && route.destination == destination) || (route.departure == destination && route.destination == departure))
                         .findFirst()
                         .get();
    }
}
