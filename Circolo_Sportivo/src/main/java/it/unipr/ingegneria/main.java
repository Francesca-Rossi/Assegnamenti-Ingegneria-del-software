package it.unipr.ingegneria;

import it.unipr.ingegneria.entities.*;
import it.unipr.ingegneria.utils.LogWriter;
import it.unipr.ingegneria.utils.Type;
import it.unipr.ingegneria.utils.Typology;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String args[]) {
        Circle circle = new Circle(); //create the cirlce
        LogWriter logWriter = LogWriter.getInstance(); //open the file
        /**
         * Principals variables
         */
        String names[] = {"Marco", "Francesca", "Milena", "Giorgio", "Riccardo", "Noemi"};
        String surnames[] = {"Rossi", "Gialli", "Verdi", "Neri", "Bianchi", "Viola"};
        String courses[] = {"Nuoto", "Pallavolo", "GAG", "Atletica", "Arti Marziali", "Basket"};
        String race[] = {"Partita Pallavolo", "Sfida Arti Marziali", "Gara Nuto", " Gara Atletica", "Gara Basket", "Sfida Judo"};
        String name = "";
        String surname = "";
        String mail;
        int tot_member = 5;
        int tot_course = 4;
        int tot_race = 3;
        List<Member> members = new ArrayList<Member>();
        List<Activity> activities = new ArrayList<Activity>();
        /**
         * Define the  admin
         */
        Admin admin1 = new Admin(circle);
        admin1
                .setName("Giuseppe")
                .setSurname("Conte")
                .setEmail("giuseppe.conte@gmail.com")
                .setType(Type.ADMIN);

        Admin admin2 = new Admin(circle);
        admin2
                .setName("Lucia")
                .setSurname("Azzolina")
                .setEmail("lucia.azzolina@gmail.com")
                .setType(Type.ADMIN);
        try {
            /**
             * Add the admin in the waiting list
             */
            circle.addPeopleWaitingList(admin1);
            circle.addPeopleWaitingList(admin2);
            /**
             * Generate a random Member and add this member in the waiting list
             */
            for (int i = 0; i <= tot_member; i++) {
                name = names[new Random().nextInt(names.length)];
                surname = surnames[new Random().nextInt(surnames.length)];
                mail = name + "." + surname + "@gmail.com";
                Member s = new Member(name, surname, mail, "", Type.MEMBER);
                circle.addPeopleWaitingList(s);
            }

            /**
             * Generate a random course and add this course in the waiting list
             */
            for (int i = 0; i <= tot_course; i++) {
                name = courses[new Random().nextInt(names.length)] + " " + (i + 1);
                Course c = new Course(name);
                circle.addActivityWaitingList(c);
            }
            /**
             * Generate a random race and add this race in the waiting list
             */
            for (int i = 0; i <= tot_race; i++) {
                name = race[new Random().nextInt(names.length)] + " " + (i + 1);
                Race r = new Race(name);
                circle.addActivityWaitingList(r);
            }
            /**
             * Get a random admin
             */
            Admin administrator = (Admin) circle.getRandomUser(Type.ADMIN);

            circle.removePeopleWaitingList(administrator);


            /**
             * Add the admin in the circle
             */
            administrator.addUser(administrator);

            /**
             * Add the member in the circle
             */
            for (int i = 0; i < tot_member; i++) {
                Member m = (Member) circle.getRandomUser(Type.MEMBER);
                members.add(m);
                administrator.addUser(m);
                circle.removePeopleWaitingList(m);

            }
            /**
             * Add the activities in the circle
             */
            for (int i = 0; i < tot_course + tot_race; i++) {
                Activity a;
                if (i % 2 == 0) {
                    a = (Course) circle.getRandomActivity(Typology.COURSE);

                } else {
                    a = (Race) circle.getRandomActivity(Typology.RACE);
                }
                activities.add(a);
                administrator.addActivity(a);
                circle.removeActivityWaitingList(a);

            }

            Member random_member = (Member) administrator.getRandomUser(Type.MEMBER);
            Activity random_course = (Activity) administrator.getRandomActivity(Typology.COURSE);
            Activity random_race = (Activity) administrator.getRandomActivity(Typology.RACE);
            /**
             * Member subscribe an unsubscribe an activities
             */

            random_member.subscribe(random_race);
            random_member.subscribe(random_course);
            random_member.unsubscribe(random_course);

            /**
             *  Member Edit
             */

            Member random_member1 = (Member) administrator.getRandomUser(Type.MEMBER);
            Member member_changed = new Member(random_member1); //Clones socio
            member_changed.setName("Achille"); //Changes name
            administrator.editUser(random_member1, member_changed); //Load new data

        } catch (Exception e) {
            logWriter.printException(e.getMessage());
        }

    }
}
