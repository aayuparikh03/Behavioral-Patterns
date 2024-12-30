package org.example;

// State Interface
interface TrafficLightState {
    void changeLight(TrafficLightContext context);
    String getState();
}

// Concrete State: Red Light
class RedLightState implements TrafficLightState {
    @Override
    public void changeLight(TrafficLightContext context) {
        System.out.println("Switching to Green Light...");
        context.setState(new GreenLightState());
    }

    @Override
    public String getState() {
        return "Red Light";
    }
}

// Concrete State: Green Light
class GreenLightState implements TrafficLightState {
    @Override
    public void changeLight(TrafficLightContext context) {
        System.out.println("Switching to Yellow Light...");
        context.setState(new YellowLightState());
    }

    @Override
    public String getState() {
        return "Green Light";
    }
}

// Concrete State: Yellow Light
class YellowLightState implements TrafficLightState {
    @Override
    public void changeLight(TrafficLightContext context) {
        System.out.println("Switching to Red Light...");
        context.setState(new RedLightState());
    }

    @Override
    public String getState() {
        return "Yellow Light";
    }
}

// Context: Traffic Light
class TrafficLightContext {
    private TrafficLightState currentState;

    public TrafficLightContext() {
        // Initial state
        this.currentState = new RedLightState();
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void changeLight() {
        currentState.changeLight(this);
    }

    public String getCurrentState() {
        return currentState.getState();
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        TrafficLightContext trafficLight = new TrafficLightContext();

        System.out.println("Initial State: " + trafficLight.getCurrentState());

        trafficLight.changeLight();
        System.out.println("Current State: " + trafficLight.getCurrentState());

        trafficLight.changeLight();
        System.out.println("Current State: " + trafficLight.getCurrentState());

        trafficLight.changeLight();
        System.out.println("Current State: " + trafficLight.getCurrentState());
    }
}
