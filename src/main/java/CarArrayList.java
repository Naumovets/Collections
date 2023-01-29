import java.util.Arrays;

public class CarArrayList implements CarList{

    private int size = 0;
    private Car[] list = new Car[10];


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Car car){
        for(int i = 0; i < size; i++){
            if(list[i].equals(car)){
                return true;
            }
        }
        return false;
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
//        for(int i = index; i < size-1; i++){
//            list[i] = list[i+1];
//        }
        size--;
//      изначальный список, начиная с какого элемента по индексу, в какой список добавляем,
//      начиная с какого индекса добавляем, размер замены

        System.arraycopy(list,index+1,list,index,size-index);
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        list = new Car[10];
    }

    @Override
    public boolean add(Car car) {
        increaseArray();
        list[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index){
        increaseArray();
        if(index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(list,index,list,index+1,size-index);
        list[index] = car;
        size++;
        return true;
    }

    @Override
    public Car get(int index){
        checkIndex(index);
        return list[index];
    }

    void checkIndex(int index){
        if(index >= size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void increaseArray(){
        if (size == list.length){
            list = Arrays.copyOf(list,list.length*2);
        }
    }
}
