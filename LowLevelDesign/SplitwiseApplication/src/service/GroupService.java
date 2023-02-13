package service;

import dao.SplitWiseBalanceManager;
import model.account.Group;

import java.util.List;

public class GroupService {

    SplitWiseBalanceManager splitWiseBalanceManager;


    public GroupService(SplitWiseBalanceManager splitWiseBalanceManager) {
        this.splitWiseBalanceManager = splitWiseBalanceManager;
    }

    public Group addGroup(String groupName, List<String> groupUsers, String groupDescription) {
        return splitWiseBalanceManager.addGroup(groupName, groupUsers, groupDescription);
    }
}
