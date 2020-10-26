package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.utils.Typology;

import java.util.ArrayList;
import java.util.List;
/**
 *
 *  The {@code Activity} is an abstract class that define the behavior of the activity into the circle.
 *  It is the father class of {@code Race} and {@code Course}.
 *
 * @author Ruslan, Francesca, Nick
 * @see Person
 *
 */
public abstract class Activity
{
    
    private long id;
    /**
     * List of people subscribe in the activity
     */
    public List<Person> people;
    private String name;
    private Typology type;

    /**
     * Main costructor of the class
     * @param name
     * @param type
     */
    public Activity(String name, Typology type)
    {
        this.name=name;
        this.people=new ArrayList<>();
        this.type=type;
    }

    /**
     * Default costructor of the class
     */
    public Activity(){ }

    /**
     * Third type of class costructor
     * @param activity
     */
    public Activity(Activity activity){
        this.id = activity.id;
        this.people = activity.people;
        this.name = activity.name;
        this.type = activity.type;
    }

    public Activity setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setType(Typology type)
    {
        this.type = type;
        return this;
    }

    public Typology getType() {
        return type;
    }

    public long getId() { return id; }

    public Activity setId(long id) {
        this.id = id;
        return this;
    }

    public List<Person> getPeople() { return people; }

    public Activity setPeople(List<Person> people) {
        this.people = people;
        return this;
    }

    /**
     * Abstract  method to subscribe the person in an activity
     * @param person
     */
    public void subscribe(Person person){ }
    /**
     * Abstract  method to unsubscribe the person in an activity
     * @param person
     */
    public void unsubscribe(Person person){ }
}
