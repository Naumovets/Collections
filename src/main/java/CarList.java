public interface CarList {
    void add(Car car, int index);
    int size();
    boolean remove(Car car);
    boolean removeAt(int index);
    void clear();
    void add(Car car);
    Car get(int index);


}
