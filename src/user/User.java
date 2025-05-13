package user;

import filters.CompositeSpamFilter;
import main.Main;
import main.UserStorage;
import message.Message;

import java.util.*;

public class User {
    private String user;
    private final List<String> inbox = new ArrayList<>();
    private final List<String> spam = new ArrayList<>();
    private final List<String> outbox = new ArrayList<>();
    private Map<String, String> spamFilter = new HashMap<>();

    public User(String user) {
        setUser(user);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String string){
        user = string;
    }

    public List<String> getInbox() {
        return inbox;
    }

    public void setInbox(String string) {
        inbox.add(string);
    }

    public List<String> getSpam() {
        return spam;
    }

    public void setSpam(String string) {
        spam.add(string);
    }

    public List<String> getOutbox() {
        return outbox;
    }

    public void setOutbox(String string) {
        outbox.add(string);
    }

    public String getSpamFilter(String string) {
        return spamFilter.get(string);
    }

    public void setSpamFilter(Map<String, String> newFilters) {
        spamFilter = new HashMap<>(newFilters);
    }

    public void setSpamFilter(String filterName, String keys) {
        spamFilter.remove(filterName);
        spamFilter.put(filterName, keys);

    }

    boolean isNumber(String string) {
        for (char c : string.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }

    private String readLine(Scanner scanner) {
        return scanner.nextLine().trim().toLowerCase();
    }

    void setSimple(String filterName) {
        spamFilter.put(filterName, "yes");
        System.out.println("Simple filter is set");
    }

    void setKeywords(String filterName, Scanner scanner) {
        System.out.print("Enter keywords through space: ");
        String keys = readLine(scanner);
        String[] check = keys.split("[A-Za-z0-9А-Яа-яЁё ]");
        if (check.length > 0){
            System.out.println("Keywords should be not empty and consist of letters and digits only");
            return;
        }
        setSpamFilter(filterName, keys);
        System.out.println("Keyword filter is set");
    }

    private void setRepetition(String filterName, Scanner scanner) {
        System.out.print("Enter maximum number of repetitions: ");
        String numberOfRepetitions = readLine(scanner);
        setRepetitionForTests(filterName, numberOfRepetitions);
    }

    void setRepetitionForTests(String filterName, String numberOfRepetitions) {
        if (!isNumber(numberOfRepetitions)) {
            System.out.println("Not a number");
            return;
        }
        spamFilter.put(filterName, numberOfRepetitions);
        System.out.println("Repetition filter is set");
    }

    void setSender(String filterName) {
        String userName = Main.getUser("Enter the sender name: ");
        if (!userName.isEmpty())
            spamFilter.put(filterName, userName);
        System.out.println("Sender filter is set");
    }

    int setFilerForTests(Scanner scanner, String filterName) {
        switch (filterName) {
            case "simple":
                setSimple(filterName);
                break;
            case "keywords":
                setKeywords(filterName, scanner);
                break;
            case "repetition":
                setRepetition(filterName, scanner);
                break;
            case "sender":
                setSender(filterName);
                break;
            case "done":
                System.out.println("Filters are set ");
                return 1;
            default:
                System.out.println("Enter simple, keywords, repetition, sender or done");
        }
        return 0;
    }

    public void setFilter(Scanner scanner) {
        while (true) {
            System.out.print("Enter filter name or 'done' to quit: ");
            String filterName = readLine(scanner);
            if (setFilerForTests(scanner, filterName) == 1)
                return;
        }
    }

    private void wrightLetter(Scanner scanner, Message message) {
        System.out.print("Enter a letter header: ");
        message.setHeader(scanner.nextLine());
        System.out.print("Enter letter text: ");
        message.setText(scanner.nextLine());
        wrightLetterForTests(message);
    }

    void wrightLetterForTests(Message message) {
        message.getSender().setOutbox(message.getReceiver().getUser() + ": "
                + message.getHeader() + ": " + message.getText());
        if (new CompositeSpamFilter().isSpam(message))
            message.getReceiver().setSpam(message.getSender().getUser() + ": "
                    + message.getHeader() + ": " + message.getText());
        else
            message.getReceiver().setInbox(message.getSender().getUser() + ": "
                    + message.getHeader() + ": " + message.getText());
    }

    public  void sendMessage(Scanner scanner, Message message) {
        String name = Main.getUser("Enter the receiver name: ");
        if (name.isEmpty())
            return;
        if (Main.getUsers().contains(name)) {
            message.setReceiver(UserStorage.getUser(name));
            wrightLetter(scanner, message);
            System.out.printf("Letter from %s to %s is sent%n",
                    message.getSender().getUser(), message.getReceiver().getUser());
        } else
            System.out.printf("User %s is not found%n", name);
    }


}

