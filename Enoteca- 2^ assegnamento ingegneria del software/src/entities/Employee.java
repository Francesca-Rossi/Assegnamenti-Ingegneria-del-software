package entities;

import api.IAuthentication;
import api.IObserver;
import utils.Observer;
import utils.Type;

import java.util.ArrayList;
import java.util.List;

public class Employee  extends User implements  IObserver {
    private List<Wine> finish_wine;


    public Employee(Type type, WineShop wineShop)
    {
        super(type);
        this.finish_wine=new ArrayList<>();
        this.setWineshop(wineShop);
        this.setObserverType(Observer.EMPLOYEE);
    }

    public Employee(long id, String name, String surname, String email, String password, Type type, WineShop wineShop)
    {
        super(id, name, surname, email, password, type);
        this.finish_wine=new ArrayList<>();
        this.setWineshop(wineShop);
        this.setObserverType(Observer.EMPLOYEE);
    }


    public void sendWine()
    {
        for(Order orders:getWineshop().orders){
            if(!orders.isDelivered())
            {
                orders.setDelivered(true); //ho spedito gli ordini
            }
        }
        System.out.println("All the order are send by "+this.getName());

    }
    public void registerClient(Client user){
        this.getWineshop().users.add(user);
        user.setWineshop(getWineshop());
        System.out.println("The client "+user.getName()+" is registered in the wineshop");
    }
    public void buyWine(Wine wine, int quantity)
    {
        //per comprare un nuovo vino o riacquistarne uno finito
        if(!this.getWineshop().wines.contains(wine)) {
            this.getWineshop().registerWine(wine);
            wine.addObserver(this);
            wine.setQuantity(quantity);
            System.out.println("The Employee "+this.getName()+"buy the "+wine.getName());
            if (this.finish_wine.contains(wine)) {
                //ho riacquistato un vecchio vino
                wine.avaible(quantity);
                this.finish_wine.remove(wine);
            }



        }

    }

    public boolean fillWine(Wine wine, int quantity)
    {
        //per rimpiazzare gli scaffali di un vino già esistete e non finito
        if (this.getWineshop().wines.contains(wine)) {
                if(wine.getQuantity()!=0) {//se ho ancora delle bottiglie, vado solo a riempire
                wine.setQuantity(wine.getQuantity()+quantity);
                System.out.println("The Employee "+this.getName()+"fill the stock of "+wine.getName());
                wine.avaible(quantity);
                return true;
                }
                else {
                    //se il vino è finito, lo vado a ricomprare
                   this.buyWine(wine, quantity);
                }
        }
        return false;
    }



    @Override
    public void notify(Wine wine, String message) {
        if(wine.getQuantity()==0)
            finish_wine.add(wine);
        System.out.println("["+this.getUserType()+": "+ this.getName()+"] "+message);


    }



}
