package it.unipr.ingegneria.api;

import it.unipr.ingegneria.exception.AvailabilityException;
import it.unipr.ingegneria.utils.Params;

import java.util.List;
import java.util.Map;

/**
 *
 * The {@code IStoreManager} is a general interface that  extends the {@code IWarehouseManager} whit other function for the storage in the warehouse.
 * @param <T>
 * @see it.unipr.ingegneria.api.IWarehouseManager
 * @see it.unipr.ingegneria.exception.AvailabilityException
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 */
public interface IStoreManager<T> extends IWarehouseManager<T> {
    /**
     * Method that sell the wine
     * @param elements
     * @return a generic list
     * @throws AvailabilityException
     */
    List<T> sellWine(Map<Params, Object> elements) throws AvailabilityException;

    /**
     * Method method of replenishing wine in the warehouse
     * @param elements
     */
    void provisionWine(Map<Params, Object> elements);

    /**
     * Method to send the orders at the costumers
     */
    void sendOrders();

}
