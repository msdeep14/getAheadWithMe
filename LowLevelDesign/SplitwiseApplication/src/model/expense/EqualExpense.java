package model.expense;

import model.account.User;
import model.split.Split;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(String groupName, String expenseDescription, User paidBy,
                        double totalAmount, List<User> participants, List<Split> expenseSplitDetails) {
        super(groupName, expenseDescription, paidBy, totalAmount, participants, expenseSplitDetails);
    }
}
