package org.example;
// Abstract Class
abstract class Beverage {
    // Template Method
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }
    protected void boilWater() {
        System.out.println("Boiling water...");
    }
    protected void pourInCup() {
        System.out.println("Pouring into cup...");
    }
    // Abstract methods to be implemented by subclasses
    protected abstract void brew();
    protected abstract void addCondiments();
    // Hook method - can be overridden by subclasses
    protected boolean customerWantsCondiments() {
        return true; // Default behavior
    }
}

// Concrete Class: Tea
class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Steeping the tea...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon...");
    }
}

// Concrete Class: Coffee
class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Brewing the coffee...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }

    @Override
    protected boolean customerWantsCondiments() {
        // Example of overriding the hook
        return false; // No condiments for coffee
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        System.out.println("Preparing Tea:");
        Beverage tea = new Tea();
        tea.prepareRecipe();

        System.out.println("\nPreparing Coffee:");
        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
