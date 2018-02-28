package mantis.model;

import java.math.BigInteger;

/**
 * Created by User on 26.02.2018.
 */
public class Project {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public Project withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }


}
