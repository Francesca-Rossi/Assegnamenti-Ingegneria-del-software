package entities;

import java.util.Date;

public class Order {

    private long ID;
    private Date date;
    private User user;
    private Wine wine;
    private int quantity;
    private boolean delivered;

    public Order(long id, Date date, User user, Wine wine, int quantity){
        this.ID=id;
        this.date=date;
        this.user=user;
        this.wine = wine;
        this.quantity=quantity;
        this.delivered=false;
    }

    public Order setID(long ID) {
        this.ID = ID;
        return  this;
    }

    public long getID() {
        return ID;
    }

    public Order setDate(Date date) {
        this.date = date;
        return  this;
    }

    public Date getDate() {
        return date;
    }

    public Order setQuantity(int quantity) {
        this.quantity = quantity;
        return  this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Order setUser(User user) {
        this.user = user;
        return  this;
    }

    public User getUser() {
        return user;
    }

    public Order setWine(Wine wine) {
        this.wine = wine;
        return  this;
    }

    public Wine getWine() {
        return wine;
    }

    public Order setDelivered(boolean delivered) {
        this.delivered = delivered;
        return this;
    }

    public boolean isDelivered() {
        return delivered;
    }
}
