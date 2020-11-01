package it.unipr.ingegneria.entities.api;

import it.unipr.ingegneria.entities.Wine;

public interface IObserver {
    void notify(Wine t, String c);
}
