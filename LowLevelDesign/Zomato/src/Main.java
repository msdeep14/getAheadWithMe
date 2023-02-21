
/**
 * Low Level Design of Online Food Delivery - such as Zomato, Swiggy, DoorDash
 *
 * Focus on basic requirements from interview standpoint -
 * Customers -->
 * 1. Search for restaurants and dishes.
 * 2. Place Order for dish/multiple dishes from restaurant. Single order can contain only items from same restaurant.
 * 3. Make Payment
 * 4. Track the Order
 * 5. Rate the Food/Delivery Partner.
 *
 * Zomato System -->
 * 1. Add new restaurants. [This process could be something on lines of restaurant owners raise a request
 * to onboard to zomato and once request is approved, owners can start accepting order and add food items on
 * web app.]
 * 2. Assign delivery partner to deliver food.
 * 3. Intermediary between customers and restaurant owners. Customers place order, zomato forwards the requests
 * to restaurant, restaurant accepts the request, zomato sends confirmation back to customer.
 *
 * Restaurant Owners -->
 * 1. Add/update food items
 * 2. Accept order
 * 3. Analytics - Check total orders placed at restaurant and amount earned and customer ratings.
 *
 * Delivery Person -->
 * 1. Accept request from zomato to pick up food.
 * 2. Pick up food from restaurant.
 * 3. Deliver food to Customer.
 * 4. Analytics - View total orders delivered/amount earned/ratings.
 * */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //Add restaurants
    }
}