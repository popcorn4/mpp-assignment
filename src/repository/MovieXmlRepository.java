package repository;

import domain.Movie;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Nicu on 3/11/2017.
 */
public class MovieXmlRepository extends InMemoryRepository<Long, Movie> {
    private String fileName;

    public MovieXmlRepository(Validator<Movie> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData() {
        List<Movie> movies = new XmlReader<Long, Movie>(fileName).loadEntities();
        for (Movie movie : movies) {
            try {
                super.save(movie);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        new XmlWriter<Long, Movie>(fileName).save(entity);
        return Optional.empty();
    }
}