package it.unipr.ingegneria;


import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import java.util.List;

/**
 * Is the main class of the system
 * @author Ruslan Vaslin, Nick, Francesca Rossi
 */
public class main {
    public static void main(String... args) throws Exception {

        /**
         * Create a wineshop
         */
        WineShop wineShop = new WineShop();

        Employee emp = new Employee(1L, "Luca", "Bianchi", "email", "pwd", wineShop);
        emp.provisionWine("Chianti", 15);
        emp.provisionWine("Lambrusco", 15);
        wineShop.addUser(emp);

        Customer customer = new Customer(1L, "Ciccio", "Pasticcio", "Ciccio.past@gmail.com", "pwd", wineShop);
        wineShop.addUser(customer);//l'utente si registra
        List<Wine> l = customer.findByName("Chianti");
        List<Wine> a = customer.findByYear(2020);

        customer.login("Ciccio.past@gmail.com", "pwd"); //l'utente accede
        customer.order("Chianti", 14); //l'utente acquista un tot di vino


        Customer customer1 = new Customer(2L, "Mario", "Rossi", "mariorossi@gmail.com", "pwd1", wineShop);
        wineShop.addUser(customer1);
        customer1.login("mariorossi@gmail.com", "pwd1");
        customer1.order("Lambrusco", 15);

        Customer customer2 = new Customer(3L, "Giorgio", "Verdi", "giorgio.verdi@gmail.com", "pwd2", wineShop);
        wineShop.addUser(customer1);
        customer1.login("giorgio.verdi@gmail.com", "pwd2");
        customer1.order("Lambrusco", 2);

        emp.sendOrders();

    }
}
