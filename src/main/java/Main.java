import java.util.*;

public class Main {
    public static void main(String[] args) {
        CarArrayList<Car> array = new CarArrayList<>();
        Car car = new Car("Brand0",0);
        array.add(car);
        array.add(car);
        array.removeAt(0);
    }
}