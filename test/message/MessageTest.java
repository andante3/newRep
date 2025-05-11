package message;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import user.Main;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    public void testSendMessage() {
        Scanner scanner = new Scanner(System.in);
        user.User user1 = new user.User("Ma");
        user.User user2 = new user.User("Ig");
        user.Main.users.add("Ma");
        user.Main.users.add("Ig");
        Message message = new Message(user1, user2);
        assertEquals(2, Main.users.size());
    }
    @AfterEach
    public void testCheckOutbox() {
//        assertEquals(1, user1.getOutbox().size());
    }

}