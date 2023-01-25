public interface CarList {
    void add(Car car, int index);
    void add(Car car);
    int size();
    boolean remove(Car car);
    boolean removeAt(int index);
    void clear();
    Car get(int index);


}
