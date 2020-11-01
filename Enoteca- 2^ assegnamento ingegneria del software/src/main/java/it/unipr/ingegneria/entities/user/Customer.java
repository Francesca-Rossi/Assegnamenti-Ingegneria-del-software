package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.api.IAuthentication;
import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.entities.exception.NotFoundException;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User implements IAuthentication {

    private static final Logger logger = Logger.getLogger(Customer.class);

    public Customer(long id, String name, String surname, String email, String password, WineShop wineShop) {
        super(id, name, surname, email, password, Type.CLIENT);
        setWineshop(wineShop);
    }


    public void order(String name, int quantity) {
        if (isAuthenticated) {
            try {
                Map<Params, Object> elements = new HashMap<>();
                elements.put(Params.QTY, String.valueOf(quantity));
                elements.put(Params.NAME, name);
                this.wineshop.sellWine(elements);
            } catch (AvailabilityException e) {
                logger.info(e);
            } catch (Exception e){
                logger.info(e);
            }
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
    public void notify(Wine t, String message) {
        logger.info("[" + this.getUserType() + ": " + this.getName() + "] " + message);

    }
}
