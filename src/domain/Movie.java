package domain;

/**
 * Created by user on 3/5/2017.
 */
public class Movie extends BaseEntity<Long> {
    //region Parameters
    String name;
    String director;
    String type;
    int availableCopies; //number of available copies available for rent
    //endregion

    //region Constructor
    public Movie(){
    }
    public Movie(String name, String director, String type, int availableCopies) {
        this.name = name;
        this.director = director;
        this.type = type;
        this.availableCopies = availableCopies;
    }
    //endregion

    //region Methods
    public String getType(){
        return this.type;
    }
    public String toString() {
        return "Movie '" + name + "', " + type + ", directed by " + director + " has " + availableCopies + " avaible copies for rent.";
    }
    //endregion
}