package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.api.IRandom;
import it.unipr.ingegneria.exception.ElementAlreadyPresentException;
import it.unipr.ingegneria.exception.ListEmptyException;
import it.unipr.ingegneria.utils.Type;
import it.unipr.ingegneria.utils.Typology;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * The {@code Circle} class define the component of the circle.
 *
 * @author Ruslan, Francesca, Nick
 */
public  class Circle implements IRandom<Person, Activity> {
    /**
     * The activities present in the circle
     */
    public List<Activity> activities;
    /**
     * The people present in the circle
     */
    public List<Person> people;
    /**
     * The activities that is in waiting to enter in the circle
     */
    public List<Activity> activities_waiting_list;
    /**
     * The people that is in  waiting to enter in the circle
     */
    public List<Person> people_waiting_list;

    /**
     * Class costructor
     */
    public Circle(){
        this.activities = new ArrayList<>();
        this.people = new ArrayList<>();
        this.people_waiting_list = new ArrayList<>();
        this.activities_waiting_list = new ArrayList<>();

    }

    /**
     * Method to add an activity in the waiting list
     * @param activity
     */
    public void addActivityWaitingList(Activity activity){
        if (this.activities_waiting_list.contains(activity)) {
            throw new ElementAlreadyPresentException("The "+ activity.toString()+" is already in the  waiting list.");
        }
        else
        {
            this.activities_waiting_list.add(activity);
        }
    }

    /**
     * Method to add a person in the waiting list
     * @param person
     */
    public void addPeopleWaitingList(Person person){
        if (this.people_waiting_list.contains(person)) {
            throw new ElementAlreadyPresentException("The "+ person.toString()+" is already in the  waiting list.");
        }
        else {
            this.people_waiting_list.add(person);
        }
    }

    /**
     * Method to remove an activity from the waiting list
     * @param activity
     */
    public void removeActivityWaitingList(Activity activity){
        if (this.activities_waiting_list.isEmpty())
        {
            throw new ListEmptyException("You can't remove a "+activity.toString()+" because there is not  in the waiting list");
        }
        else if (this.activities_waiting_list.contains(activity)!=true) {
            throw new NoSuchElementException("The element "+activity.toString()+"is not present in the list.");
        }
        else {
            this.activities_waiting_list.remove(activity);
        }
    }

    /**
     * Method to remove a person from the waiting list
     * @param person
     */
    public void removePeopleWaitingList(Person person){
        if (this.people_waiting_list.isEmpty())
        {
            throw new ListEmptyException("You can't remove a "+person.toString()+" because there is not in the waiting list");
        }
        else if (this.people_waiting_list.contains(person)!=true) {
            throw new NoSuchElementException("The element "+person.toString()+"is not present in the list.");
        }
        else
        {
        this.people_waiting_list.remove(person);
        }
    }

    /**
     * Get a random Person in the waiting list of the circle
     * @param type
     */
    @Override
    public Person getRandomUser(Type type) {
        ArrayList<Person> peopleToChooseFrom = new ArrayList<>();
        for (int i = 0; i < this.people_waiting_list.size(); i++) // Iterate over all people;
            if (this.people_waiting_list.get(i).getType() == type)
                peopleToChooseFrom.add(this.people_waiting_list.get(i)); //Add user to random list
        if (peopleToChooseFrom.isEmpty())
        {
            throw new ListEmptyException("There is no people like "+type+" in waiting list");
        }
        return peopleToChooseFrom.get(new Random().nextInt(peopleToChooseFrom.size()));
    }
    /**
     * Get a random Activity in the waiting list of the circle
     * @param type
     */
    @Override
    public Activity getRandomActivity(Typology type) {
        ArrayList<Activity> activitiesToChooseFrom = new ArrayList<>();

        for (int i = 0; i < this.activities_waiting_list.size(); i++) // Iterate over all activities;
            if (this.activities_waiting_list.get(i).getType() == type)
                activitiesToChooseFrom.add(this.activities_waiting_list.get(i)); //Add activity to random list
        if (activitiesToChooseFrom.isEmpty())
        {
            throw new ListEmptyException("There is no activities like "+type+" in waiting list");
        }
        return activitiesToChooseFrom.get(new Random().nextInt(activitiesToChooseFrom.size()));
    }

}
