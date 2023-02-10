package service;

import model.movie.Movie;
import model.review.Review;
import model.user.User;
import model.user.UserType;

import java.time.Year;
import java.util.*;

public class ReviewService {

    Map<String, Movie> movieMap;
    Map<String, Set<String>> userMovieReviewMap;
    Map<String, List<Review>> movieReviewMap;
    Map<String, User> userMap;
    Map<String, UserType> userTypeMap;


    public ReviewService(Map<String, Movie> movieMap, Map<String, Set<String>> userMovieReviewMap,
                         Map<String, User> userMap, Map<String, UserType> userTypeMap) {
        this.movieMap = movieMap;
        this.userMovieReviewMap = userMovieReviewMap;
        this.movieReviewMap = new HashMap<>();
        this.userMap = userMap;
        this.userTypeMap = userTypeMap;
    }

    public void reviewMovie(String userName, String movieName, int rating) {
        Review review = new Review(userName, movieName, rating);

        if(!movieMap.containsKey(movieName)) {
            throw new RuntimeException("The movie: "+movieName+" don't exist for review");
        } else {
            //movie should be released
            Movie movie = movieMap.get(movieName);
            //it should be on exact date.....
            long year = Year.now().getValue();
            if(year < movie.getReleaseYear()) {
                throw new RuntimeException("Movie: "+movieName+" is not released yet for review");
            } else {
                //check if user already added the review
                Set<String> userReviewedMovieSet = userMovieReviewMap.get(userName);
                if(userReviewedMovieSet != null && userReviewedMovieSet.contains(movieName)) {
                    throw new RuntimeException("Movie: "+movieName+" is already reviewed by user: "+userName);
                }

                //add the review
                List<Review> reviews = movieReviewMap.get(movieName);
                if(reviews == null) {
                    reviews = new ArrayList<>();
                }
                System.out.println("Adding new Review for movie:"+movieName+" with rating:"+rating);
                reviews.add(review);
                movieReviewMap.put(movieName, reviews);
                userReviewedMovieSet.add(movieName);

                //also check total reviews by user if bypassed 3
                //promote user to CRITIC...
                //should nto be hardcoded
                if(userReviewedMovieSet.size() >= 3) {
                    User user = userMap.get(userName);
                    user.setUserType(UserType.CRITIC);
                }

                System.out.println("Review added successfully for movie:"+movieName+" by user: "+userName);

            }
        }
    }


    /**
     * List top n movies by total review score by viewer’ in a particular year of release.
     * List top n movies by total review score by ‘viewer’ in a particular genre.
     * List top n movies by total review score by ‘critics’ in a particular year of release.
     * List top n movies by total review score by ‘critics’ in a particular genre.
     * Average review score in a particular year of release.
     * Average review score in a particular genre.
     * */

    public Map<String, Integer> getTopNMoviesInYear(long year, UserType userType, int n) {
        switch (userType) {
            case CRITIC:
                return getMoviesByCritic(year, n);
            case VIEWER:
                return getMoviesByViewer(year, n, userType);
            default:
                throw new RuntimeException("userType not supported");
        }
    }

    private Map<String, Integer> getMoviesByCritic(long year, int n) {
        return new HashMap<>();
    }

    private Map<String, Integer> getMoviesByViewer(long year, int n, UserType userType) {
        //traverse movie review map
        //add the total rating of reviews
        //sort by rating.
        HashMap<String, Integer> movieRatingMap = new HashMap<>();
        System.out.println("MovieReviewMap: "+movieReviewMap);
        for(Map.Entry<String,List<Review>> mapElement : movieReviewMap.entrySet()) {
            String movieName = mapElement.getKey();
            Movie movie = movieMap.get(movieName);

            //check if released in this year....

            if(movie.getReleaseYear() == year) {
                List<Review> reviewList = mapElement.getValue();
                int totalRating=0;
                for(Review review : reviewList) {
                    String userName = review.getUserName();
                    if(userTypeMap.get(userName) == userType) {
                        totalRating += review.getRating();
                    }
                }
                movieRatingMap.put(movieName, totalRating);
            }
        }

        //sort map by totalRating
        //maxheap-- better solution
        movieRatingMap = sortByValue(movieRatingMap);

        Map<String, Integer> resultantMovieReviewMap = new HashMap<>();
        int i=0;
        for(Map.Entry<String,Integer> mapElement : movieRatingMap.entrySet()) {
            if(i>=n) {
                return movieRatingMap;
            }
            i+=1;
            resultantMovieReviewMap.put(mapElement.getKey(), mapElement.getValue());
        }

        return resultantMovieReviewMap;
    }


    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
