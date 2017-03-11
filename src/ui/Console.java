package ui;

import domain.Movie;
import domain.validators.MovieValidator;
import domain.validators.RentalException;
import domain.validators.ValidatorException;
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
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            try{
                System.out.println(
                        "MAIN MENU \n" +
                                "1. movie menu \n"+
                                "2. client menu \n"+
                                "3. rent menu \n" +
                                "0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        movieMenu();
                    case "2":
                        clientMenu();
                    case "3":
                        rentMenu();
                    case "0":
                        break;
                    default:
                        System.out.println("Invalid command !");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private void movieMenu(){
        printAllMovies();
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            try{
                printAllMovies();
                System.out.println(
                                "MOVIE MENU \n" +
                                "1. add movie \n"+
                                "2. delete movie \n"+
                                "3. update movie \n" +
                                "4. filter movie \n" +
                                "0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        addMovies();
                    case "2":
                        deleteMovies();
                    case "3":
                        updateMovies();
                    case "4":
                        filterMovies();
                    case "0":
                        break;
                    default:
                        System.out.println("Invalid command !");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private void clientMenu(){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            try{
                printAllMovies();
                System.out.println(
                                "CLIENT MENU \n"+
                                "1. add client \n"+
                                "2. delete client \n"+
                                "3. update client \n" +
                                "4. filter client \n" +
                                "0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        return;
                    case "2":
                        return;
                    case "3":
                        return;
                    case "4":
                        return;
                    case "0":
                        break;
                    default:
                        System.out.println("Invalid command !");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private void rentMenu(){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while(true){
            try{
                printAllMovies();
                System.out.println(
                                "RENT MENU \n"+
                                "1. add client \n"+
                                "2. delete client \n"+
                                "3. update client \n" +
                                "4. filter client \n" +
                                "0. exit"

                );
                command = bufferRead.readLine();
                switch (command){
                    case "1":
                        break;
                    case "2":
                        return;
                    case "3":
                        return;
                    case "4":
                        return;
                    case "0":
                        break;
                    default:
                        System.out.println("Invalid command !");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
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
            }
            return movie;
        }catch (ValidatorException e){
            System.out.println("Error: " + e);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
