import java.util.*;

public class Main {
    public static void main(String[] args) {
        CarList array = new CarLinkedList();
        Car car = new Car("Brand0",0);
        array.add(car);
        array.add(car);
        System.out.println(array.size());
        array.removeAt(0);
    }
}