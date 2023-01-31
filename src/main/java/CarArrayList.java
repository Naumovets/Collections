import java.util.Arrays;
import java.util.Iterator;

public class CarArrayList<T> implements CarList<T> {

    private int size = 0;
    private Object[] list = new Object[10];


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T car) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(car)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T car) {
        for (int i = 0; i < size; i++) {
            if (car.equals(list[i])) {
                removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
//        for(int i = index; i < size-1; i++){
//            list[i] = list[i+1];
//        }
        size--;
//      изначальный список, начиная с какого элемента по индексу, в какой список добавляем,
//      начиная с какого индекса добавляем, размер замены

        System.arraycopy(list, index + 1, list, index, size - index);
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        list = new Car[10];
    }

    @Override
    public boolean add(T car) {
        increaseArray();
        list[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean add(T car, int index) {
        increaseArray();
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = car;
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) list[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                T car = (T) list[index++];
                return car;
            }
        };
    }

    void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void increaseArray() {
        if (size == list.length) {
            list = Arrays.copyOf(list, list.length * 2);
        }
    }
}
