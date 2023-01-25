import java.util.Arrays;

public class CarArrayList implements CarList{

    int size = 0;
    Car[] list = new Car[10];


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(Car car) {
        for(int i = 0; i < size; i++){
            if(car.equals(list[i])){
                removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
        for(int i = index; i < size-1; i++){
            list[i] = list[i+1];
        }
        size--;
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        list = new Car[10];
    }

    @Override
    public void add(Car car) {
        if (size == list.length){
            list = Arrays.copyOf(list,list.length*2);
        }
        list[size] = car;
        size++;
    }

    void checkIndex(int index){
        if(index >= size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
