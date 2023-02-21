package service.crud;

import model.category.CategoryType;
import model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCatalog {
    //mapping on basis of product Id
    Map<String, Product> productCatalogMap;
    Map<CategoryType, List<Product>> productMapByType;
    Map<String, List<Product>> productMapByName;

    public ProductCatalog() {
        this.productCatalogMap = new HashMap<>();
        this.productMapByName = new HashMap<>();
        this.productMapByType = new HashMap<>();
    }

    public void createProduct(String productId, String productDescription, double price, CategoryType type,
                                 long quantity, boolean isProductSponsored) {
        if(productCatalogMap.containsKey(productId)) {
            throw new RuntimeException("Product exists with productId:"+productId+" in catalog,");
        }
        Product product = new Product();
        product.setProductId(productId);
        product.setProductDescription(productDescription);
        product.setProductSponsored(isProductSponsored);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategoryType(type);

        productCatalogMap.put(productId, product);

        if(productMapByName.containsKey(productDescription)) {
            List<Product> productList = productMapByName.get(productDescription);
            productList.add(product);
            productMapByName.put(productDescription, productList);
        } else {
            productMapByName.put(productDescription, new ArrayList<>(List.of(product)));
        }

        if(productMapByType.containsKey(type)) {
            List<Product> productList = productMapByType.get(type);
            productList.add(product);
            productMapByType.put(type, productList);
        } else {
            productMapByType.put(type, new ArrayList<>(List.of(product)));
        }
    }


    public Map<String, Product> getProductCatalogMap() {
        return productCatalogMap;
    }

    public Map<CategoryType, List<Product>> getProductMapByType() {
        return productMapByType;
    }

    public Map<String, List<Product>> getProductMapByName() {
        return productMapByName;
    }
}
