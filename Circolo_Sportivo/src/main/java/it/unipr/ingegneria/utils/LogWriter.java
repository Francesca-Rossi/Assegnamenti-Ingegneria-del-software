package it.unipr.ingegneria.utils;

import it.unipr.ingegneria.entities.Activity;
import it.unipr.ingegneria.entities.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Singleton class that use to write in a file
 * @author Ruslan, Nick, Francesca
 */
public class LogWriter {
    /**
     * The file name
     */
    private static final String LOG_FILE = "output.txt";

    private PrintWriter writer;

    private static LogWriter logger = null;

    /**
     * The costructor
     */
    private LogWriter() {
        try {
            FileWriter fw = new FileWriter(LOG_FILE);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method that retun the istance of the class
     * @return
     */
    public static synchronized LogWriter getInstance() {
        if (logger == null)
            logger = new LogWriter();
        return logger;
    }

    /**
     * Method call when a person subscribe in an activity
     * @param person
     * @param activity
     */
    public void subscribe(Person person, Activity activity) {
        try {
            String role = person.getType().name();
            StringBuilder str
                    = new StringBuilder();
            str
                    .append(person.toString())

                    .append("with Role: \t" + role + "\n")
                    .append("has subscribed to: \t")
                    .append(activity.toString())
                    .append("\n");
            writer.println(str.toString());
        }
        catch(Exception e) {
            this.printException(e.getMessage());
        }

    }

    /**
     * Method call when a person unsubscribe  an activity
     * @param person
     * @param activity
     */
    public void unsubscribe(Person person, Activity activity) {
        try {
            String role = person.getType().name();
            StringBuilder str
                    = new StringBuilder();
            str
                    .append(person.toString())
                    .append("with Role: \t" + role + "\n")
                    .append("has unsubscribed from: \t")
                    .append(activity.toString())
                    .append("\n");
            writer.println(str.toString());
        }
        catch(Exception e)
        {
            this.printException(e.getMessage());
        }
    }

    /**
     * Method call when the ADMIN add a Person in the circle
     * @param person
     */
    public void addUser(Person... person) {
        try {
            String role0 = person[0].getType().name();
            String role1 = person[1].getType().name();

            StringBuilder str
                    = new StringBuilder();
            str
                    .append(person[0].toString())
                    .append("with Role: \t" + role0 + "\n")
                    .append("has added user: \t")
                    .append(person[1].toString())
                    .append("with Role: \t" + role1 + "\n")
                    .append("\n");
            writer.println(str.toString());
        }
        catch(Exception e)
        {
            this.printException(e.getMessage());
        }
    }

    /**
     * Method call when the ADMIN remove a Person from the circle
     * @param person
     */
    public void deleteUser(Person... person) {
        try {
            String role0 = person[0].getType().name();
            String role1 = person[1].getType().name();

            StringBuilder str
                    = new StringBuilder();
            str
                    .append(person[0].toString())
                    .append("with Role: \t" + role0 + "\n")
                    .append("has deleted user: \t")
                    .append(person[1].toString())
                    .append("with Role: \t" + role1 + "\n")
                    .append("\n");
            writer.println(str.toString());
        }
        catch(Exception e)
        {
            this.printException(e.toString());
        }
    }

    /**
     * Method call when the ADMIN modify a person
     * @param person
     */
    public void editUser(Person... person) {
        try {

            String role0 = person[0].getType().name();
            String role1 = person[1].getType().name();

            StringBuilder str
                    = new StringBuilder();
            str
                    .append(person[0].toString())
                    .append("with Role: \t" + role0 + "\n")
                    .append("has edited user information of: \t")
                    .append(person[1].toString())
                    .append("to: \t")
                    .append(person[2].toString())
                    .append("\n");

            writer.println(str.toString());
        }
        catch (Exception e)
        {
            this.printException(e.toString());
        }
    }

    /**
     * Method call when the ADMIN add an activity in the circle
     * @param person
     * @param activity
     */
    public void addActivity(Person person, Activity activity) {
        try {
            String role = person.getType().name();
            StringBuilder str
                    = new StringBuilder();
            str
                    .append(person.toString())
                    .append("with Role: \t" + role + "\n")
                    .append("has created: \t")
                    .append(activity.toString())
                    .append("\n");
            writer.println(str.toString());
        }
        catch(Exception e)
        {
            this.printException(e.toString());
        }
    }

    /**
     * Method call when the ADMIN remove an activity from the circle
     * @param person
     * @param activity
     */
    public void deleteActivity(Person person, Activity activity) {
        try {
            String role = person.getType().name();
            StringBuilder str
                    = new StringBuilder();
            str
                    .append(person.toString())
                    .append("with Role: \t" + role + "\n")
                    .append("has delated: \t")
                    .append(activity.toString())
                    .append("\n");
            writer.println(str.toString());
        }
        catch(Exception e)
        {
            this.printException(e.getMessage());
        }
    }

    /**
     * Method call when the ADMIN modify an activity
     * @param person
     * @param prev_activity
     * @param new_activity
     */
    public void editActivity(Person person, Activity prev_activity, Activity new_activity)
    {
        try {
            String role = person.getType().name();
            StringBuilder str
                    = new StringBuilder();
            str
                    .append(person.toString())
                    .append("with Role: \t" + role + "\n")
                    .append("has edited activity information of: \t")
                    .append(prev_activity.toString())
                    .append("to: \t")
                    .append(new_activity.toString())
                    .append("\n");
            writer.println(str.toString());
        }
        catch(Exception e)
        {
            this.printException(e.getMessage());
        }
    }

    /**
     * Method call when the exception is generate
     * @param exception
     */
    public void printException(String exception)
    {
        StringBuilder str
                = new StringBuilder();
        str
                .append("[Exception] " + exception)
                .append("\n");
        writer.println(str.toString());
    }

}
