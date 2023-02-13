package dao;

import model.account.Group;
import model.account.User;
import model.expense.Expense;
import model.expense.ExpenseType;
import model.split.Split;
import service.ExpenseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitWiseBalanceManager {
    List<Expense> expenseList;
    Map<String, User> userMap;
    Map<String, Group> groupMap;
    Map<String, Map<String, Double>> balanceSheet;

    Map<String, Map<String, Map<String, Double>>> groupBalanceSheet;

    /***
     * T1: user1 has to get 100 from user2
     * T2: user2 has to get 100 from user1
     *
     * user1 = 0
     *
     * T1, user1 update to +50
     * T2 user2 update to +50
     *
     * update(read and then write)
     *
     * allowed reads
     * T1 read the value is 50
     * T2 will read the value is 50
     *
     * allowed the write
     * T1+50
     * T2+50
     *
     * fencing tokens
     * maintain versioning
     * */
    @Override
    public String toString() {
        return "SplitWiseBalanceManager{" +
                "expenseList=" + expenseList +
                ", userMap=" + userMap +
                ", groupMap=" + groupMap +
                ", balanceSheet=" + balanceSheet +
                ", groupBalanceSheet=" + groupBalanceSheet +
                '}';
    }

    private SplitWiseBalanceManager() {
        expenseList = new ArrayList<>();
        userMap = new HashMap<>();
        groupMap = new HashMap<>();
        balanceSheet = new HashMap<>();
        groupBalanceSheet = new HashMap<>();
    }

    private static volatile SplitWiseBalanceManager splitWiseBalanceManager = null;

    public static SplitWiseBalanceManager getSplitWiseBalanceManagerInstance() {
        if(splitWiseBalanceManager == null) {
            synchronized (SplitWiseBalanceManager.class) {
                if(splitWiseBalanceManager == null) {
                    splitWiseBalanceManager = new SplitWiseBalanceManager();
                }
            }
        }
        return splitWiseBalanceManager;
    }

    public User addUser(String userName) {
        User user = new User(userName);
        if(userMap.containsKey(userName)) {
            throw  new RuntimeException("User with userName:"+userName+" already exists");
        }
        userMap.put(userName, user);
        balanceSheet.put(userName, new HashMap<>());
        return user;
    }

    public User getUser(String userName) {
        if(userMap.containsKey(userName)) {
            return userMap.get(userName);
        }
        throw new RuntimeException("User with userName:"+userName+" don't exist");
    }

    public Group addGroup(String groupName, List<String> participants, String groupDescription) {
        Group group = new Group(groupName, groupDescription);
        //create user list;
        if(groupMap.containsKey(groupName)) {
            throw new RuntimeException("Group with groupName:"+groupName+" already exists");
        }

        List<User> groupUsers = new ArrayList<>();

        Map<String, Map<String, Double>> userGroupMap = new HashMap<>();
        for(String userName : participants) {
            User user = getUser(userName);
            groupUsers.add(user);
            userGroupMap.put(userName, new HashMap<>());
        }
        group.setUserList(groupUsers);
        groupMap.put(groupName, group);
        groupBalanceSheet.put(groupName, userGroupMap);
        return group;
    }

    public Group getGroup(String groupName) {
        if(groupMap.containsKey(groupName)) {
            return groupMap.get(groupName);
        }
        throw new RuntimeException("Group with groupName:"+groupName+" don't exist");
    }


    public void addUserExpense(ExpenseType expenseType, double totalAmount, String paidBy, List<Split> splits,
                               String description, List<String> participants) {

        //get all participants
        List<User> userList = new ArrayList<>();
        for(String userName: participants) {
            User user1 = getUser(userName);
            userList.add(user1);
        }
        Expense expense = ExpenseService.createExpense(expenseType, totalAmount, getUser(paidBy), splits, description, userList, null);
        expenseList.add(expense);

        for(Split split : splits) {
            String paidTo = split.getUser().getUserName();
            Map<String, Double> balanceMap = balanceSheet.get(paidBy);
            if(!balanceMap.containsKey(paidTo)) {
                balanceMap.put(paidTo, 0.0);
            }
            balanceMap.put(paidTo, balanceMap.get(paidTo) + split.getAmount());

            balanceMap = balanceSheet.get(paidTo);
            if(!balanceMap.containsKey(paidBy)) {
                balanceMap.put(paidBy, 0.0);
            }

            balanceMap.put(paidBy, balanceMap.get(paidBy) - split.getAmount());
        }
    }

    public void addGroupExpense(ExpenseType expenseType, double totalAmount, String paidBy, List<Split> splits,
                           String description, List<String> participants, String groupName) {

        //get all participants
        List<User> userList = new ArrayList<>();
        for(String userName: participants) {
            User user1 = getUser(userName);
            userList.add(user1);
        }
        Expense expense = ExpenseService.createExpense(expenseType, totalAmount, getUser(paidBy), splits, description, userList, groupName);
        expenseList.add(expense);

        for(Split split : splits) {
            String paidTo = split.getUser().getUserName();

            Map<String, Map<String, Double>> balanceMap = groupBalanceSheet.get(groupName);

            Map<String, Double> paidByMap = balanceMap.get(paidBy);

            if(!paidByMap.containsKey(paidTo)) {
                paidByMap.put(paidTo, 0.0);
            }
            paidByMap.put(paidTo, paidByMap.get(paidTo) + split.getAmount());

            if(!paidByMap.containsKey(paidBy)) {
                paidByMap.put(paidBy, 0.0);
            }

            paidByMap.put(paidBy, paidByMap.get(paidBy) - split.getAmount());
        }

        //System.out.println("Updated group balance map: "+groupBalanceSheet.toString());
    }

    public List<String> getUserBalance(String userName) {
        List<String> userBalanceList = new ArrayList<>();
        for(Map.Entry<String, Double> userBalance : balanceSheet.get(userName).entrySet()) {
            if(userBalance.getValue() != 0) {
                userBalanceList.add(createUserView(userName, userBalance.getKey(), userBalance.getValue()));
            }
        }
        return userBalanceList;
    }

    public List<Expense> getAllExpenses() {
        return expenseList;
    }

    public List<String> getGroupBalance(String groupName) {
        List<String> groupBalanceList = new ArrayList<>();
        Map<String, Map<String, Double>> groupBalanceGroup = groupBalanceSheet.get(groupName);

        //traverse for all users
        Group group = getGroup(groupName);
        List<User> userList = group.getUserList();
        for(User user : userList) {
            String userName = user.getUserName();
            for(Map.Entry<String, Double> userBalance : groupBalanceGroup.get(user.getUserName()).entrySet()) {
                if(userBalance.getValue() != 0 && !userName.equals(userBalance.getKey())) {
                    groupBalanceList.add(createUserView(userName, userBalance.getKey(), userBalance.getValue()));
                }
            }
        }

        return groupBalanceList;
    }

    private String createUserView(String user1, String user2, double amount) {
        if(amount < 0) {
            return (user1+" owes "+user2+" : "+Math.abs(amount));
        } else {
            return  (user2+" owes "+user1+" : "+Math.abs(amount));
        }
    }

//    private String createOweString(String ) {
//
//    }

}
