package it.unipr.ingegneria.api;

import java.util.List;
/**
 * The  {@code IWarehouseManager} is a generic interface that include the principal service for the Warehouse
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 * @param <T>
 */
public interface IWarehouseManager<T> {
     /**
      * Method to search an something by name.
      * @param name
      * @return a generic list
      */
     List<T> findByName(String name);

     /**
      * Method to search an something by year.
      * @param d
      * @return
      */
     List<T> findByYear(int d);
}
