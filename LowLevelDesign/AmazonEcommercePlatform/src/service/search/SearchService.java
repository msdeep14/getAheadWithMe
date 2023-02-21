package service.search;

import model.category.CategoryType;
import model.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchService {
    Map<String, Product> productCatalogMap;
    Map<CategoryType, List<Product>> productMapByType;
    Map<String, List<Product>> productMapByName;

    public SearchService(Map<String, Product> productCatalogMap,
                         Map<CategoryType, List<Product>> productMapByType,
                         Map<String, List<Product>> productMapByName) {
        this.productCatalogMap = productCatalogMap;
        this.productMapByType = productMapByType;
        this.productMapByName = productMapByName;
    }

    public List<Product> search(String identifier, SearchType searchType) {
        switch (searchType) {
            case BRAND:
                return searchWithBrand(identifier);
            case NAME:
                return searchWithName(identifier);
            case TYPE:
                return searchWithType(identifier);
            default:
                throw new RuntimeException("search Type not supported");

        }
    }

    private List<Product> searchWithBrand(String identifier) {
        return new ArrayList<>();
    }

    private List<Product> searchWithName(String identifier) {
        if(productMapByName.containsKey(identifier)) {
            //sort the list so as sponsored products appear first in list
            //1. iterate over the list and construct two lists with sponsored and non-sponsored.
            //2. return the appended list of both the lists - sponsoredList+nonSponsoredList.
            return productMapByName.get(identifier);
        } else {
            return new ArrayList<>();
        }
    }

    private List<Product> searchWithType(String identifier) {
        return new ArrayList<>();
    }
}
