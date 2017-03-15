package repository;

import domain.Movie;
import domain.validators.RentalException;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Nicu on 3/11/2017.
 */
public class MovieFileRepository extends InMemoryRepository<Long, Movie> {
    private String fileName;

    public MovieFileRepository(Validator<Movie> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String name = items.get((2));
                String director = items.get((3));
                String genre = items.get((4));
                int availableCopies = Integer.parseInt(items.get(5));

                Movie movie = new Movie(name, director, genre, availableCopies);
                movie.setId(id);

                try {
                    super.save(movie);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Movie entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getName() + "," + entity.getDirector() + ","+ entity.getGenre() +"," + entity.getAvailableCopies());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
