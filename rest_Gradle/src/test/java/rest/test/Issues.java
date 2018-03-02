package rest.test;

/**
 * Created by User on 27.02.2018.
 */
public class Issues {
    private int id;
    private String subject;
    private String description;

    public int getId() {
        return id;
    }



    public Issues withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issues withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issues withDescription(String description) {
        this.description = description;
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issues issues = (Issues) o;

        if (id != issues.id) return false;
        if (subject != null ? !subject.equals(issues.subject) : issues.subject != null) return false;
        return description != null ? description.equals(issues.description) : issues.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
