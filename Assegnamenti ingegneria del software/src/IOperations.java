public interface IOperations<T> {
    void subscribe(T t);
    void delete(T activity);
}
