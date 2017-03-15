package domain;

import java.util.List;

/**
 * Created by user on 3/7/2017.
 */
public class Client extends BaseEntity<Long> {
    //region Fields
    String name;
    int age;
//    List<Long> rentedMovies; // the rented movies this client has currently in his possession
    //endregion

    //region Constructor
    public Client() {}

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //endregion

    //region Methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Client - " + this.name + ", " + this.age + " years old";
    }
    //endregion
}
