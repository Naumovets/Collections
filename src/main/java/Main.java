public class Main {
    public static void main(String[] args) {
        CarHashSet hashSet = new CarHashSet();
        Car car = new Car("BMW",2);
        hashSet.add(car);
        System.out.println(hashSet.contains(car));
    }
}