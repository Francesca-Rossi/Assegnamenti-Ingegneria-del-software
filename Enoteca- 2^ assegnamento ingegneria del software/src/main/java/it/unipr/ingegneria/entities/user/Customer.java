package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.api.IAuthentication;
import it.unipr.ingegneria.entities.api.IObserver;
import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.entities.exception.NotFoundException;
import it.unipr.ingegneria.entities.notifications.CustomerNotification;
import it.unipr.ingegneria.utils.LogMessages;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.util.*;

public class Customer extends User implements IAuthentication, IObserver {


    private static final Logger logger = Logger.getLogger(Customer.class);
    private CustomerNotification notification;
    private Boolean isAuthenticated;
    private List<Order> orders;

    public Customer(long id, String name, String surname, String email, String password, WineShop wineShop) {
        super(id, name, surname, email, password, Type.CLIENT);
        this.orders = new ArrayList<>();
        this.isAuthenticated = false;
        setWineshop(wineShop);
    }


    public void order(String name, int quantity) {

        if (isAuthenticated) {
            try {

                Map<Params, Object> elements = new HashMap<>();
                elements.put(Params.QTY, String.valueOf(quantity));
                elements.put(Params.NAME, name);
                logger.info("User " + this.getName() + " " + this.getSurname() + " asked for " + name);
                List<Wine> workedWines = this.getWineshop().sellWine(elements);

                Order order = new Order()
                        .setID(Long.valueOf(orders.size() + 1))
                        .setDate(new Date())
                        .setWine(workedWines)
                        .setDelivered(false);

                this.orders.add(order);

                logger.info(LogMessages.userCompletedOrder(this));
            } catch (AvailabilityException e) {
                logger.info(LogMessages.wineEnded(this, name));
                notification =
                        new CustomerNotification()
                                .setCustomer(this)
                                .setQuantity(quantity)
                                .setWineName(name);
                this.getWineshop().addObserver(notification);
            } catch (Exception e) {
                logger.info(e);
            }
        } else {
            logger.info(LogMessages.userNoAuth(this));
        }
    }

    @Override
    public void login(String email, String password) throws Exception {
        if (!this.getWineshop().hasUser(this))
            throw new NotFoundException();

        isAuthenticated = this.getEmail().equals(email) && this.getPassword().equals(password) && this.getWineshop().hasUser(this);
    }

    @Override
    public void logout() throws Exception {
        if (!this.getWineshop().hasUser(this))
            throw new NotFoundException();

        this.getWineshop().deleteUser(this);
        isAuthenticated = this.getWineshop().hasUser(this);
    }


    @Override
    public void update(Object o) {
        this.getWineshop().removeObserver(notification);
        logger.info(LogMessages.userNotification(this, notification));
    }

    public List<Wine> findByName(String name) {
        return this.getWineshop().findByName(name);
    }


    public List<Wine> findByYear(int d) {
        return this.getWineshop().findByYear(d);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
