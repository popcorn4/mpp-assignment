package ui;

import domain.Movie;
import service.MovieService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
/**
 * Created by Nicu on 3/5/2017.
 */

public class Console {
    private MovieService movieService;

    public Console(MovieService ms) {
        this.movieService = ms;
    }

    public void runConsole() {
        addMovies();
        printAllMovies();
        filterMovies();
    }

    private void filterMovies() {
        System.out.println("filtered movies (type 'action'):");
        Set<Movie> movies = movieService.filterMovieByType("action");
        movies.stream().forEach(System.out::println);
    }

    private void printAllMovies() {
        Set<Movie> movies = movieService.getAllMovies();
        movies.stream().forEach(System.out::println);
    }

    private void addMovies() {
        while (true) {
            Movie movie = readMovie();
            if (movie == null || movie.getId() < 0) {
                break;
            }
            try {
                movieService.addMovie(movie);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Movie readMovie() {
        System.out.println("Read movie {id, name, director, type, availableCopies}: ");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());// ...
            String name = bufferRead.readLine();
            String director = bufferRead.readLine();
            String type = bufferRead.readLine();
            int availableCopies = Integer.parseInt(bufferRead.readLine());// ...

            Movie movie = new Movie(name, director, type, availableCopies);
            movie.setId(id);

            return movie;
        } catch (IOException ex) {
            ex.printStackTrace();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return null;
    }
}
