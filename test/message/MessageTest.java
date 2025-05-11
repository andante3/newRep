package message;

import main.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import main.Main;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    public void testSendMessage() {
        Scanner scanner = new Scanner(System.in);
        User user1 = new User("Ma");
        User user2 = new User("Ig");
        Main.users.add("Ma");
        Main.users.add("Ig");
        Message message = new Message(user1, user2);
        assertEquals(2, Main.users.size());
    }

}