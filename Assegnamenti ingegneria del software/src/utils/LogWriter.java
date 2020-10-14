package utils;

import entities.Activity;
import entities.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter {
    private static final String LOG_FILE = "output.txt";
    private PrintWriter writer;

    private static LogWriter logger = null;

    private LogWriter() {
        try {
            FileWriter fw = new FileWriter(LOG_FILE);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {
        }
    }

    public static synchronized LogWriter getInstance() {
        if (logger == null)
            logger = new LogWriter();
        return logger;
    }

    public void subscribe(Person person, Activity activity) {
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

    public void unsubscribe(Person person, Activity activity) {
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


    public void addUser(Person... person) {

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


    public void deleteUser(Person... person) {

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

    public void addActivity(Person person, Activity activity) {

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

}
