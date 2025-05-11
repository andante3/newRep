package filters;

import main.User;
import message.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SenderSpamFilterTest {

    @Test
    public void isSpamTest() {
        SenderSpamFilter filter = new SenderSpamFilter();
        User receiver = new User("rr");
        User sender = new User("John");
        receiver.setSpamFilter("sender", "John");

        Message message = new Message(sender, receiver);
        message.setHeader("Header");
        message.setText("Text");

        assertEquals(true, filter.isSpam(message));
    }

    @Test
    public void isSpamTestFalse() {
        SenderSpamFilter filter = new SenderSpamFilter();
        User receiver = new User("rr");
        User sender = new User("Mike");
        receiver.setSpamFilter("sender", "John");

        Message message = new Message(sender, receiver);
        message.setHeader("Header");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));
    }
    @Test
    public void isSpamTestNullSender() {
        SenderSpamFilter filter = new SenderSpamFilter();
        User receiver = new User("rr");
        User sender = new User("Mike");
        receiver.setSpamFilter("sender", null);

        Message message = new Message(sender, receiver);
        message.setHeader("Header");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));
    }
}