public interface IOperationsPrevileged<T, C> extends IOperations<C> {

    void addUser(T t);
    void deleteUser(T t);
}
