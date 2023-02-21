package model.restaurant;

import model.common.Location;

import java.util.UUID;

public class Restaurant {
    private String restaurantId;
    private String restaurantName;
    private Location restaurantLocation;
    private FoodMenu foodMenu;

    public Restaurant(String restaurantName, Location restaurantLocation) {
        this.restaurantId = UUID.randomUUID().toString();
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
    }

    //APIs
    public void addFoodMenu(FoodMenu foodMenu) {


    }
}
