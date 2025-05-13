package filters;

import user.User;
import message.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeSpamFilterTest {

    @Test
    void isSpamNoFilters() {
        CompositeSpamFilter filter = new CompositeSpamFilter();
        User receiver = new User("rr");
        User sender = new User("John");
        Message message = new Message(sender, receiver);
        message.setHeader("Header");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));

    }

    @Test
    void isSpamSimpleInHeader() {
        CompositeSpamFilter filter = new CompositeSpamFilter();
        User receiver = new User("rr");
        User sender = new User("John");
        receiver.setSpamFilter("simple", "yes");

        Message message = new Message(sender, receiver);
        message.setHeader("Header spam");
        message.setText("Text");

        assertEquals(true, filter.isSpam(message));

    }

    @Test
    void isSpamSimpleInText() {
        CompositeSpamFilter filter = new CompositeSpamFilter();
        User receiver = new User("rr");
        User sender = new User("John");
        receiver.setSpamFilter("simple", "yes");

        Message message = new Message(sender, receiver);
        message.setHeader("Header");
        message.setText("Text spam");

        assertEquals(true, filter.isSpam(message));

    }

    @Test
    void isSpamKeywords() {
        CompositeSpamFilter filter = new CompositeSpamFilter();
        User receiver = new User("rr");
        User sender = new User("John");
        receiver.setSpamFilter("keywords", "key");

        Message message = new Message(sender, receiver);
        message.setHeader("Header key");
        message.setText("Text");

        assertEquals(true, filter.isSpam(message));

    }

    @Test
    void isSpamRepetition() {
        CompositeSpamFilter filter = new CompositeSpamFilter();
        User receiver = new User("rr");
        User sender = new User("John");
        receiver.setSpamFilter("repetition", "2");

        Message message = new Message(sender, receiver);
        message.setHeader("Header");
        message.setText("Text t t t");

        assertEquals(true, filter.isSpam(message));

    }
}