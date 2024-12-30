package org.example;
import java.util.ArrayList;
import java.util.List;
// Mediator interface
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
// Concrete Mediator
class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }
    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            // Message should not be received by the sender
            if (u != user) {
                u.receive(message);
            }
        }
    }
    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
}
// Colleague
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}
// Concrete Colleague
class ChatUser extends User {

    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " receives: " + message);
    }
}
public class Main {
    public static void main(String[] args) {
        // Create mediator
        ChatMediator chatRoom = new ChatRoom();

        // Create users
        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");
        User user4 = new ChatUser(chatRoom, "Diana");

        // Add users to the chat room
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        chatRoom.addUser(user4);

        // Send messages
        user1.send("Hello, everyone!");
        user3.send("Hi, Alice!");
    }
}
