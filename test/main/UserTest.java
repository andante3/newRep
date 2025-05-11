package main;

import org.junit.jupiter.api.Test;

import java.util.List;

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


}