package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.utils.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    private String name;
    private String surname;
    private String email;
    private Type type;
    public List<Activity> activities;



    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }


    public Person(String name, String surname, String email, Type type){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
    }

    public Person(Type type){
        this.type = type;
    }

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
}
