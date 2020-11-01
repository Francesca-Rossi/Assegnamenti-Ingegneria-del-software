package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.entities.api.IObservable;
import it.unipr.ingegneria.entities.api.IObserver;
import it.unipr.ingegneria.entities.api.StoreManager;
import it.unipr.ingegneria.entities.api.UserManager;
import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.entities.exception.RequiredValueException;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.repo.WineRepository;
import it.unipr.ingegneria.utils.Params;
import org.apache.log4j.Logger;


import java.util.*;
import java.util.stream.Collectors;


public class WineShop implements
        UserManager, StoreManager, IObservable<CustomerNotification>, IObserver {

    private WineRepository wineRepo = WineRepository.getInstance();
    Logger logger = Logger.getLogger(WineShop.class);

    private Warehouse warehouse;
    private List<User> users;
    private List<CustomerNotification> observers = new ArrayList<>();

    public WineShop() {
        this.users = new ArrayList<>();
        this.warehouse = new Warehouse();
        warehouse.addObserver(this);
    }


    @Override
    public void sellWine(Map<Params, Object> elements) throws AvailabilityException {
        try {

            this.warehouse.remove(elements);
        } catch (RequiredValueException e) {
            logger.info(e);
        } catch (AvailabilityException e) {
            throw new AvailabilityException();
        } catch (Exception e) {
        }
    }


    @Override
    public void provisionWine(Map<Params, Object> elements) {
        try {
            this.warehouse.add(elements);
        } catch (RequiredValueException e) {
            logger.equals(e.getMessage());
        } catch (Exception e) {
            logger.equals(e);
        }
    }


    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    @Override
    public Boolean hasUser(User item) {
        return this.users.contains(item);
    }



    @Override
    public void update(Object o) {
        List<Wine> winesAvailable = this.warehouse.getAvailableWines();

        for (CustomerNotification observer : this.observers) {
            String wineName = observer.getWineName();
            int sizes = winesAvailable.stream()
                    .filter(i -> i.getName().equals(wineName))
                    .collect(Collectors.toList()).size();

            Customer customer = observer.getCustomer();
            if (sizes >= 1)
                customer.update("");
        }
    }

    @Override
    public void addObserver(CustomerNotification user) {
        this.observers.add(user);
    }

    @Override
    public void removeObserver(CustomerNotification user) {
        this.observers.remove(user);
    }
}
