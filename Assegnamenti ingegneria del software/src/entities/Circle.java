package entities;

import java.util.ArrayList;
import java.util.List;

public class Circle {
    public List<Activity> activities;
    public List<Person> peoples;

    public Circle(){
        this.activities = new ArrayList<>();
        this.peoples = new ArrayList<>();
    }

}
