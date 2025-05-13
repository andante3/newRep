package filters;

import user.User;
import message.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSpamFilterTest {

    @Test
    public void isSpamTestNoWord() {
        SimpleSpamFilter filter = new SimpleSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("simple", "yes");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));


    }

    @Test
    public void isSpamTestNoFilterWithWordInHeader() {
        SimpleSpamFilter filter = new SimpleSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("simple", null);

        Message message = new Message(null, receiver);
        message.setHeader("Header spam");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));


    }
    @Test
    public void  isSpamTestNoFilterWithWordInText() {
        SimpleSpamFilter filter = new SimpleSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("simple", null);

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text spam");

        assertEquals(false, filter.isSpam(message));


    }
    @Test
    public void  isSpamTestNoFilterWithWordInHeaderAndText() {
        SimpleSpamFilter filter = new SimpleSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("simple", null);

        Message message = new Message(null, receiver);
        message.setHeader("Header spam");
        message.setText("Text spam");

        assertEquals(false, filter.isSpam(message));


    }
    @Test
    public void  isSpamTestWithWordInHeader() {
        SimpleSpamFilter filter = new SimpleSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("simple", "yes");

        Message message = new Message(null, receiver);
        message.setHeader("Header spam");
        message.setText("Text");

        assertEquals(true, filter.isSpam(message));


    }
    @Test
    public void isSpamTestWithWordInText() {
        SimpleSpamFilter filter = new SimpleSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("simple", "yes");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text spam");

        assertEquals(true, filter.isSpam(message));


    }
    @Test
    public void isSpamTestWithWordInHeaderAndText() {
        SimpleSpamFilter filter = new SimpleSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("simple", "yes");

        Message message = new Message(null, receiver);
        message.setHeader("Header spam");
        message.setText("Text spam");

        assertEquals(true, filter.isSpam(message));


    }
}