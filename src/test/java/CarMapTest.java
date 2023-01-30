import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CarMapTest {

    CarMap carMap = new CarHashMap();

    @Before
    public void setUp() throws Exception {

    }



    @Test
    public void whenPut100ElementsThenSizeMustBe100(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.size());
    }

    @Test
    public void whenPutNewKeyAndValueThenMustBe101(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.size());
        carMap.put(new CarOwner(100,"name100","lastname100"),
                new Car("brand100",100));
        assertEquals(101,carMap.size());
    }

    @Test
    public void whenPutWithTheSameOwnerThenMustBe100(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.size());
        carMap.put(new CarOwner(0,"name0","lastname0"),
                new Car("brand100",100));
        assertEquals(100,carMap.size());
    }

    @Test
    public void whenPut100ElementsWith10DifferentKeysThenMustBe10(){
        for(int i = 0; i <100; i++){
            int index = i % 10;
            carMap.put(new CarOwner(index,"name"+index,"lastname"+index),
                    new Car("brand"+i,i));
        }
        assertEquals(10,carMap.size());
    }

    @Test
    public void whenPutWithTheDiffOwnerButWithTheSameValuesThenMustBe101(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.size());
        carMap.put(new CarOwner(100,"name100","lastname100"),
                new Car("brand0",0));
        assertEquals(101,carMap.size());
    }

    @Test
    public void whenInsertToGetOwnerMustReturnCar(){
        Car car = new Car("brand0",0);
        CarOwner owner = new CarOwner(100,"name100","lastname100");
        carMap.put(owner, car);
        CarOwner newOwner = new CarOwner(100,"name100","lastname100");
        Car newCar = carMap.get(newOwner);
        assertEquals(car,newCar);
    }

    @Test
    public void whenInsertToGetNonExistentOwnerMustReturnNull(){
        CarOwner owner = new CarOwner(100,"name100","lastname100");
        Car newCar = carMap.get(owner);
        assertNull(newCar);
    }

    @Test
    public void keySet(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.keySet().size());
        for(CarOwner carOwner : carMap.keySet()){
            assertNotNull(carOwner);
        }
    }

    @Test
    public void values(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.values().size());
        for(Car car : carMap.values()){
            assertNotNull(car);
        }
    }

    @Test
    public void clear(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.size());
        carMap.clear();
        assertEquals(0,carMap.size());
    }

    @Test
    public void whenTryRemoveNonExistentOwnerThenMustReturnFalse(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.size());
        assertFalse(carMap.remove(new CarOwner(100,"name"+100,"lastname"+100)));
        assertEquals(100,carMap.size());
    }

    @Test
    public void whenTryRemoveExistentOwnerThenMustReturnTrue(){
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                    new Car("brand"+i,i));
        }
        assertEquals(100,carMap.size());
        assertTrue(carMap.remove(new CarOwner(0,"name"+0,"lastname"+0)));
        assertEquals(99,carMap.size());
    }
}