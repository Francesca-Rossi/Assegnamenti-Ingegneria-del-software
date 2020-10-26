package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.utils.Type;
import java.util.ArrayList;
import java.util.List;


/**
 *
 *  The {@code Person} is an abstract class.
 *  It is the father class of {@code Member} and {@code Admin}.
 *
 * @author Francesca, Ruslan, Nick
 * @see Member
 * @see Admin
 */
public abstract class Person {
    /**
     * Is the unique ID of the person
     */
    private long id;
    /**
     * Is the name of the person
     */
    private String name;
    /**
     * Is the surname of the person
     */
    private String surname;
    /**
     * Is the email of the person
     */
    private String email;
    /**
     * Is the password of the person
     */
    private String password;
    /**
     * Is the typology of the person
     */
    private Type type;
    /**
     * Is the list of activity which subscribe this person
     */
    public List<Activity> activities;

    /**
     * First type of class costructor
     * @param name
     * @param surname
     * @param email
     */
    public Person(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password=password;
    }

    /**
     * Second type of class costructor
     * @param name
     * @param surname
     * @param email
     * @param type
     */
    public Person(String name, String surname, String email, Type type, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
        this.password=password;
        this.activities = new ArrayList<>();
    }

    /**
     * Third type of class costructor
     * @param type
     */
    public Person(Type type){
        this.type = type;
    }

    /**
     * Fourth type of class costructor
     * @param person
     */
    public Person(Person person){
        this.id = person.id;
        this.name = person.name;
        this.surname = person.surname;
        this.email = person.email;
        this.password=person.password;
        this.type = person.type;
        this.activities=person.activities;
    }


    /**
     * Default class costructor
     */
    public Person() {
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Person setType(Type type) {
        this.type = type;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() { return this.password; }
    public Person setPassword(String password){
        this.password = password;
        return this;
    }

    public long getId() {return id;}

    public Person setId(long id) {
        this.id = id;
        return this;
    }
}
