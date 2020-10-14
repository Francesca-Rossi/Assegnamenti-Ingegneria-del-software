import java.util.ArrayList;
import java.util.List;

public abstract class Persona {
    private String name;
    private String surname;
    private String email;
    private Type type;
    public List<Activity> activities;

    public Persona() {
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Persona setName(String name) {
        this.name = name;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Persona setType(Type type) {
        this.type = type;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Persona setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Persona setEmail(String email) {
        this.email = email;
        return this;
    }
}
