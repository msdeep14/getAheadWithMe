package model.movie;

import java.util.List;

public class Movie {
    String movieName;
    long releaseYear;
    List<Genre> movieGenre;


    public Movie(String movieName, long releaseYear, List<Genre> movieGenre) {
        this.movieName = movieName;
        this.releaseYear = releaseYear;
        this.movieGenre = movieGenre;
    }

    public String getMovieName() {
        return movieName;
    }

    public long getReleaseYear() {
        return releaseYear;
    }

    public List<Genre> getMovieGenre() {
        return movieGenre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + '\'' +
                ", releaseYear=" + releaseYear +
                ", movieGenre=" + movieGenre +
                '}';
    }
}
