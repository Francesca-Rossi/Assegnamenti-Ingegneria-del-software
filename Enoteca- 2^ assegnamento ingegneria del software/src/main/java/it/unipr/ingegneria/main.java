package it.unipr.ingegneria;

import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.repo.WineRepository;

import org.apache.log4j.Logger;


public class main {
    public static void main(String... args) throws Exception {
        WineRepository wineRepo = WineRepository.getInstance();

        Logger logger = Logger.getLogger(main.class);

        WineShop wineShop = new WineShop();

        Customer customer = new Customer(1L, "Ruslan", "Vasyunin", "email", "pwd", wineShop);
        wineShop.addUser(customer);
        customer.login("email", "pwd");
        customer.order("Lambrusco", 2);

        Customer customer1 = new Customer(2L, "Anna", "Russo", "email", "pwd", wineShop);
        wineShop.addUser(customer1);
        customer1.login("email", "pwd");
        customer1.order("Lambrusco", 1);


        Employee emp = new Employee(1L, "Ruslan", "Vasyunin", "email", "pwd", wineShop);
        emp.provisionWine("Lambrusco", 10);

        logger.info("||" + wineRepo.getItems().size());




    }
}
