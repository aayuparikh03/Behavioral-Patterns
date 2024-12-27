package org.example;
// Abstract Handler
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String issue);
}

// Concrete Handler 1: Basic Support
class BasicSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("password_reset")) {
            System.out.println("Basic Support: Handling password reset.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Basic Support: Unable to handle the request.");
        }
    }
}

// Concrete Handler 2: Advanced Support
class AdvancedSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("account_hack")) {
            System.out.println("Advanced Support: Handling account hack issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Advanced Support: Unable to handle the request.");
        }
    }
}

// Concrete Handler 3: Technical Support
class TechnicalSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("server_down")) {
            System.out.println("Technical Support: Handling server down issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Technical Support: Unable to handle the request.");
        }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        // Create handlers
        SupportHandler basic = new BasicSupportHandler();
        SupportHandler advanced = new AdvancedSupportHandler();
        SupportHandler technical = new TechnicalSupportHandler();

        // Chain them
        basic.setNextHandler(advanced);
        advanced.setNextHandler(technical);

        // Send requests
        basic.handleRequest("password_reset");
        basic.handleRequest("account_hack");
        basic.handleRequest("server_down");
        basic.handleRequest("unknown_issue");
    }
}
