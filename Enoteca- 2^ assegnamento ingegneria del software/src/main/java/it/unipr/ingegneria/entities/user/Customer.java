package it.unipr.ingegneria.entities.user;

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

import java.util.HashMap;
import java.util.Map;

public class Customer extends User implements IAuthentication, IObserver {


    private static final Logger logger = Logger.getLogger(Customer.class);
    private CustomerNotification notification;
    private Boolean isAuthenticated;


    public Customer(long id, String name, String surname, String email, String password, WineShop wineShop) {
        super(id, name, surname, email, password, Type.CLIENT);
        this.isAuthenticated = false;
        setWineshop(wineShop);
    }


    public void order(String name, int quantity) {

        if (isAuthenticated) {
            try {
                Map<Params, Object> elements = new HashMap<>();
                elements.put(Params.QTY, String.valueOf(quantity));
                elements.put(Params.NAME, name);
                this.getWineshop().sellWine(elements);
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
        }  else {
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

        StringBuilder builder = new StringBuilder()
                .append("Dear ")
                .append(this.getName())
                .append(" ")
                .append(this.getSurname())
                .append(" the wine that you searched ")
                .append(notification.getWineName())
                .append(" is now is available");
        this.getWineshop().removeObserver(notification);
        logger.info(builder.toString());
    }
}
