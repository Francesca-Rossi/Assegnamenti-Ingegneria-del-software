package api;
import entities.Wine;
public interface IObserver{
    void notify(Wine t, String c);
}
