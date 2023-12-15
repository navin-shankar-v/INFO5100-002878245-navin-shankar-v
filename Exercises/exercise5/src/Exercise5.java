import java.util.ArrayList;
import java.util.List;

// Singleton Design Pattern
class MessagingService {
    private static MessagingService instance;

    private List<MessageListener> listeners = new ArrayList<>();

    private MessagingService() {
    }

    public static MessagingService getInstance() {
        if (instance == null) {
            instance = new MessagingService();
        }
        return instance;
    }

    public void addListener(MessageListener listener) {
        listeners.add(listener);
    }

    public void sendMessage(String message, String sender, String receiver) {
        System.out.println("Sending message: \"" + message + "\" from " + sender + " to " + receiver);

        // Notify observers (users) about the new message
        for (MessageListener listener : listeners) {
            listener.onMessageReceived(message, sender, receiver);
        }
    }
}

// Observer Design Pattern
interface MessageListener {
    void onMessageReceived(String message, String sender, String receiver);
}

class User implements MessageListener {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void onMessageReceived(String message, String sender, String receiver) {
        if (receiver.equals(name)) {
            System.out.println(name + " received a new message: \"" + message + "\" from " + sender);
        }
    }
}

// Factory Design Pattern
interface MessageSender {
    void sendMessage(String message, String sender, String receiver);
}

class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message, String sender, String receiver) {
        System.out.println("Sending Email: \"" + message + "\" from " + sender + " to " + receiver);
    }
}

class SMSSender implements MessageSender {
    @Override
    public void sendMessage(String message, String sender, String receiver) {
        System.out.println("Sending SMS: \"" + message + "\" from " + sender + " to " + receiver);
    }
}

class MessageSenderFactory {
    public static MessageSender createMessageSender(String type) {
        if (type.equalsIgnoreCase("email")) {
            return new EmailSender();
        } else if (type.equalsIgnoreCase("sms")) {
            return new SMSSender();
        } else {
            throw new IllegalArgumentException("Invalid message sender type");
        }
    }
}

public class Exercise5{
    public static void main(String[] args) {
        // Singleton Design Pattern
        MessagingService messagingService = MessagingService.getInstance();

        // Observer Design Pattern
        User alice = new User("Alice");
        User bob = new User("Bob");

        messagingService.addListener(alice);
        messagingService.addListener(bob);

        // Factory Design Pattern
        MessageSender emailSender = MessageSenderFactory.createMessageSender("email");
        MessageSender smsSender = MessageSenderFactory.createMessageSender("sms");

        // Sending messages
        messagingService.sendMessage("Hello, Bob!", "Alice", "Bob");
        emailSender.sendMessage("Meeting at 3 PM", "John", "Alice");
        smsSender.sendMessage("Urgent: Call me back!", "Bob", "Alice");
    }
}
