package edu.gatech.cs2340.wheresmystuff.model;

import java.util.HashMap;

/**
 * A reverse dependency for testing out features before attaching them to Firebase
 *
 * @author Hartley McGuire
 * @version 1.0
 */
public class FakeFirebase {
    private static final FakeFirebase ourInstance = new FakeFirebase();
    public static FakeFirebase getInstance() {
        return ourInstance;
    }
    private FakeFirebase() {}

    /*
     * Registration and Sign In
     */
    private User user;
    private final HashMap<String, User> userList = new HashMap<>();

    public HashMap<String, User> getUserList() {
        return userList;
    }

    public boolean register(String email, String password) {
        if (email == null || password == null) {
            return false;
        } else if (userList.containsKey(email)) {
            return false;
        } else if (!email.contains("@")) {
            return false;
        } else if (password.length() <= 7) {
            return false;
        } else {
            User newUser = new User(email, password);
            userList.put(email, newUser);
            user = newUser;
            return true;
        }
    }

    public boolean registerAdmin(String email, String password) {
        if (userList.containsKey(email)) {
            return false;
        } else {
            User newUser = new Admin(email, password);
            userList.put(email, newUser);
            user = newUser;
            return true;
        }
    }

    public boolean login(String email, String password) {
        if (userList.containsKey(email) && userList.get(email).getPass().equals(password)) {
            user = userList.get(email);
            return true;
        } else {
            return false;
        }
    }

    public void signOut() {
        user = null;
    }

    /*
      * Basic Database layout?:
      * {
      *     Admins {
      *         (UUID: User object?)
      *         .
      *         .
      *         .
      *     },
      *     Users {
      *         (UUID: User object?) - emails and passwords not exposed
      *         .
      *         .
      *         .
      *     },
      *     Items {
      *
      *     }
      */

}
