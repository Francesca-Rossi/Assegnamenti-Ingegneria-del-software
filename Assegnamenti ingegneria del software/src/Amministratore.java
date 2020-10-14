public class Amministratore extends Persona implements IOperationsPrevileged<Persona, Activity> {
    private Circolo c;

    public Amministratore(Circolo c) {
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
    public void addUser(Persona persona) {
        c.peoples.add(persona);
    }

    @Override
    public void deleteUser(Persona persona) {
        c.peoples.remove(persona);
    }


}
