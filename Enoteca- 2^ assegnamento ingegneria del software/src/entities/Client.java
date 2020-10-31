package entities;
import api.IAuthentication;
import api.IObserver;

import utils.Type;
import utils.Observer;
public class Client extends User implements IAuthentication<Boolean, String>, IObserver {


    public Client(Type type)
    {
        super(type);
    }

    public Client(long id, String name, String surname, String email, String password, Type type)
    {
        super(id, name, surname, email, password, type);
    }



    @Override
    public Boolean login(String email, String password) {
        if(this.getWineshop().users.contains(this)) {
            if (this.getEmail() == email && this.getPassword() == password) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean logout() {
        if(this.getWineshop().users.contains(this)) {
            this.getWineshop().users.remove(this);
            return true;
        }
        return false;
    }

    @Override
    public void notify(Wine wine, String message) {
        System.out.println("["+this.getUserType()+": "+ this.getName()+"] "+message);
    }
    public void buyWine(Wine wine, int quantity)
    {
        if(this.getWineshop().sellWine(this, wine, quantity)){
            System.out.println("The client "+this.getName()+" has buy "+ quantity+" bottle of "+wine.getName());
        }
        else
        {
            System.out.println("The quantity of  "+wine.getName()+" bottle that you want buy is not avaible");
            requestWine(wine, quantity);
        }
    }
    public void requestWine(Wine wine, int quantity)
    {
        this.setObserverType(Observer.WINEREQUIRED);
        wine.addObserver(this);
        wine.allarm(quantity, this);
        System.out.println("The client "+this.getName()+"want receive notify when there is almost "+quantity+" bottle of "+wine.getName());

    }
}
