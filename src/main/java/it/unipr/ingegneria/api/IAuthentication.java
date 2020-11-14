package it.unipr.ingegneria.api;

/**
 * The  {@code IAuthentication} interface include the method for the person authentication.
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 *
 */
public interface IAuthentication {
    /**
     * Method to check the login
     * @param c1
     * @param c2
     * @throws Exception
     */
    void login(String c1, String c2) throws Exception;

    /**
     * Method to check the logout
     * @throws Exception
     */
    void logout() throws Exception;
}