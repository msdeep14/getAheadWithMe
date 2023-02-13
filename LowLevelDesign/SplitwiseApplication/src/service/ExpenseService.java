package service;

import model.account.User;
import model.expense.EqualExpense;
import model.expense.Expense;
import model.expense.ExpenseType;
import model.split.Split;

import java.util.List;

public class ExpenseService {


    public static Expense createExpense(ExpenseType expenseType, double totalAmount, User paidBy, List<Split> splits,
                                        String expenseDescription, List<User> participants, String groupName) {
        switch(expenseType) {
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(totalAmount*100/totalSplits))/100.0;
                for(Split split: splits) {
                    split.setAmount(splitAmount);
                }
                return new EqualExpense(groupName, expenseDescription, paidBy, totalAmount, participants, splits);
            default:
                return null;
        }
    }
}
