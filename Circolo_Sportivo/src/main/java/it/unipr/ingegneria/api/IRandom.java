package it.unipr.ingegneria.api;


import it.unipr.ingegneria.utils.Type;
import it.unipr.ingegneria.utils.Typology;

/**
 * Generic interface for the random operation implemented in the entities
 * @param <T> Return parameter for the method of the interface
 * @param <C> Return parameter for the method of the interface
 * @author Ruslan, Francesca, Nick
 */
public interface IRandom<T, C> {
    <T> T getRandomUser(Type type);
    <C> C getRandomActivity(Typology type);
}
