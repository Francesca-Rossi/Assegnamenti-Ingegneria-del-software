package it.unipr.ingegneria.entities.api;

public interface IAuthentication {
    void login(String c1, String c2) throws Exception;
    void logout() throws Exception;
}