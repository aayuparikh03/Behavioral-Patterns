package org.example;
import java.util.ArrayList;
import java.util.List;
// Observer interface
interface Observer {
    void update(String stockName, double stockPrice);
}
// Subject interface
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
// Concrete Subject
class Stock implements Subject {
    private List<Observer> observers;
    private String stockName;
    private double stockPrice;
    public Stock(String stockName) {
        this.stockName = stockName;
        this.observers = new ArrayList<>();
    }
    public void setPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, stockPrice);
        }
    }
}
// Concrete Observer 1
class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println("Investor " + name + " notified: " + stockName + " price updated to $" + stockPrice);
    }
}

// Concrete Observer 2
class StockApp implements Observer {
    private String appName;

    public StockApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println("StockApp " + appName + " shows: " + stockName + " is now $" + stockPrice);
    }
}
public class Main {
    public static void main(String[] args) {
        // Create a stock (subject)
        Stock teslaStock = new Stock("Tesla");

        // Create observers
        Observer investorJohn = new Investor("John");
        Observer investorEmma = new Investor("Emma");
        Observer stockApp = new StockApp("Stock Tracker");

        // Add observers to the stock
        teslaStock.addObserver(investorJohn);
        teslaStock.addObserver(investorEmma);
        teslaStock.addObserver(stockApp);

        // Change the stock price
        teslaStock.setPrice(800.50); // All observers will be notified
        teslaStock.setPrice(815.75); // All observers will be notified again
    }
}
