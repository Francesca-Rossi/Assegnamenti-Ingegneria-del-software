package entities;

import api.IObservable;
import utils.Observer;
import utils.Type;

import java.util.ArrayList;
import java.util.List;

public  class Wine  implements IObservable<User, Integer> {

    private long ID;
    private String name;
    private int year;
    private String producer;
    private String tech_notes;
    private int quantity;
    private List<Vineyard> vineyards;
    private final List<User> observers;

    public Wine()
    {
        this.vineyards=new ArrayList<Vineyard>();
        this.observers=new ArrayList<User>();
    }

    public Wine(long id, String name, int year, String producer, String tech_notes)
    {
        this.ID=id;
        this.name=name;
        this.year=year;
        this.producer=producer;
        this.tech_notes=tech_notes;
        this.vineyards=new ArrayList<Vineyard>();
        this.observers=new ArrayList<User>();

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public Wine setName(String name) {
        this.name = name;
        return this;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }


    public String getProducer() {
        return producer;
    }

    public Wine setProducer(String producer) {
        this.producer = producer;
        return this;
    }
    public String getTech_notes() {
        return tech_notes;
    }
    public Wine setTech_notes(String tech_notes) {
        this.tech_notes = tech_notes;
        return this;
    }
    public List<Vineyard> getVineyards() {
        return vineyards;
    }

    public Wine setVineyards(List<Vineyard> vineyards) {
        this.vineyards = vineyards;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Wine setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public void addObserver(User observer)
    {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }

    };

    @Override
    public void removeObserver(User observer){
        if(this.observers.contains(observer)) {
        this.observers.remove(observer);
        }
    };

    @Override
    public void avaible(Integer newQuantity) {
        //notifico che il vino e tornato disponibile
        this.quantity=newQuantity;
        String message="The wine "+this.name+"is already avaible with "+this.quantity+" bottle";
        for(User observer:this.observers) {
            if (observer.getUserType()== Type.CLIENT ) {
                observer.notify(this, message);
            }
        }
    };

    @Override
    public void finish() {
        String messageClient="The wine "+this.name+"is not avaible";
        String messageEmployee="The wine "+this.name+" is finish";
        for(User observer:this.observers) {
            if (observer.getUserType()== Type.EMPLOYEE){
                observer.notify(this, messageEmployee);}
            else if (observer.getUserType()== Type.CLIENT)
                observer.notify(this, messageClient);
        }
    }

    @Override
    public void allarm(Integer quantity, User user) {
        String message="The client "+user.getName()+" want buy/ request"+quantity+" but "+this.quantity+" were given";
        for(User observer:this.observers) {
            if (observer.getUserType()== Type.EMPLOYEE)
                observer.notify(this, message);
        }
    }
}
