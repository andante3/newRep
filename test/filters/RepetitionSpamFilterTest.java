package filters;

import main.User;
import message.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepetitionSpamFilterTest {

    @Test
    public void isSpamTest() {
        RepetitionSpamFilter filter = new RepetitionSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("repetition", "2");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));
    }

    @Test
    public void isSpamTestInHeader() {
        RepetitionSpamFilter filter = new RepetitionSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("repetition", "2");

        Message message = new Message(null, receiver);
        message.setHeader("Header header header");
        message.setText("Text");

        assertEquals(false, filter.isSpam(message));
    }

    @Test
    public void isSpamTestInText() {
        RepetitionSpamFilter filter = new RepetitionSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("repetition", "2");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text text text");

        assertEquals(true, filter.isSpam(message));
    }
    @Test
    public void isSpamTestInHeaderAndText() {
        RepetitionSpamFilter filter = new RepetitionSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("repetition", "2");

        Message message = new Message(null, receiver);
        message.setHeader("Header header header");
        message.setText("Text text text");

        assertEquals(true, filter.isSpam(message));
    }
    @Test
    public void isSpamTestSeparationWithSigns() {
        RepetitionSpamFilter filter = new RepetitionSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("repetition", "2");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text+text-text=text");

        assertEquals(true, filter.isSpam(message));
    }

    @Test
    public void isSpamTestRepetitionIsNull() {
        RepetitionSpamFilter filter = new RepetitionSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("repetition", null);

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("Text text text text text text");

        assertEquals(false, filter.isSpam(message));
    }

    @Test
    public void isSpamTestEmptyText() {
        RepetitionSpamFilter filter = new RepetitionSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("repetition", "2");

        Message message = new Message(null, receiver);
        message.setHeader("Header");
        message.setText("");

        assertEquals(false, filter.isSpam(message));
    }

    @Test
    public void isSpamTestEmptyHeaderAndText() {
        RepetitionSpamFilter filter = new RepetitionSpamFilter();
        User receiver = new User("rr");
        receiver.setSpamFilter("repetition", "2");

        Message message = new Message(null, receiver);
        message.setHeader("");
        message.setText("");

        assertEquals(false, filter.isSpam(message));
    }
}