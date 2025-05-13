package main;

import user.User;

import java.util.*;

public class Main {
    private static Scanner scanner;
    private static String user = "";
    private static List<String> commands = new ArrayList<>();
    private final static List<String> users = new ArrayList<>();
    private static User userObject;

    private static void init() {
        commands = Arrays.asList("add", "list", "send", "inbox", "spam", "outbox", "setfilter", "user", "exit", "help");
        scanner = new Scanner(System.in);
    }

    public static void setUser(String string) {
        user = string;
    }

    public static void setCommands(List<String> list) {
        commands = list;
    }

    public static List<String> getCommands() {
        return commands;
    }

    public static List<String> getUsers() {
        return users;
    }

    public static User getUserObject() {
        return userObject;
    }


    public static List<String> getTokens(String string, boolean makeLowerCase, boolean noEmptyTokens) {
        String[] temp = string.split("[^A-Za-z0-9_]");
        List<String> list = new ArrayList<>();
        for (String token : temp)
            if (!token.trim().isEmpty() || !noEmptyTokens)
                if (makeLowerCase)
                    list.add(token.toLowerCase());
                else
                    list.add(token);
        return list;
    }

    public static String getCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("exit") || line.equals("help"))
            return line;
        if (user.isEmpty() && !line.equals("add")) {
            System.out.println("First command must be 'add'! No one user is defined");
            return "Error";
        }
        int length = line.length();
        List<String> tokens = getTokens(line, true, false);
        if (tokens.isEmpty() || tokens.getFirst().length() != length || tokens.size() > 1
                || !commands.contains(tokens.getFirst())) {
            System.out.println("Wrong command");
            return "Error";
        }
        return tokens.getFirst();
    }

    public static void setUserActive(String name, boolean printIfExist) {
        if (!users.contains(name)) {
            users.add(name);
            Collections.sort(users);
            UserStorage.setUser(name);
        } else if (printIfExist)
            System.out.printf("User %s already exists%n", name);
        user = name;
        userObject = UserStorage.getUser(name);
    }

    public static String getUser(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine().trim();
        return getUserForTests(line);
    }

    public static String getUserForTests(String line) {
        List<String> tokens = getTokens(line, false, false);
        if (tokens.size() != 1) {
            System.out.println("Name must be one word and consist of latin letters and digits only");
            System.out.println("First symbol is a letter");
            return "";
        }
        if (tokens.getFirst().isEmpty()) {
            System.out.println("Name can not be a blank line");
            return "";
        }
        if (Character.isDigit(tokens.getFirst().charAt(0))) {
            System.out.println("First character of a name must be a letter");
            return "";
        }
        return tokens.getFirst();
    }

    public static void printListOfUsers() {
        if (users.isEmpty())
            System.out.println("No users");
        else {
            System.out.println("==========================");
            System.out.println("List of users: ");
            for (String user : users)
                System.out.println(user);
            System.out.printf("Total: %d users%n", users.size());
            System.out.println("==========================");
        }
    }

    private static void changeUser() {
        String name = getUser("Enter an existing user name: ");
        if (!users.contains(name))
            System.out.printf("User %s is not found%n", name);
        else
            setUserActive(name, false);
    }

    private static void printInbox() {
        if (userObject.getInbox().isEmpty())
            System.out.println("Inbox: empty folder");
        else {
            System.out.println("==========================");
            System.out.println("Inbox: ");
            for (String mail : userObject.getInbox())
                System.out.println(mail);
            System.out.printf("Total: %d messages%n", userObject.getInbox().size());
            System.out.println("==========================");
        }
    }

    private static void printSpam() {
        if (userObject.getSpam().isEmpty())
            System.out.println("Spam: empty folder");
        else {
            System.out.println("==========================");
            System.out.println("Spam: ");
            for (String mail : userObject.getSpam())
                System.out.println(mail);
            System.out.printf("Total: %d messages%n", userObject.getSpam().size());
            System.out.println("==========================");
        }
    }

    private static void printOutbox() {
        if (userObject.getOutbox().isEmpty())
            System.out.println("Outbox: empty folder");
        else {
            System.out.println("==========================");
            System.out.println("Outbox: ");
            for (String mail : userObject.getOutbox())
                System.out.println(mail);
            System.out.printf("Total: %d messages%n", userObject.getOutbox().size());
            System.out.println("==========================");
        }
    }

    public static void main(String[] args) {
        init();
        while (true) {
            System.out.printf("Enter a command, use 'help' if needed (%s): ", user);
            String line = scanner.nextLine();
            String command = getCommand(line);
            if (!command.equals("Error"))
                switch (command) {
                    case "add":
                        String name = getUser("Enter a new user name: ");
                        if (!name.isEmpty())
                            setUserActive(name, true);
                        break;
                    case "list":
                        printListOfUsers();
                        break;
                    case "send":
                        userObject.sendMessage(scanner, new message.Message(userObject,null));
                        break;
                    case "inbox":
                        printInbox();
                        break;
                    case "spam":
                        printSpam();
                        break;
                    case "outbox":
                        printOutbox();
                        break;
                    case "setfilter":
                        userObject.setFilter(scanner);
                        break;
                    case "user":
                        changeUser();
                        break;
                    case "exit":
                        return;
                    case "help":
                        System.out.println("Command list: add, list, send, inbox, spam, outbox, setfilter");
                        System.out.println("Additional commands: user (changes active user), exit, help");
                        break;
                    default:
                        System.out.println("Unknown command");
                        break;
                }
        }
    }
}
