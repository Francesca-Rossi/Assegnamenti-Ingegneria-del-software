package it.unipr.ingegneria.api;

import it.unipr.ingegneria.entities.user.User;

/**
 * The {@code IUserManager} interface contain the method to manage a user.
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 */
public interface IUserManager {
    /**
     * Method to add user in the system.
     * @param user
     */
    void addUser(User user);

    /**
     * Method to remove user by the system.
     * @param user
     */
    void deleteUser(User user);

    /**
     * Method to control if is user.
     * @param item
     * @return
     */
    Boolean hasUser(User item);
}
