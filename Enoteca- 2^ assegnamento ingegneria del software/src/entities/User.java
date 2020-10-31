package entities;


import api.IObserver;
import utils.Observer;
import utils.Type;


public abstract class User implements IObserver {

    private long ID;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Type userType;
    private WineShop wineshop;
    private Observer observerType;


    public User(Type type)
    {
        this.userType=type;
    }
    public User(long id, String name, String surname, String email, String password, Type type)
    {
        this.ID=id;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;
        this.userType=type;
    }

    public User setID(long ID) {
        this.ID = ID;
        return this;
    }

    public long getID() {
        return ID;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setWineshop(WineShop wineshop) {
        this.wineshop = wineshop;
        return this;
    }

    public WineShop getWineshop() {
        return wineshop;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setUserType(Type type) {
        this.userType = type;
        return this;
    }

    public Type getUserType() {
        return userType;
    }
    public User setObserverType(Observer type)
    {
        this.observerType=observerType;
        return this;
    }

    public Observer getObserverType() {
        return observerType;
    }

}
