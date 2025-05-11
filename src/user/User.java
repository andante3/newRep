package user;

import java.util.*;

public class User {
    private final String user;
    private final List<String> inbox = new ArrayList<>();
    private final List<String> spam = new ArrayList<>();
    private final List<String> outbox = new ArrayList<>();
    private Map<String, String> filters = new HashMap<>();

    public User(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
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

    public String getFilters(String string) {
        return filters.get(string);
    }

    public void setFilters(Map<String, String> newFilters) {
        filters = newFilters;
    }

    private boolean isNumber(String string) {
        for (char c : string.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }

    public void setFilter(Scanner scanner) {
        while (true) {
            System.out.print("Enter filter name or 'done' to quit: ");
            String filterName = scanner.nextLine().trim().toLowerCase();
            switch (filterName) {
                case "simple":
                    filters.put(filterName, "yes");
                    System.out.println("Simple filter is set");
                    break;
                case "keywords":
                    System.out.print("Enter keywords through space: ");
                    String keys = scanner.nextLine().trim().toLowerCase();
                    filters.remove(filterName);
                    filters.put(filterName, keys);
                    System.out.println("Keyword filter is set");
                    break;
                case "repetition":
                    System.out.print("Enter maximum number of repetitions: ");
                    String numberOfRepetitions = scanner.nextLine().trim().toLowerCase();
                    if (!isNumber(numberOfRepetitions)) {
                        System.out.println("Not a number");
                        break;
                    }
                    filters.put(filterName, numberOfRepetitions);
                    System.out.println("Repetition filter is set");
                    break;
                case "sender":
                    String userName = Main.getUser("Enter the sender name: ");
                    if (!userName.isEmpty())
                        filters.put(filterName, userName);
                    System.out.println("Sender filter is set");
                    break;
                case "done":
                    System.out.println("Filters are set ");
                    return;
                default:
            }
        }
    }
}

