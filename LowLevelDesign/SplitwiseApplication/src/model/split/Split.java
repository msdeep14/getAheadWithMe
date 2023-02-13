package model.split;

import model.account.User;

public class Split {
    User user;
    double amount;


    public Split(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Split{" +
                "user=" + user +
                ", amount=" + amount +
                '}';
    }
}
