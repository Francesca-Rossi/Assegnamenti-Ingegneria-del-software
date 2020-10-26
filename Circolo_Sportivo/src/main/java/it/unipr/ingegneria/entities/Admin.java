package it.unipr.ingegneria.entities;



import it.unipr.ingegneria.api.IOperationsPrevileged;
import it.unipr.ingegneria.api.IRandom;
import it.unipr.ingegneria.exception.ElementAlreadyPresentException;
import it.unipr.ingegneria.exception.ListEmptyException;
import it.unipr.ingegneria.utils.LogWriter;

import java.util.ArrayList;
import it.unipr.ingegneria.utils.Type;
import it.unipr.ingegneria.utils.Typology;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 *  The {@code Admin} class manage the operations inside the circle.
 *  Extends the abstract class {@code Person} and implement {@code IOperationsPrevileged<T,C>} interface.
 *
 * @author Ruslan, Francesca, Nick
 * @see Person
 * @see it.unipr.ingegneria.api.IOperationsPrevileged
 */
public class Admin extends Person implements IOperationsPrevileged<Person, Activity>, IRandom<Person, Activity> {
    /**
     * Is the main circle
     */
    private Circle c;
    /**
     * Is the log file
     */
    private LogWriter logWriter = LogWriter.getInstance();

    /**
     * Class costructor that extend one of the Person costructor
     * @param c is the istance of the circle
     */
    public Admin(Circle c)
    {
        super(Type.ADMIN);
        this.c = c;
    }
    /**
     * Class constructor that allows cloning
     * @param admin
     */
    public Admin(Admin admin){
        super(admin);
        this.c = admin.c;
    }


    /**
     * Subscribe the admin in a particular activity
     * @param activity the activity
     */

    @Override
    public void subscribe(Activity activity)
    {
        if (this.activities.contains(activity)) {
            throw new ElementAlreadyPresentException("The activity "+ activity.toString()+" is already in the list.");
        }
        else {
            this.activities.add(activity);
            activity.subscribe(this);
            logWriter.subscribe(this, activity);
        }

    }

    /**
     * unsubscribe the admin in a particular activity
     * @param activity is the activity
     */
    @Override
    public void unsubscribe(Activity activity) {
        if (this.activities.isEmpty())
        {
            throw new ListEmptyException("You can't remove an Activity because "+this.toString()+" are not subscribe any Activity");
        }
        else if (this.activities.contains(activity)!=true) {
            throw new NoSuchElementException("The activity "+activity.toString()+"is not present in the list.");
        }
        else {
            logWriter.unsubscribe(this, activity);
            this.activities.remove(activity);
            activity.unsubscribe(this);
        }
    }

    /**
     * Add a Person in the circle
     * @param person
     */
    @Override
    public void addUser(Person person) {
        long user_id;
        if (c.people.contains(person)) {
            throw new ElementAlreadyPresentException("The person "+ person.toString()+" is already present in the list.");
        }
        else {
            if (c.people.isEmpty()) //Set index to one if list is empty - First user
                user_id = 1;
            else {
                Person last_user = c.people.get(c.people.size() - 1); //Get last element in people
                user_id = last_user.getId() + 1; // User id is incremental to guarantee uniqueness
            }
            person.setId(user_id);
            logWriter.addUser(this, person);
            c.people.add(person);
        }
    }

    /**
     * Remove a Person from the circle
     * @param person
     */
    @Override
    public void deleteUser(Person person) {
        if (c.people.isEmpty()) {
            throw  new ListEmptyException("You can't remove a Person because there is not Person in the circle");
        }
        else if (c.people.contains(person)!=true)
        {
            throw new NoSuchElementException("The Person "+person.toString()+"is not present in the circle.");
        }
        else {
            logWriter.deleteUser(this, person);
            c.people.remove(person);
        }

    }

    /**
     * Edit a Person in the circle
     * @param prev_person
     * @param new_person
     */
    @Override
    public void editUser(Person prev_person, Person new_person) {
        if(c.people.isEmpty())
        {
            throw new ListEmptyException("you can't edit a Person because there is no people in the circle ");
        }
        else if (c.people.contains(prev_person)!=true) {
            throw new NoSuchElementException("You can't edit a Person "+prev_person.toString()+" that is not present in the circle.");
        }
        else {
            int u_index = -1;

            for (int i = 0; i < c.people.size(); i++) // Iterate over all people
            {
                if (c.people.get(i).getId() == prev_person.getId()) //Get index of person to edit
                {
                    u_index = i;
                    break;
                }
            }
            c.people.set(u_index, new_person); // Replace old user info with new user info
            logWriter.editUser(this, prev_person, new_person);
        }
    }

    /**
     * Get a Person in the circle
     * @param id
     */
    public Person getUserById(long id) {
        for (int i = 0; i < c.people.size(); i++) // Iterate over all people;
            if (c.people.get(i).getId() == id) //Get index of person to edit
                return c.people.get(i);

        return null;
    }


    /**
     * Add an Activity in the circle
     * @param activity
     */
    @Override
    public void addActivity(Activity activity) {
        long activity_id;
        if (c.activities.contains(activities)) {
            throw new ElementAlreadyPresentException("The activity "+ activity.toString()+" is already present in the list.");
        }
        else {
            if (c.activities.isEmpty()) //Set index to one if list is empty - First user
                activity_id = 1;
            else {
                Activity last_activity = c.activities.get(c.activities.size() - 1); //Get last element in activities
                activity_id = last_activity.getId() + 1; // activity id is incremental to guarantee uniqueness
            }
            activity.setId(activity_id);
            logWriter.addActivity(this, activity);
            c.activities.add(activity);
        }
    }

    /**
     * Remove an Ativity by the circle
     * @param activity
     */
    @Override
    public void deleteActivity(Activity activity) {
        if (c.activities.isEmpty()) {
            throw  new ListEmptyException("You can't remove this Activity because there is not Activity in the circle");
        }
        else if (c.activities.contains(activity)!=true)
        {
            throw new NoSuchElementException("The Activity "+activity.toString()+"is not present in the circle.");
        }
        c.activities.remove(activity);
        logWriter.deleteActivity(this, activity);
    }

    /**
     * Edit a Person in the circle
     * @param prev_activity
     * @param new_activity
     */
    @Override
    public void editActivity(Activity prev_activity, Activity new_activity) {
        if(c.activities.isEmpty())
        {
            throw new ListEmptyException("You cant' edit an Activity because there is no activities in the circle ");
        }
        else if (c.activities.contains(prev_activity)!=true) {
            throw new NoSuchElementException("You can't edit an activity "+prev_activity.toString()+" that is not present in the circle.");
        }
        else {
            int u_index = -1;

            for (int i = 0; i < c.activities.size(); i++) // Iterate over all activities
            {
                if (c.activities.get(i).getId() == prev_activity.getId()) //Get index of activity to edit
                {
                    u_index = i;
                    break;
                }
            }
            c.activities.set(u_index, new_activity); // Replace old activity info with new activity info
            logWriter.editActivity(this, prev_activity, new_activity);
        }
    }

    /**
     * Get an Activity in the circle
     * @param id
     */
    public Activity getActivityById(long id) {
        for (int i = 0; i < c.activities.size(); i++) // Iterate over all activities;
            if (c.activities.get(i).getId() == id) //Get index of activity to edit
                return c.activities.get(i);

        return null;
    }
    /**
     * Get a Random person in the circle
     * @param type The type of the person
     */
    @Override
    public Person getRandomUser(Type type) {
            ArrayList<Person> peopleToChooseFrom = new ArrayList<>();
            for (int i = 0; i < c.people.size(); i++) // Iterate over all people;
                if (c.people.get(i).getType() == type)
                    peopleToChooseFrom.add(c.people.get(i)); //Add user to random list
        if (peopleToChooseFrom.isEmpty())
        {
            throw new ListEmptyException("There is no people like "+type+" in waiting list");
        }
            return peopleToChooseFrom.get(new Random().nextInt(peopleToChooseFrom.size()));

    }
    /**
     * Get a random activity in the circle
     * @param type The type of the activity
     */
    @Override
    public Activity getRandomActivity(Typology type) {
        ArrayList<Activity> activitiesToChooseFrom = new ArrayList<>();

        for (int i = 0; i < c.activities.size(); i++) // Iterate over all activities;
            if (c.activities.get(i).getType() == type)
                activitiesToChooseFrom.add(c.activities.get(i)); //Add activity to random list
        if (activitiesToChooseFrom.isEmpty())
        {
            throw new ListEmptyException("There is no activities like "+type+" in waiting list");
        }
        return activitiesToChooseFrom.get(new Random().nextInt(activitiesToChooseFrom.size()));
    }
    /**
     * Method that write the properties of the admin
     * This method is recall in the {@code LogWriter} class
     * @see it.unipr.ingegneria.utils.LogWriter
     * @return a string
     */
    @Override
    public String toString() {
        StringBuilder str
                = new StringBuilder();
        str
                .append("ID: " + this.getId())
                .append("\t")
                .append("Name: " + this.getName())
                .append("\t")
                .append("Surname: " + this.getSurname())
                .append("\t")
                .append("\n");
        return str.toString();
    }
}
