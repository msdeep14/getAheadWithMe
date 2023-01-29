package facadePattern;

public class SearchDataFacade {
    public Object getSearchData() {
        //get ElasticSearch instance from ES Factory
        //get DDB instance from DDB factory
        //es.query
        //ddb.batchLoad
        //some transformation to convert into Object readable by clients.
        return "search results";
    }
}
