package advanced.generics;

public interface Data2<T>{
    void add(T t);
    void delete(T t);
    void update(T t);
    T get();
}
