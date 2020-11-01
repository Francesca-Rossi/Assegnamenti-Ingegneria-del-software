package it.unipr.ingegneria.entities.api;

import it.unipr.ingegneria.entities.user.User;

public interface IUserManager {
    void addUser(User user);
    void deleteUser(User user);
    Boolean hasUser(User item);
}
