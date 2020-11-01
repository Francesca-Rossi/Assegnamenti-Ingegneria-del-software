package it.unipr.ingegneria;

import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.repo.WineRepository;
import it.unipr.ingegneria.utils.Toolbox;
import org.apache.log4j.Logger;


public class main {
    public static void main(String... args) throws Exception {
        Toolbox toolbox = new Toolbox();

        Logger logger = Logger.getLogger(main.class);

        WineShop wineShop = new WineShop();
        Employee emp = new Employee(1L, "Ruslan", "Vasyunin", "email", "pwd", wineShop);
        emp.provisionWine("Lambrusco", 10);
        emp.provisionWine("Malvasia", 20);
        emp.provisionWine("Chianti", 30);

        wineShop.addUser(emp);

        Customer customer = new Customer(1L, "Ruslan", "Vasyunin", "email", "pwd", wineShop);
        wineShop.addUser(customer);
        customer.login("email", "pwd");
        customer.order("Lambrusco", 12);


        WineRepository wineRepo = Toolbox.wineRepo;
        logger.info("||" + wineRepo.getItems().size());




    }
}
