package model.restaurant;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
    List<FoodItem> foodItemList;

    public FoodMenu() {
        this.foodItemList = new ArrayList<>();
    }

    public boolean addFoodItems(List<FoodItem> foodItemList) {
        this.foodItemList.addAll(foodItemList);
        return true;
    }
}
