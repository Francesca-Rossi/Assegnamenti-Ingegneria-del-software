package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.api.IObserver;
import it.unipr.ingegneria.api.IStoreManager;
import it.unipr.ingegneria.utils.LogMessages;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Employee extends User implements IObserver {

    private IStoreManager wineShop;
    private Boolean isWorking;
    private static final Logger logger = Logger.getLogger(Employee.class);

    public Employee(long id, String name, String surname, String email, String password, IStoreManager wineShop) {
        super(id, name, surname, email, password, Type.EMPLOYEE);
        this.wineShop = wineShop;

    }

    public void provisionWine(String name, int quantity) {
        logger.info(LogMessages.employeeProvisiongWine(this, name));
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, name);
        elements.put(Params.QTY, String.valueOf(quantity));
        elements.put(Params.DATE, new Date());
        this.wineShop.provisionWine(elements);
        this.isWorking = false;
    }

    public Boolean getWorking() {
        return isWorking;
    }


    public Employee setWorking(Boolean working) {
        isWorking = working;
        return this;
    }

    public void sendOrders() {
        wineShop.sendOrders();
    }

    @Override
    public void update(Object o) {
        Map<Params, Object> elements = (Map<Params, Object>) o;
        String name = (String) elements.get(Params.NAME);
        Integer number = Integer.parseInt((String) elements.get(Params.QTY));
        provisionWine(name, number);
    }
}


