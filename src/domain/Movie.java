package domain;

/**
 * Created by user on 3/5/2017.
 */
public class Movie extends BaseEntity<Long> {
    //region Fields
    String name;
    String director;
    String genre;
    int availableCopies; //number of available copies available for rent
    //endregion

    //region Constructor
    public Movie(){
    }
    public Movie(String name, String director, String type, int availableCopies) {
        this.name = name;
        this.director = director;
        this.genre = type;
        this.availableCopies = availableCopies;
    }
    //endregion

    //region Methods
    public String getName(){return this.name;}
    public String getDirector(){return this.director;}
    public String getGenre(){
        return this.genre;
    }
    public int getAvailableCopies(){return this.availableCopies;}
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", available copies =" + availableCopies +
                "} " + super.toString();
    }
    //endregion
}