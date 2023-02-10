package service;

import model.user.User;
import model.user.UserType;

import java.util.*;

public class UserService {

    HashMap<String, Set<String>> userMovieReviewMap;

    public Map<String, UserType> getUserTypeMap() {
        return userTypeMap;
    }

    Map<String, UserType> userTypeMap;

    public Map<String, User> getUserMap() {
        return userMap;
    }

    Map<String, User> userMap;

    public UserService() {
        userMovieReviewMap = new HashMap<>();
        userTypeMap = new HashMap<>();
        userMap = new HashMap<>();
    }

    public User createUser(String username) {
        User user = new User(username);
        userMovieReviewMap.put(username, new HashSet<>());
        userMap.put(username, user);
        userTypeMap.put(username, UserType.VIEWER);
        return user;
    }

    public HashMap<String, Set<String>> getUserMovieReviewMap() {
        return userMovieReviewMap;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userMovieReviewMap=" + userMovieReviewMap +
                '}';
    }
}
