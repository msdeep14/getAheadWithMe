package facadePattern;

/**
 * Type of Structural Design Pattern
 *
 * This pattern hides the complexities of system and provides simple interface to client.
 *
 * Example - To figure out search results, application needs to query both DynamoDB and ElasticSearch.
 * Now there are two options-
 * 1. Client invoke both DDB and ElasticSearch and collate results
 * 2. Facade layer in between which expose an API that can clients can use. The API internally calls both DDB and ES to
 * gather results and clients don't have to worry about data layers.
 *
 *
 *
 * */
public class FacadePatternController {
    public static void main(String[] args) {
        System.out.println(new SearchDataFacade().getSearchData());
    }
}
