package org.example;
// Strategy Interface
interface TravelStrategy {
    void calculateCost();
}
// Concrete Strategy 1: Bus Strategy
class BusStrategy implements TravelStrategy {
    @Override
    public void calculateCost() {
        System.out.println("Calculating cost for bus: $20");
    }
}
// Concrete Strategy 2: Car Strategy
class CarStrategy implements TravelStrategy {
    @Override
    public void calculateCost() {
        System.out.println("Calculating cost for car: $50");
    }
}
// Concrete Strategy 3: Plane Strategy
class PlaneStrategy implements TravelStrategy {
    @Override
    public void calculateCost() {
        System.out.println("Calculating cost for plane: $200");
    }
}
// Context class that uses the strategy
class TravelContext {
    private TravelStrategy travelStrategy;

    public TravelContext(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    // Set the travel strategy dynamically
    public void setTravelStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    // Execute the strategy
    public void executeStrategy() {
        travelStrategy.calculateCost();
    }
}
public class Main {
    public static void main(String[] args) {
        // Initially, select the travel strategy as Bus
        TravelContext context = new TravelContext(new BusStrategy());
        context.executeStrategy(); // Output: Calculating cost for bus: $20

        // Change the strategy to Car
        context.setTravelStrategy(new CarStrategy());
        context.executeStrategy(); // Output: Calculating cost for car: $50

        // Change the strategy to Plane
        context.setTravelStrategy(new PlaneStrategy());
        context.executeStrategy(); // Output: Calculating cost for plane: $200
    }
}
