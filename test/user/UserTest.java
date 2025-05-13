package user;

import message.Message;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void isNumberTestLetters() {
        User user = new User("user");
        assertEquals(false, user.isNumber("abba"));
    }

    @Test
    public void isNumberTestNumbers() {
        User user = new User("user");
        assertEquals(true, user.isNumber("123"));
    }

    @Test
    public void isNumberTestNumbersAndLetters() {
        User user = new User("user");
        assertEquals(false, user.isNumber("1e33"));
    }

    @Test
    public void isNumberTestNumbersAndSpace() {
        User user = new User("user");
        assertEquals(false, user.isNumber("1 33"));
    }

    @Test
    public void getUserTest(){
        User user = new User("");
        user.setUser("user");
        String temp = user.getUser();
        assertEquals("user", temp);
    }

    @Test
    public void getInboxTest(){
        User user = new User("");
        user.setInbox("Inbox");
        List<String> temp = user.getInbox();
        assertEquals("Inbox", temp.getLast());
    }

    @Test
    public void getSpamTest(){
        User user = new User("");
        user.setSpam("Spam");
        List<String> temp = user.getSpam();
        assertEquals("Spam", temp.getLast());
    }

    @Test
    public void getOutboxTest(){
        User user = new User("");
        user.setOutbox("Outbox");
        List<String> temp = user.getOutbox();
        assertEquals("Outbox", temp.getLast());
    }

    @Test
    void setSimpleTestIsSet() {
        User user = new User("John");
        user.setSimple("simple");
        assertEquals("yes", user.getSpamFilter("simple"));
    }

    @Test
    void setSimpleTestIsNotSet() {
        User user = new User("John");
        assertEquals(null, user.getSpamFilter("simple"));
    }

    @Test
    void setRepetitionForTestsIsNumber() {
        User user = new User("John");
        user.setRepetitionForTests("repetition", "5");
        assertEquals("5", user.getSpamFilter("repetition"));
    }

    @Test
    void setRepetitionForTestsIsNumberRewrite() {
        User user = new User("John");
        user.setRepetitionForTests("repetition", "5");
        user.setRepetitionForTests("repetition", "3");
        assertEquals("3", user.getSpamFilter("repetition"));
    }

    @Test
    void setRepetitionForTestsIsNotNumber() {
        User user = new User("John");
        user.setRepetitionForTests("repetition", "5a");
        assertEquals(null, user.getSpamFilter("repetition"));
    }

    @Test
    void setRepetitionForTestsIsNumberRewriteNotChanged() {
        User user = new User("John");
        user.setRepetitionForTests("repetition", "5");
        user.setRepetitionForTests("repetition", "not number");
        assertEquals("5", user.getSpamFilter("repetition"));
    }

    @Test
    void setFilterForTestsSimple() {
        User user = new User("John");
        user.setFilerForTests(new Scanner(System.in), "simple");
        assertEquals("yes", user.getSpamFilter("simple"));
    }

    @Test
    void setFilterForTestsDone() {
        User user = new User("John");
        user.setFilerForTests(new Scanner(System.in), "done");
        assertEquals(null, user.getSpamFilter("simple"));
    }

    @Test
    void setFilterForTestsDefault() {
        User user = new User("John");
        user.setFilerForTests(new Scanner(System.in), "abba");
        assertEquals(null, user.getSpamFilter("simple"));
    }

    @Test
    void wrightLetterForTestsOutbox() {
        User sender = new User("John");
        User receiver = new User("Mike");
        Message message = new Message(sender, receiver);
        message.setHeader("Hi");
        message.setText("Glad to see you");
        sender.wrightLetterForTests(message);
        assertEquals("Mike: Hi: Glad to see you", sender.getOutbox().getFirst());
    }

    @Test
    void wrightLetterForTestsInbox() {
        User sender = new User("John");
        User receiver = new User("Mike");
        Message message = new Message(sender, receiver);
        message.setHeader("Hi");
        message.setText("Glad to see you");
        sender.wrightLetterForTests(message);
        assertEquals("John: Hi: Glad to see you", receiver.getInbox().getFirst());
    }

    @Test
    void wrightLetterForTestsSpam() {
        User sender = new User("John");
        User receiver = new User("Mike");
        Message message = new Message(sender, receiver);
        receiver.setSpamFilter("simple", "yes");
        message.setHeader("Hi spam");
        message.setText("Glad to see you");
        sender.wrightLetterForTests(message);
        assertEquals("John: Hi spam: Glad to see you", receiver.getSpam().getFirst());
    }



}