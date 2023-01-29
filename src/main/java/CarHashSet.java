import java.util.Iterator;

public class CarHashSet implements CarSet{
    private int size = 0;
    private double LOAD_FACTOR = 0.75;
    private static final int INITIAL_CAPACITY = 16;
    private Entry[] array = new Entry[INITIAL_CAPACITY];

    private boolean add(Car car, Entry[] dst){
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null){
            Entry entry = new Entry(car,null);
            dst[position] = entry;
            return true;
        }else{
            Entry existedElement = dst[position];
            while (true){
                if(existedElement.value.equals(car)){
                    return false;
                }else if(existedElement.next == null){
                    existedElement.next = new Entry(car,null);
                    return true;
                }else{
                    existedElement = existedElement.next;
                }
            }
        }
    }

    @Override
    public boolean add(Car car) {
        if(size >= array.length*LOAD_FACTOR){
            increaseArray();
        }
        boolean added = add(car,array);
        if (added){
            size++;
        }
        return added;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car,array.length);
        if(array[position] == null){
            return false;
        }
        Entry secondLast = array[position];
        Entry last = secondLast.next;
        if(secondLast.value.equals(car)){
            array[position] = last;
            size--;
            return true;
        }
        while (last!=null){
            if(last.value.equals(car)){
                secondLast.next = last.next;
                size--;
                return true;
            }
            else{
                secondLast = last;
                last = last.next;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        array = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private int getElementPosition(Car car, int arrayLenght){
        return Math.abs(car.hashCode() % arrayLenght);
    }



    private void increaseArray(){
        Entry[] newArray = new Entry[array.length*2];
        for(Entry entry : array){
            Entry existentElement = entry;
            while (existentElement!=null){
                add(existentElement.value,newArray);
                existentElement = existentElement.next;
            }
        }
        array = newArray;
    }

    @Override
    public boolean contains(Car car){
        int id = car.hashCode() % array.length;
        Entry current = array[id];
        while (current!=null) {
            if (current.value.equals(car)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<Car> iterator(){
        return new Iterator<Car>() {
            int count = 0;
            int index = 0;
            Entry entry = array[index];
            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Car next() {
                while(entry == null) {
                    entry = array[++index];
                }
                Car car = entry.value;
                entry = entry.next;
                count++;
                return car;
            }
        };
    }

    private static class Entry{
        private Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
