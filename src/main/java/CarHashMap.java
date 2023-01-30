import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CarHashMap implements CarMap{
    private int size = 0;

    private double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[16];

    @Override
    public void put(CarOwner key, Car value) {
        updateArray();
        int index = Math.abs(key.hashCode() % array.length);
        if(array[index] == null){
            array[index] = new Entry(key,value,null);
            size++;
        }else{
            Entry check = array[index];
            if(check.key.equals(key)){
                check.value = value;
            }
            while(check.next !=null && !check.key.equals(key)){
                check = check.next;
            }
            if(check.key.equals(key)){
                check.value = value;
            }else{
                size++;
                check.next = new Entry(key,value,null);
            }

        }
    }

    @Override
    public Car get(CarOwner key) {
        int index = Math.abs(key.hashCode() % array.length);
        Entry entry = array[index];
        while (entry!= null){
            if(entry.key.equals(key)){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> set = new HashSet<>();
        int index = 0;
        int count = 0;
        Entry entry = array[index];
        while(count < size){
            if(entry!=null) {
                set.add(entry.key);
                entry = entry.next;
                count++;
            }
            else{
                entry = array[++index];
            }
        }
        return set;
    }

    @Override
    public List<Car> values() {
        List<Car> cars = new LinkedList<>();
        for(CarOwner owner : keySet()){
            cars.add(get(owner));
        }
        return cars;
    }

    @Override
    public boolean remove(CarOwner key) {
        int index = Math.abs(key.hashCode() % array.length);
        if(array[index]==null){
            return false;
        }
        if(array[index].key.equals(key)){
            array[index] = array[index].next;
            size--;
            return true;
        }
        else{
            Entry entry = array[index].next;
            while (entry != null){
                if(entry.key.equals(key)){
                    size--;
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[16];
        size = 0;
    }

    private void updateArray(){
        if (size >= array.length * LOAD_FACTOR){
            Entry[] newArray = new Entry[array.length*2];
            Set<CarOwner> keys = keySet();
            for(CarOwner key : keys){
                int index = Math.abs(key.hashCode() % newArray.length);
                if(newArray[index]==null){
                    newArray[index] = new Entry(key,get(key),null);
                }else{
                    Entry check = newArray[index];
                    while(check.next !=null){
                        check = check.next;
                    }
                    check.next = new Entry(key,get(key),null);
                }
            }
            array = newArray;
        }
    }

    private static class Entry{
        private CarOwner key;
        private Car value;
        private Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
