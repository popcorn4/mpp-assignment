import domain.Movie;

import repository.InMemoryRepository;
import repository.Repository;
import service.MovieService;
import ui.Console;

/**
 * Created by Nicu on 3/5/2017.
 */
public class main {
    public static void main(String[] args){
        Repository<Long, Movie> movieRepository =
                new InMemoryRepository<>();
        MovieService movieService = new MovieService(movieRepository);
        Console console = new Console(movieService);
        console.runConsole();

        //System.out.println("Hello World!");
    }
}
