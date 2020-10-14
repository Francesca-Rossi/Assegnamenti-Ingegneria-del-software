import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Socio extends Persona implements IOperations<Activity> {



    public Socio() {
        super();
    }

    @Override
    public void subscribe(Activity activity) {
        this.activities.add(activity);
    }

    @Override
    public void delete(Activity activity) {
        this.activities.remove(activity);
    }

    @Override
    public String toString() {
        Iterator<Activity> it = activities.iterator();
        System.out.println("Numero Attività:" + this.activities.size());
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        return super.toString();
    }
}
