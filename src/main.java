import domain.Movie;

import domain.validators.MovieValidator;
import domain.validators.Validator;
import repository.InMemoryRepository;
import repository.Repository;
import service.MovieService;
import ui.Console;

/**
 * Created by Nicu on 3/5/2017.
 */
public class main {
    public static void main(String[] args){
        Validator<Movie> validator = new MovieValidator();
        Repository<Long, Movie> movieRepository =
                new InMemoryRepository<>(validator);
        MovieService movieService = new MovieService(movieRepository);
        Console console = new Console(movieService);
        console.runConsole();

        //System.out.println("Hello World!");
    }
}
