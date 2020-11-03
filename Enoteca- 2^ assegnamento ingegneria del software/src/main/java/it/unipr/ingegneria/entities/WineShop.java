package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.entities.api.IObservable;
import it.unipr.ingegneria.entities.api.IObserver;
import it.unipr.ingegneria.entities.api.IStoreManager;
import it.unipr.ingegneria.entities.api.IUserManager;
import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.entities.exception.RequiredValueException;
import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;


public class WineShop implements
        IUserManager, IStoreManager<Wine>, IObservable<CustomerNotification>, IObserver {

    Logger logger = Logger.getLogger(WineShop.class);

    private Warehouse warehouse;
    private List<User> users;
    private ProvisioningManager provisioningManager;
    private List<CustomerNotification> observers = new ArrayList<>();

    public WineShop() {
        this.users = new ArrayList<>();
        this.provisioningManager = new ProvisioningManager();
        this.warehouse = new Warehouse();
        warehouse.addObserver(this);
    }


    @Override
    public List<Wine> sellWine(Map<Params, Object> elements) throws AvailabilityException {
        List<Wine> workedWines = new ArrayList<>();
        try {
            workedWines = this.warehouse.remove(elements);
        } catch (RequiredValueException e) {
            logger.info(e);
        } catch (AvailabilityException e) {
            throw new AvailabilityException();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return workedWines;
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
    public void sendOrders() {
        users.stream()
                .filter(u -> u.getUserType().equals(Type.CLIENT))
                .map(user -> ((Customer) user))
                .map(customer -> customer.getOrders())
                .filter(orders -> !orders.isEmpty())
                .forEach((x) -> x.stream().forEach((i) -> i.setDelivered(true)));
    }

    @Override
    public List<Wine> findByName(String name) {
        return this.warehouse.findByName(name);
    }

    @Override
    public List<Wine> findByYear(int d) {
        return this.warehouse.findByYear(d);
    }


    @Override
    public void addUser(User user) {
        if (user.getUserType().equals(Type.EMPLOYEE))
            this.provisioningManager.addObserver((Employee) user);
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
            if (sizes >= observer.getQuantity())
                customer.update("");
        }
    }

    @Override
    public void addObserver(CustomerNotification user) {
        this.observers.add(user);
        // ToDO: If you want generate random provisiong do it here
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, user.getWineName());
        elements.put(Params.QTY, String.valueOf(user.getQuantity()));
        this.provisioningManager.handleProvisioning(elements);
    }

    @Override
    public void removeObserver(CustomerNotification user) {
        this.observers.remove(user);
    }
}
