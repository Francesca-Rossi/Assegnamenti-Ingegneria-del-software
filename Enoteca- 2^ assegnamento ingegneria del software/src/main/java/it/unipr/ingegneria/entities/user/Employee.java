package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.entities.api.IObserver;
import it.unipr.ingegneria.entities.api.IStoreManager;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;

import java.util.HashMap;
import java.util.Map;

public class Employee extends User implements IObserver {

    private IStoreManager wineShop;

    public Employee(long id, String name, String surname, String email, String password, IStoreManager wineShop)
    {
        super(id, name, surname, email, password, Type.EMPLOYEE);
        this.wineShop = wineShop;
    }

    public void provisionWine(String name, int quantity){
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, name);
        elements.put(Params.QTY, String.valueOf(quantity));
        this.wineShop.provisionWine(elements);
    }


    @Override
    public void update(Object o) {

    }
}


