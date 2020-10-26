package it.unipr.ingegneria.api;

/**
 * Generic interface for the main operations implemented in Admin class.
 * This interface extends the basic operations interface.
 * @see it.unipr.ingegneria.api.IOperations
 * @param <T>
 * @param <C>
 * @author Ruslan, Francesca, Nick
 */
public interface IOperationsPrevileged<T, C> extends IOperations<C> {
    /**
     * add user in the parameter t
     * @param t
     */
    void addUser(T t);
    /**
     * Remove user from the parameter t
     * @param t
     */
    void deleteUser(T t);
    /**
     * Edit user of the parameter t1 with new info of parameter t2
     * @param t1
     * @param t2
     */
    void editUser(T t1, T t2);
    /**
     * Add activity in the parameter c
     * @param c
     */
    void addActivity(C c);
    /**
     * Remove activity from the parameter c
     * @param c
     */
    void deleteActivity(C c);
    /**
     * Edit activity of the parameter t1 with new info of parameter t2
     * @param c1
     * @param c2
     */
    void editActivity(C c1, C c2);
}
