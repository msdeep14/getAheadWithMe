import dao.SplitWiseBalanceManager;
import model.account.Group;
import model.account.User;
import model.expense.ExpenseType;
import model.split.EqualSplit;
import model.split.Split;
import service.GroupService;
import service.SplitWiseService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        System.out.println("SplitWise Application!!!");

        SplitWiseBalanceManager splitWiseBalanceManager = SplitWiseBalanceManager.getSplitWiseBalanceManagerInstance();
        UserService userService = new UserService(splitWiseBalanceManager);
        GroupService groupService = new GroupService(splitWiseBalanceManager);
        SplitWiseService splitWiseService = new SplitWiseService(splitWiseBalanceManager);

        //create users
        User user1 = userService.addUser("Mandeep");
        User user2 = userService.addUser("Sandeep");
        User user3 = userService.addUser("Bob");
        User user4 = userService.addUser("Alice");


        //Create Expense 1
        Split split1 = new EqualSplit(user1);
        Split split2 = new EqualSplit(user2);
        Split split3 = new EqualSplit(user3);
        List<Split> splitListForExpense1 = new ArrayList<>(List.of(split1, split2, split3));
        splitWiseService.addExpense(ExpenseType.EQUAL, 900, "Mandeep",
                splitListForExpense1, "Expense Number 1",
                List.of("Mandeep", "Sandeep","Bob"));

        //Display balance for users
        splitWiseService.showUserExpenses("Mandeep");
        splitWiseService.showUserExpenses("Alice");
        splitWiseService.showUserExpenses("Bob");

        //group Expense

        //create group
        Group group1 = groupService.addGroup("MyTestGroup", List.of("Mandeep","Bob"),
                "This group is created for testing");

        splitWiseService.addExpense(ExpenseType.EQUAL, 900, "Mandeep",
                splitListForExpense1, "Expense Number 1",
                List.of("Mandeep", "Sandeep","Bob"), "MyTestGroup");

        //Get Group Expense
        splitWiseService.showGroupExpense("MyTestGroup");


        //List all expenses
        splitWiseService.showAllExpenses();

    }
}