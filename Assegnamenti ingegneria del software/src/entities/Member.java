package entities;

import api.IOperations;
import utils.LogWriter;

public class Member extends Person implements IOperations<Activity> {
    private LogWriter logWriter = LogWriter.getInstance();

    public Member() {
        super();
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
