import model.category.CategoryType;
import service.crud.ProductCatalog;
import service.search.SearchService;
import service.search.SearchType;

/**
 * Design ecommerce platform like Amazon/Flipkart - Machine Coding 1.5-2hrs
 *
 * Requirements --->
 * 1. Create/Update/Delete categories
 * 2. Create/Update/Delete products.
 * 3. Search on basis of any product attributes like brand/name/type.
 * 4. Sponsored products should be displayed first in search results.
 *
 * Non-Functional
 * 1. Sample test cases for code execution
 * 2. Object-oriented design and implementation.
 *
 *
 * Thinking of a solution --->
 *
 * 1. To look for solution at scale - it will make sense to store data in DynamoDB + elasticSearch.
 * Here DDB is source of truth for all the information and ES can be additional storage that can be used to serve
 * search functionality. Data can be indexed in ES on all the required product attributes - such as brand, name and type
 * Data Type of these attributes could be -
 * brand - text, fuzzy search should be allowed
 * type - keyword, specific category should be selected
 * name - fuzzy search along with some of the text matching. We can specify % of text matching from what is being stored
 * in the ElasticSearch.
 *
 * 2. High level should look something like this --->
 * Admins should be able to add products/categories
 * AdminConsole -> backend service -> DynamoDB -> Lambda -> ElasticSearch.
 * BackendService stores data to DDB. Lambda is invoked on DDB streams which create data entry as per ES and stores them
 * in bulk.
 *
 * Search functionality can be directly served with ES.
 * CustomerPortal -> BackendService -> ElasticSearch
 * */
public class Main {
    public static void main(String[] args) {

        //There should be extra layer between services and main so as main class don't access all the internal structures
        //created such as hashmaps.

        ProductCatalog productCatalog = new ProductCatalog();
        //quantity is just for product storage, quantity should not be reflected to customer.
        //This is for internal check that Amazon will be able to fulfill the order of customers - this quantity can be reduced as orders are placed and
        //increased as more inventory is added.
        productCatalog.createProduct("ASIN123", "nokia mobile", 5000, CategoryType.ELECTRONICS, 10, false);
        productCatalog.createProduct("ASIN124", "nokia mobile", 5000, CategoryType.ELECTRONICS, 5, false);


        SearchService searchService = new SearchService(productCatalog.getProductCatalogMap(),
                productCatalog.getProductMapByType(),
                productCatalog.getProductMapByName());

        System.out.println(searchService.search("nokia mobile", SearchType.NAME));
        System.out.println("Welcome to MsDeep Singh ka ecommerce platform");

    }
}