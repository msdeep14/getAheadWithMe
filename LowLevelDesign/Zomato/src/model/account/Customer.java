package model.account;

import java.util.List;

public abstract class Customer extends Account {

    public abstract List<String> getPreviousOrders();
    public abstract String getMyProfile();
}
