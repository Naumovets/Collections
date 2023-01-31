public interface CarList<T> extends CarCollection<T> {
    boolean add(T car, int index);
    boolean add(T car);
    int size();
    boolean remove(T car);
    boolean removeAt(int index);
    void clear();

    boolean contains(T car);
    T get(int index);


}
