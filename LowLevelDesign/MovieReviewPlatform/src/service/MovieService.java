package service;

import model.movie.Genre;
import model.movie.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {
    Map<String, Movie> movieMap;

    public Map<String, Movie> getMovieMap() {
        return movieMap;
    }

    public MovieService() {
        this.movieMap = new HashMap<>();
    }

    public void createMovie(String movieName, long releaseYear, List<Genre> genreList) {
        Movie movie = new Movie(movieName, releaseYear, genreList);
        movieMap.put(movieName, movie);
    }

    @Override
    public String toString() {
        return "MovieService{" +
                "movieMap=" + movieMap +
                '}';
    }
}
