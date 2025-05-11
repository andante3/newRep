package message;

import java.util.Scanner;
import filters.CompositeSpamFilter;
import user.User;

public class Message {
    private String header;
    private String text;
    private final user.User sender;
    private user.User receiver;

    public Message(user.User sender, user.User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    private void setHeader(Scanner scanner) {
        System.out.print("Enter a letter header: ");
        header = scanner.nextLine();
    }

    private void setText(Scanner scanner) {
        System.out.print("Enter letter text: ");
        text = scanner.nextLine();
    }

    public  void sendMessage(Scanner scanner) {
        String name = user.Main.getUser("Enter the receiver name: ");
        if (name.isEmpty())
            return;
        if (user.Main.users.contains(name)) {
            receiver = user.UserStorage.getUser(name);
            if (wrightLetter(scanner)) {
                System.out.printf("Letter from %s to %s is sent%n", sender.getUser(), receiver.getUser());
            } else
                System.out.println("Something got wrong. Try later");
        } else
            System.out.printf("user.User %s is not found%n", name);
    }

    private boolean wrightLetter(Scanner scanner) {
        setHeader(scanner);
        setText(scanner);
        sender.setOutbox(receiver.getUser() + ": " + header + ": " + text);
        if (new CompositeSpamFilter().isSpam(this))
            receiver.setSpam(sender.getUser() + ": " + header + ": " + text);
        else
            receiver.setInbox(sender.getUser() + ": " + header + ": " + text);

        return true;
    }
}
