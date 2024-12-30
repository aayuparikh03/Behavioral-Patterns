package org.example;
// Command interface
interface Command {
    void execute();
    void undo(); // Optional: For undo functionality
}
// Receiver
class Light {
    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void turnOn() {
        System.out.println(location + " light is ON.");
    }

    public void turnOff() {
        System.out.println(location + " light is OFF.");
    }
}
// Concrete Command: Turn On Light
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

// Concrete Command: Turn Off Light
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}
// Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}
public class Main {
    public static void main(String[] args) {
        // Receiver
        Light livingRoomLight = new Light("Living Room");

        // Commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // Invoker
        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(lightOn);
        remote.pressButton();

        // Undo the operation
        remote.pressUndo();

        // Turn off the light
        remote.setCommand(lightOff);
        remote.pressButton();

        // Undo the operation
        remote.pressUndo();
    }
}
