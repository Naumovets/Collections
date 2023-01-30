import java.util.*;

public class Main {
    public static void main(String[] args) {
        CarMap carMap = new CarHashMap();
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        for(int i = 0; i < 100; i++){
            System.out.println(carMap.get(new CarOwner(i,"name"+i,"lastname"+i)));
        }
    }
}