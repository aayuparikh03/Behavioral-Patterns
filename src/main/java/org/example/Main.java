package org.example;
import java.util.List;
import java.util.ArrayList;
// Visitor interface
interface ShoppingCartVisitor {
    void visit(Clothing item);
    void visit(Electronics item);
    void visit(Book item);
}
// Element interface
interface Item {
    void accept(ShoppingCartVisitor visitor);
}
// ConcreteElement 1: Clothing
class Clothing implements Item {
    private double price;

    public Clothing(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(ShoppingCartVisitor visitor) {
        visitor.visit(this);
    }
}
// ConcreteElement 2: Electronics
class Electronics implements Item {
    private double price;

    public Electronics(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(ShoppingCartVisitor visitor) {
        visitor.visit(this);
    }
}
// ConcreteElement 3: Book
class Book implements Item {
    private double price;

    public Book(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(ShoppingCartVisitor visitor) {
        visitor.visit(this);
    }
}
// ConcreteVisitor: Tax Calculation
class TaxVisitor implements ShoppingCartVisitor {
    @Override
    public void visit(Clothing item) {
        double tax = item.getPrice() * 0.10; // 10% tax on clothing
        System.out.println("Tax for clothing: $" + tax);
    }

    @Override
    public void visit(Electronics item) {
        double tax = item.getPrice() * 0.15; // 15% tax on electronics
        System.out.println("Tax for electronics: $" + tax);
    }

    @Override
    public void visit(Book item) {
        System.out.println("No tax for books.");
    }
}

// ConcreteVisitor: Discount Calculation
class DiscountVisitor implements ShoppingCartVisitor {
    @Override
    public void visit(Clothing item) {
        double discount = item.getPrice() * 0.20; // 20% discount on clothing
        System.out.println("Discount on clothing: $" + discount);
    }

    @Override
    public void visit(Electronics item) {
        double discount = item.getPrice() * 0.10; // 10% discount on electronics
        System.out.println("Discount on electronics: $" + discount);
    }

    @Override
    public void visit(Book item) {
        double discount = item.getPrice() * 0.05; // 5% discount on books
        System.out.println("Discount on book: $" + discount);
    }
}


public class Main {
    public static void main(String[] args) {
        // Create some items
        Item clothing = new Clothing(100.0);
        Item electronics = new Electronics(200.0);
        Item book = new Book(50.0);
        // Add items to the shopping cart
        List<Item> cartItems = new ArrayList<>();    
        cartItems.add(clothing);
        cartItems.add(electronics);
        cartItems.add(book);
        // Create visitors
        ShoppingCartVisitor taxVisitor = new TaxVisitor();
        ShoppingCartVisitor discountVisitor = new DiscountVisitor();
        // Accept the visitors
        for (Item item : cartItems) {
            item.accept(taxVisitor);
            item.accept(discountVisitor);
        }
    }
}
