package service;

import dao.SplitWiseBalanceManager;
import model.expense.Expense;
import model.expense.ExpenseType;
import model.split.Split;

import java.util.List;
import java.util.Map;

public class SplitWiseService {
    SplitWiseBalanceManager splitWiseBalanceManager;


    public SplitWiseService(SplitWiseBalanceManager splitWiseBalanceManager) {
        this.splitWiseBalanceManager = splitWiseBalanceManager;
    }

    public void addExpense(ExpenseType expenseType, double totalAmount, String paidBy, List<Split> splits,
                           String description, List<String> participants) {
        splitWiseBalanceManager.addUserExpense(expenseType, totalAmount, paidBy, splits,
                description, participants);
    }

    public void addExpense(ExpenseType expenseType, double totalAmount, String paidBy, List<Split> splits,
                           String description, List<String> participants, String groupName) {
        splitWiseBalanceManager.addGroupExpense(expenseType, totalAmount, paidBy, splits,
                description, participants, groupName);
    }

    public void showUserExpenses(String userName) {
        List<String> userBalanceList = splitWiseBalanceManager.getUserBalance(userName);
        System.out.println("\nExpense List for :{"+userName+"}");
        if(userBalanceList.isEmpty()) {
            System.out.println("No entries yet");
        } else {
            for(String balance : userBalanceList) {
                System.out.println(balance);
            }
        }
    }

    public void showGroupExpense(String groupName) {
        List<String> groupBalanceList = splitWiseBalanceManager.getGroupBalance(groupName);
        System.out.println("\n Expense list for Group:"+groupName);

        if(groupBalanceList.isEmpty()) {
            System.out.println("No entries in group");
        } else {
            for(String balance : groupBalanceList) {
                System.out.println(balance);
            }
        }
    }

    public void showAllExpenses() {
        System.out.println("\nList of all expenses");
        List<Expense> expenses = splitWiseBalanceManager.getAllExpenses();
        for(Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}
