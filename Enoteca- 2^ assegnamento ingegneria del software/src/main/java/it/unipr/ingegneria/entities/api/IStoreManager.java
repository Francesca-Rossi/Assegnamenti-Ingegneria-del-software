package it.unipr.ingegneria.entities.api;

import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.utils.Params;

import java.util.List;
import java.util.Map;

public interface IStoreManager<T> extends IWarehouseManager<T> {

    List<T> sellWine(Map<Params, Object> elements) throws AvailabilityException;

    void provisionWine(Map<Params, Object> elements);

    void sendOrders();

}
