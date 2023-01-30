import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CarMapTest {

    CarMap carMap;

    @Before
    public void setUp() throws Exception {
        carMap = new CarHashMap();
        for(int i = 0; i < 100; i++){
            carMap.put(new CarOwner(i,"name"+i,"lastname"+i),
                       new Car("brand"+i,i));
        }
    }

    @Test
    public void size(){
        assertEquals(100,carMap.size());
    }

    @Test
    public void whenPutNewKeyAndValueThenMustBe101(){
        assertEquals(100,carMap.size());
        carMap.put(new CarOwner(100,"name100","lastname100"),
                new Car("brand100",100));
        assertEquals(101,carMap.size());
    }

    @Test
    public void whenPutWithTheSameOwnerThenMustBe100(){
        assertEquals(100,carMap.size());
        carMap.put(new CarOwner(0,"name0","lastname0"),
                new Car("brand100",100));
        assertEquals(100,carMap.size());
    }

    @Test
    public void whenPutWithTheDiffOwnerButWithTheSameValuesThenMustBe101(){
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
        assertEquals(100,carMap.keySet().size());
        for(CarOwner carOwner : carMap.keySet()){
            assertNotNull(carOwner);
        }
    }

    @Test
    public void values(){
        assertEquals(100,carMap.values().size());
        for(Car car : carMap.values()){
            assertNotNull(car);
        }
    }

    @Test
    public void clear(){
        assertEquals(100,carMap.size());
        carMap.clear();
        assertEquals(0,carMap.size());
    }

    @Test
    public void whenTryRemoveNonExistentOwnerThenMustReturnFalse(){
        assertEquals(100,carMap.size());
        assertFalse(carMap.remove(new CarOwner(100,"name"+100,"lastname"+100)));
        assertEquals(100,carMap.size());
    }

    @Test
    public void whenTryRemoveExistentOwnerThenMustReturnTrue(){
        assertEquals(100,carMap.size());
        assertTrue(carMap.remove(new CarOwner(0,"name"+0,"lastname"+0)));
        assertEquals(99,carMap.size());
    }
}