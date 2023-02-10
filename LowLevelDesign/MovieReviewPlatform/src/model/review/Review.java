package model.review;

import model.user.User;

public class Review {
    String userName;
    String movieName;
    int rating;

    public Review(String userName, String movieName, int rating) {
        this.userName = userName;
        this.movieName = movieName;
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userName='" + userName + '\'' +
                ", movieName='" + movieName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
