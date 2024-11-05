package com.avioscoins.spring_boot.Types;

public class Route {
    
    public final Airports departure;
    public final Airports destination;
    public final Double coins;

    public Route(Airports departure, Airports destination, Double coins) {
        this.departure = departure;
        this.destination = destination;
        this.coins = coins;
    }
}
