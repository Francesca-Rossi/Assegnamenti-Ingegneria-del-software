package it.unipr.ingegneria.entities;


import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.entities.exception.RequiredValueException;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.repo.WineRepository;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Toolbox;
import org.apache.log4j.Logger;


import java.util.*;


public class WineShop {

    private List<Wine> wines;
    private WineRepository wineRepo = Toolbox.wineRepo;
    private Warehouse warehouse;
    private List<User> users;
    Logger logger = Logger.getLogger(WineShop.class);

    public WineShop() {
        this.users = new ArrayList<>();
        this.warehouse = new Warehouse();
    }

    public void sellWine(Map<Params, Object> elements) throws AvailabilityException {
        try {

            this.warehouse.remove(elements);
        }
        catch (RequiredValueException e){
            logger.info(e);
        }
        catch (AvailabilityException e) {
            logger.info(e);
            throw new AvailabilityException();
        }
        catch (Exception e) { }
    }

    public void provisionWine(Map<Params, Object> elements){
        try {
            this.warehouse.add(elements);
        }
        catch (RequiredValueException e){
            logger.equals(e.getMessage());
        }
        catch (AvailabilityException e) {
            logger.equals(e.getMessage());
        }
        catch (Exception e) {
            logger.equals(e);
        }
    }


    public void addUser(User user){
        this.users.add(user);
    }

    public void deleteUser(User user){
        this.users.remove(user);
    }

    public Boolean hasUser(User item){
        return this.users.contains(item);
    }


}
