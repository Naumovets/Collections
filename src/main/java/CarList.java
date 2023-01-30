public interface CarList extends CarCollection {
    boolean add(Car car, int index);
    boolean add(Car car);
    int size();
    boolean remove(Car car);
    boolean removeAt(int index);
    void clear();

    boolean contains(Car car);
    Car get(int index);


}
