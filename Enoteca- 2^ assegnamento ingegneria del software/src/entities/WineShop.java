package entities;

import utils.Observer;
import utils.Type;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WineShop {

    public List<Wine> wines;
    public List<User> users;
    public List<Order> orders;

    public  WineShop()
    {
        wines =new ArrayList<>();
        users=new ArrayList<>();
        orders=new ArrayList<>();
    }
    public void registerWine(Wine wine)
    {
        this.wines.add(wine);
    }
    public void registerEmployee(Employee user){
        users.add(user);
        for(Wine wine: this.wines) {
            System.out.println(wine.getName());
            wine.addObserver(user);
        }
        System.out.println("The employee "+user.getName()+" is registered in the wineshop");


    }


    public Wine getWineByName(String name){
        for(Wine wine:wines){
            if(wine.getName()==name)
                return wine;
        }
        return null;
    }

    public List<Wine> getWineByYear(int year)
    {
        List<Wine> wines_year=new ArrayList<>();
        for(Wine wine:wines){
            if(wine.getYear()==year)
                wines_year.add(wine);
        }
        return wines_year;
    }
    public int getLastOrderId(){
         return orders.size();
    }
    public void decreaseStocks(Wine wine, int quantity)
    {
        wine.setQuantity(wine.getQuantity()-quantity);
    }
    public boolean sellWine(Client client, Wine wine, int requiredQuantity){
        if(client.login(client.getEmail(), client.getPassword())) { //controllo utente sia registrato nel sistema
            if(this.wines.contains(wine)) {
                if (wine.getQuantity() == 0) { //controllo scorte non siano finite

                    client.setObserverType(Observer.WINEAVAIBLE);
                    wine.addObserver(client);
                    wine.finish();
                    wines.remove(wine); //tolgo la disponibilità del vino nel negozio
                    return false;
                } else if (wine.getQuantity() < requiredQuantity) { //controllo scorte non siano inferiori alla richiesta
                    client.setObserverType(Observer.WINEAVAIBLE);
                    wine.addObserver(client);
                    wine.allarm(requiredQuantity, client);
                    return false;
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    int id = this.getLastOrderId() + 1;
                    Order order = new Order(id, date, client, wine, requiredQuantity);
                    this.orders.add(order); //memorizzo l'ordine andato a buon fine nel sistema
                    this.decreaseStocks(wine, requiredQuantity); //decremento la giacenza
                    wine.removeObserver(client);
                    return true;
                }
            }
            else{
                //il vino non è disponibile
                client.setObserverType(Observer.WINEAVAIBLE);
                wine.addObserver(client);
                wine.finish();
            }
        }
        return false;

    }

}
