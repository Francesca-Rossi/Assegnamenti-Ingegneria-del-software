package it.unipr.ingegneria.entities;



import java.util.NoSuchElementException;

import it.unipr.ingegneria.api.IOperations;
import it.unipr.ingegneria.exception.ElementAlreadyPresentException;
import it.unipr.ingegneria.exception.ListEmptyException;
import it.unipr.ingegneria.utils.LogWriter;
import it.unipr.ingegneria.utils.Type;


/**
 *
 *  The {@code Member} class defines the behavior of the circle member person.
 *  Extends the abstract class {@code Person} and implement {@code IOperations<C>} interface.
 *
 * @author Ruslan, Francesca, Nick
 * @see Person
 * @see it.unipr.ingegneria.api.IOperations
 */
public class Member extends Person implements IOperations<Activity> {
    /**
     * The log file
     */
    private LogWriter logWriter = LogWriter.getInstance();

    /**
     * Default class costructor that implement the father class defalut costructor
     */
    public Member() {
        super();
    }

    public Member(String name, String surname, String email, String password, Type type) {
        super(name, surname, email, type, password);
    }

    /**
     * Class constructor that allows cloning
     * @param member
     */
    public Member(Member member){ super(member); }

    /**
     * Subscribe the member in a particular activity
     * @param activity  the activity
     */
    @Override
    public void subscribe(Activity activity) {
        if (this.activities.contains(activity)) {
            throw new ElementAlreadyPresentException("The value "+ activity.toString()+" is already in the list.");
        }
        else {
            this.activities.add(activity);
            activity.subscribe(this);
            logWriter.subscribe(this, activity);
        }

    }
    /**
     * unsubscribe the member in a particular activity
     * @param activity  the activity
     */
    @Override
    public void unsubscribe(Activity activity) {
        if (this.activities.isEmpty())
        {
            throw new ListEmptyException("You can't remove an Activity because "+this.toString()+" are not subscribe any Activity");
        }
        else if (this.activities.contains(activity)!=true) {
            throw new NoSuchElementException("The element "+activity.toString()+"is not present in the list.");
        }
        else {
            logWriter.unsubscribe(this, activity);
            this.activities.remove(activity);
            activity.unsubscribe(this);
        }
    }

    /**
     * Method that write the properties of the member
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
