package it.unipr.ingegneria.entities.user;

public class CustomerInfo {

    private Customer customer;
    private String wineName;

    public Customer getCustomer() {
        return customer;
    }

    public CustomerInfo setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getWineName() {
        return wineName;
    }

    public CustomerInfo setWineName(String wineName) {
        this.wineName = wineName;
        return this;
    }
}
