package ui;

import domain.Movie;
import domain.validators.MovieValidator;
import domain.validators.RentalException;
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
    private MovieValidator validator = new MovieValidator();

    public Console(MovieService ms) {
        this.movieService = ms;
    }

    public void runConsole() {
        addMovies();
        printAllMovies();
        filterMovies();
        deleteMovies();
        updateMovies();
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

    private void deleteMovies() {
        while (true) {
            Movie movie = readMovie();
            if (movie == null || movie.getId() < 0 || !movieService.getAllMovies().contains(movie))
                break;
            try {
                movieService.deleteMovie(movie);
            } catch (Exception e) {
                System.out.println("The movie read is not valid or is not in the list of movies.");
            }
        }
    }

    private void updateMovies() {
        while (true) {
            Movie movie = readMovie();
            if (movie == null || movie.getId() < 0 || !movieService.getAllMovies().contains(movie))
                break;
            try {
                movieService.updateMovie(movie);
            } catch (Exception e) {
                System.out.println("The movie read is not valid or is not in the list of movies.");
            }
        }
    }

    private Movie readMovie() throws RentalException {
        System.out.println("Read movie {id, name, director, genre, availableCopies}: ");

        Movie movie;
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("id(int): ");
            Long id = Long.valueOf(bufferRead.readLine());// ...
            if (id<0){
                Movie m =  new Movie();
                m.setId(id);
                return m;
            }else{
                System.out.println("movie name(string): ");
                String name = bufferRead.readLine();
                System.out.println("movie director(string): ");
                String director = bufferRead.readLine();
                System.out.println("movie genre(string): ");
                String type = bufferRead.readLine();
                System.out.println("available copies(int): ");
                int availableCopies = Integer.parseInt(bufferRead.readLine());// ...
                movie = new Movie(name, director, type, availableCopies);
                movie.setId(id);
                validator.validate(movie);
            }
            return movie;
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
