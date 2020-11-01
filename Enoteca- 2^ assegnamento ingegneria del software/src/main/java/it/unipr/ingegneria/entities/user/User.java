package it.unipr.ingegneria.entities.user;


import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.api.IObserver;
import it.unipr.ingegneria.utils.Type;

import java.util.Observer;


public abstract class User implements IObserver {

    private long _id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Type userType;
    protected WineShop wineshop;
    protected Boolean isAuthenticated;
    private Observer observerType;

    public User(long _id, String name, String surname, String email, String password, Type type) {
        this._id = _id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = type;
        this.isAuthenticated = false;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
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

    public void setWineshop(WineShop wineshop) {
        this.wineshop = wineshop;
    }

    public WineShop getWineshop() {
        return wineshop;
    }

    public void setObserverType(Observer type) {
        this.observerType = observerType;
    }

    public Observer getObserverType() {
        return observerType;
    }

}
