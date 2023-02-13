package model.expense;

import model.account.User;
import model.split.Split;

import java.util.List;
import java.util.UUID;

public abstract class Expense {
    String expenseId;
    String groupName; //Non-null means this is group expense; else group expense.
    String expenseDescription;
    User paidBy;
    double totalAmount;
    List<User> participants;
    List<Split> expenseSplitDetails;

    public Expense(String groupName, String expenseDescription, User paidBy, double totalAmount,
                   List<User> participants, List<Split> expenseSplitDetails) {
        this.expenseId = UUID.randomUUID().toString();
        this.groupName = groupName;
        this.expenseDescription = expenseDescription;
        this.paidBy = paidBy;
        this.totalAmount = totalAmount;
        this.participants = participants;
        this.expenseSplitDetails = expenseSplitDetails;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId='" + expenseId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", expenseDescription='" + expenseDescription + '\'' +
                ", paidBy=" + paidBy +
                ", totalAmount=" + totalAmount +
                ", participants=" + participants +
                ", expenseSplitDetails=" + expenseSplitDetails +
                '}';
    }
}
