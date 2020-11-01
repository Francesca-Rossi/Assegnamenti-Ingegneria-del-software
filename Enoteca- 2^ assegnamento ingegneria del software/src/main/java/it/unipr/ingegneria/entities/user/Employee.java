package it.unipr.ingegneria.entities.user;

import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.utils.Type;

import java.util.HashMap;
import java.util.Map;

public class Employee extends User {

    public Employee(long id, String name, String surname, String email, String password, WineShop wineShop)
    {
        super(id, name, surname, email, password, Type.EMPLOYEE);
        setWineshop(wineShop);
    }

    public void provisionWine(String name, int quantity){
        Map<Params, Object> elements = new HashMap<>();
        elements.put(Params.NAME, name);
        elements.put(Params.QTY, String.valueOf(quantity));
        this.wineshop.provisionWine(elements);

    }
}


