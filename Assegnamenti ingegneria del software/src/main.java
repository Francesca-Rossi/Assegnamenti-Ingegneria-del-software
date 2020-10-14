import entities.*;
import utils.Type;

public class main {
    public static void main(String args[]) {
        Circle circle = new Circle();

        Member socio = new Member();
        socio
                .setName("Ruslan")
                .setSurname("Vasyunin")
                .setEmail("")
                .setType(Type.SOCIO);

        Activity gara = new Race();
        Race race = new Race();

        socio.subscribe(gara);
        socio.unscribe(gara);

        Admin admin = new Admin(circle);
        admin.addUser(socio);
        admin.deleteUser(socio);
        admin.addActivity(race);


        admin
                .setName("")
                .setSurname("")
                .setEmail("")
                .setType(Type.ADMIN);
    }
}
