package it.unipr.ingegneria.entities.api;

import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.utils.Params;

import java.util.Map;

public interface IStoreManager<T> extends IWarehouseManager<T> {

    void sellWine(Map<Params, Object> elements) throws AvailabilityException;

    void provisionWine(Map<Params, Object> elements);

}
