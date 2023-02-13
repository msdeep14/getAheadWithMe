package model.split;

import model.account.User;

public class PercentSplit extends Split {
    int percent;

    public PercentSplit(User user) {
        super(user);
    }

    public PercentSplit(User user, int percent) {
        super(user);
        this.percent = percent;
    }
}
