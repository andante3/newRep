package filters;

import main.User;
import message.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeywordsSpamFilterTest {

    @Test
    void isSpamTestNoKey() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("keywords", "key");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));
    }

    @Test
    void isSpamTestKeyInHeader() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("keywords", "key");

        Message message = new Message(null, receiver);
        message.setHeader("Header key");
        message.setText("Text");

        assertEquals(true, filter.isSpam(message));
    }

    @Test
    void isSpamTestKeyInText() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("keywords", "key");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text key");

        assertEquals(true, filter.isSpam(message));
    }

    @Test
    void isSpamTestKeyInHeaderAndText() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("keywords", "key");

        Message message = new Message(null, receiver);
        message.setHeader("Header key");
        message.setText("Text key");

        assertEquals(true, filter.isSpam(message));
    }

    @Test
    void isSpamTestKeyInHeaderWithoutSpace() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("keywords", "key");

        Message message = new Message(null, receiver);
        message.setHeader("Headerkey");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));
    }

    @Test
    void isSpamTestKeyInTextWithoutSpace() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("keywords", "key");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Textkey");

        assertEquals(false, filter.isSpam(message));
    }

    @Test
    void isSpamTestKeyInHeaderAndTextWithoutSpace() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("keywords", "key");

        Message message = new Message(null, receiver);
        message.setHeader("Headerkey");
        message.setText("Textkey");

        assertEquals(false, filter.isSpam(message));
    }

    @Test
    void isSpamTestKeyIsNull() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("keywords", null);

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));
    }

}