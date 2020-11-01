package it.unipr.ingegneria.entities.api;

import java.util.List;

public interface IWarehouseManager<T> {
     List<T> findByName(String name);
     List<T> findByYear(int d);
}
