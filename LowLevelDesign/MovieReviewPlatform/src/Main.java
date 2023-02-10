import model.movie.Genre;
import model.movie.Movie;
import model.review.Review;
import model.user.User;
import model.user.UserType;
import service.MovieService;
import service.ReviewService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Design a Movie Review Service
 *
 * 1. Add users.
 * 2. Add Movies
 * 3. Add reviews
 *
 * 1. A user is of type VIEWER as it is initially created. It is promoted to CRITIC once user reviews
 * 3 movies.
 * 2. Rating of CRITIC is considered as double of what is actually given
 * 3. A user should be allowed to add only one review. No update functionality.
 *
 * APIs --
 * 1. top n movies in specific year - for critic and viewer.
 * 2. top n movies in specific genre - for critic and viewer.
 * 3. average rating of movie.
 *
 * Time for implementation 1hr 45 minutes.
 * **/
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //create users
        UserService userService = new UserService();
        userService.createUser("SRK");
        userService.createUser("Salman");
        userService.createUser("Deepika");
        //System.out.println("userMovieReviewMap: "+userService.getUserMovieReviewMap());

        //create movie
        MovieService movieService = new MovieService();
        movieService.createMovie("Don", 2006, new ArrayList<>(List.of(Genre.COMEDY, Genre.ACTION)));
        movieService.createMovie("Tiger", 2008, new ArrayList<>(List.of(Genre.DRAMA)));

        //System.out.println(movieService.getMovieMap());

        //add review
        ReviewService reviewService = new ReviewService(movieService.getMovieMap(), userService.getUserMovieReviewMap(),
                userService.getUserMap(), userService.getUserTypeMap());

        try {
            reviewService.reviewMovie("SRK", "Don", 2);
        } catch (Exception e) {
            System.out.println("Exception encountered in adding review: "+e);
        }


        try {
            //duplicate review
            //reviewService.reviewMovie("SRK", "Don", 4);

            reviewService.reviewMovie("Salman", "Don", 5);
            reviewService.reviewMovie("Deepika", "Tiger", 3);
        } catch (Exception e) {
            System.out.println("Exception encountered in adding review: "+e);
        }


        //get reviews
        try {
            System.out.println("Top n movies in year 2006");
            Map<String, Integer> getReviews = reviewService.getTopNMoviesInYear(2006, UserType.VIEWER, 2);
            for(Map.Entry<String,Integer> mapElement : getReviews.entrySet()) {
                String movieName = mapElement.getKey();
                Integer rating = mapElement.getValue();
                System.out.println("Movie:"+movieName+" with rating:"+rating);
            }
        } catch (Exception e) {
            System.out.println(e);
        }



    }
}