package entities;

import api.IOperationsPrevileged;
import utils.LogWriter;
import utils.Type;

public class Admin extends Person implements IOperationsPrevileged<Person, Activity> {
    private Circle c;
    private LogWriter logWriter = LogWriter.getInstance();

    public Admin(Circle c) {
        super(Type.ADMIN);
        this.c = c;
    }


    @Override
    public void subscribe(Activity activity) {
        logWriter.subscribe(this, activity);
        this.activities.add(activity);
    }

    @Override
    public void unscribe(Activity activity) {
        logWriter.unsubscribe(this, activity);
        this.activities.remove(activity);
    }


    @Override
    public void addUser(Person person) {
        logWriter.addUser(this,person);
        c.peoples.add(person);
    }

    @Override
    public void deleteUser(Person person) {
        logWriter.deleteUser(this,person);
        c.peoples.remove(person);
    }

    @Override
    public void addActivity(Activity activity) {
        logWriter.addActivity(this,activity);
        c.activities.add(activity);
    }

    @Override
    public void deleteActivity(Activity activity) {
        c.activities.remove(activity);
    }

    @Override
    public String toString() {
        StringBuilder str
                = new StringBuilder();
        str
                .append("Name: " + this.getName())
                .append("\t")
                .append("Surname: " + this.getSurname())
                .append("\t")
                .append("\n");
        return str.toString();
    }
}
