package model.product;

import model.category.CategoryType;

public class Product {
    String productId;
    String productDescription;
    CategoryType categoryType;
    long quantity;
    boolean isProductSponsored;
    double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isProductSponsored() {
        return isProductSponsored;
    }

    public void setProductSponsored(boolean productSponsored) {
        isProductSponsored = productSponsored;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", categoryType=" + categoryType +
                ", quantity=" + quantity +
                ", isProductSponsored=" + isProductSponsored +
                ", price=" + price +
                '}';
    }
}
