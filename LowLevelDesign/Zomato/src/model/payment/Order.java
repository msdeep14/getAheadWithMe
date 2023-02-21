package model.payment;

import model.account.Customer;
import model.restaurant.FoodItem;
import model.restaurant.Restaurant;

import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private String restaurantId;
    private List<FoodItem> foodItems;
    private Payment paymentDetails;

    private boolean placeOrder() {

        return true;
    }

    private boolean acceptOrder() {

        return true;
    }

}
