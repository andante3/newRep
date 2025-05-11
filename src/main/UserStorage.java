package main;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Map<String, User> dataBase = new HashMap<>();

    public static User getUser(String name) {
        return dataBase.get(name);
    }

    public static void setUser(String name) {
        dataBase.put(name, new User(name));
    }

    public static boolean isExistUser(String name) {
        return dataBase.containsKey(name);
    }
}
