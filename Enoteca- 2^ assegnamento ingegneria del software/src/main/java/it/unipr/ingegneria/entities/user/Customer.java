package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.api.IAuthentication;
import it.unipr.ingegneria.entities.api.IObserver;
import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.entities.exception.NotFoundException;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User implements IAuthentication, IObserver {

    private static final Logger logger = Logger.getLogger(Customer.class);
    private CustomerInfo notification;

    public Customer(long id, String name, String surname, String email, String password, WineShop wineShop) {
        super(id, name, surname, email, password, Type.CLIENT);
        setWineshop(wineShop);
    }


    public void order(String name, int quantity) {
        StringBuilder builder = null;
        if (isAuthenticated) {
            try {
                Map<Params, Object> elements = new HashMap<>();
                elements.put(Params.QTY, String.valueOf(quantity));
                elements.put(Params.NAME, name);
                this.wineshop.sellWine(elements);
            } catch (AvailabilityException e) {
                 builder = new StringBuilder()
                        .append("Dear ")
                        .append(this.getName())
                        .append(" ")
                        .append(this.getSurname())
                        .append(" the wine that you searched ")
                        .append(name)
                        .append(" at the moment is not available");
                logger.info(builder.toString());
                notification =
                        new CustomerInfo()
                                .setCustomer(this)
                                .setWineName(name);
                this.wineshop.addObserver(notification);
            } catch (Exception e) {
                logger.info(e);
            }
        }  else {
            builder = new StringBuilder()
                    .append("User ")
                    .append(this.getName())
                    .append(" ")
                    .append(this.getSurname())
                    .append(" not authenticated");
            logger.info(builder.toString());
        }
    }

    @Override
    public void login(String email, String password) throws Exception {
        if (!this.wineshop.hasUser(this))
            throw new NotFoundException();

        isAuthenticated = this.getEmail().equals(email) && this.getPassword().equals(password) && this.wineshop.hasUser(this);
    }

    @Override
    public void logout() throws Exception {
        if (!this.wineshop.hasUser(this))
            throw new NotFoundException();

        this.wineshop.deleteUser(this);
        isAuthenticated = this.wineshop.hasUser(this);
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
        // this.wineshop.removeObserver(notification);
        logger.info(builder.toString());
    }
}
