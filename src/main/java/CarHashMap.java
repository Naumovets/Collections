import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CarHashMap<K,V> implements CarMap<K,V>{
    private int size = 0;

    private double LOAD_FACTOR = 0.75;
    private Object[] array = new Object[16];

    @Override
    public void put(K key, V value) {
        updateArray();
        int index = Math.abs(key.hashCode() % array.length);
        if(array[index] == null){
            array[index] = new Entry(key,value,null);
            size++;
        }else{
            Entry check = (Entry) array[index];
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
    public V get(K key) {
        int index = Math.abs(key.hashCode() % array.length);
        Entry entry = (Entry) array[index];
        while (entry!= null){
            if(entry.key.equals(key)){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        int index = 0;
        int count = 0;
        Entry entry = (Entry) array[index];
        while(count < size){
            if(entry!=null) {
                set.add(entry.key);
                entry = entry.next;
                count++;
            }
            else{
                entry = (Entry) array[++index];
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> cars = new LinkedList<>();
        for(K owner : keySet()){
            cars.add(get(owner));
        }
        return cars;
    }

    @Override
    public boolean remove(K key) {
        int index = Math.abs(key.hashCode() % array.length);
        if(array[index]==null){
            return false;
        }
        if(((Entry)array[index]).key.equals(key)){
            array[index] = ((Entry) array[index]).next;
            size--;
            return true;
        }
        else{
            Entry entry = ((Entry) array[index]).next;
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
        array = new Object[16];
        size = 0;
    }

    private void updateArray(){
        if (size >= array.length * LOAD_FACTOR){
            Object[] newArray = new Object[array.length*2];
            Set<K> keys = keySet();
            for(K key : keys){
                int index = Math.abs(key.hashCode() % newArray.length);
                if(newArray[index] ==null){
                    newArray[index] = new Entry(key,get(key),null);
                }else{
                    Entry check = (Entry) newArray[index];
                    while(check.next !=null){
                        check = check.next;
                    }
                    check.next = new Entry(key,get(key),null);
                }
            }
            array = newArray;
        }
    }

    private class Entry{
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
