package service;

import dao.SplitWiseBalanceManager;
import model.account.User;

public class UserService {
    SplitWiseBalanceManager splitWiseBalanceManager;


    public UserService(SplitWiseBalanceManager splitWiseBalanceManager) {
        this.splitWiseBalanceManager = splitWiseBalanceManager;
    }

    public User addUser(String userName) {
        return splitWiseBalanceManager.addUser(userName);
    }

    public User getUser(String userName) {
        return splitWiseBalanceManager.getUser(userName);
    }
}
