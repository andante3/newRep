package message;
import user.User;

public class Message {
    private String header;
    private String text;
    private final User sender;
    private User receiver;

    public Message(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String string) {
        header = string;
    }

    public String getText() {
        return text;
    }

    public void setText(String string) {
        text = string;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User user) {
        receiver = user;
    }
}
