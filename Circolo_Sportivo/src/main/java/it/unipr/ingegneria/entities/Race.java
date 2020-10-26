package it.unipr.ingegneria.entities;



import java.util.NoSuchElementException;
import it.unipr.ingegneria.api.IOperations;
import it.unipr.ingegneria.exception.ElementAlreadyPresentException;
import it.unipr.ingegneria.exception.ListEmptyException;
import it.unipr.ingegneria.utils.Typology;
/**
 *
 *  The {@code Race} class define the behavior of the race and extends the Activity class.
 *
 * @author Ruslan, Francesca, Nick
 * @see Activity
 * @see it.unipr.ingegneria.api.IOperations
 */
public class Race extends Activity implements IOperations<Person>
{
    /**
     * Class costuctor that implements the activity costructor
     * @param name
     */
    public Race(String name){
        super(name, Typology.RACE);
    }

    /**
     * Class constructor that allows cloning
     * @param race
     */
    public Race(Race race) { super(race); }

    /**
     * Subscribe the person in the activity
     * @param person
     */

    @Override
    public void subscribe(Person person) {

        if (this.people.contains(person)) {
            throw new ElementAlreadyPresentException("The value "+ people.toString()+" is already in the list.");
        }
        else {
            this.people.add(person);
        }
    }

    /**
     * Unsubscribe the person in the activity
     * @param person
     */
    @Override
    public void unsubscribe(Person person) {

        if (this.people.isEmpty())
        {
            throw new ListEmptyException("You can't remove a "+person.toString()+" because there is not  subscribe in this Activity");
        }
        else if (this.people.contains(person)!=true) {
            throw new NoSuchElementException("The element "+person.toString()+"is not present in the list.");
        }
        else {
            this.people.remove(person);
        }
    }
    /**
     * Method that write the properties of the Race
     * This method is recall in the {@code LogWriter} class
     * @see it.unipr.ingegneria.utils.LogWriter
     * @return a string
     */
    @Override
    public String toString()
    {
        return this.getType()+": ID: "+this.getId()+" Name: "+this.getName()+"\n";
    }
}
