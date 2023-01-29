import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarSetTest {

    private CarSet carSet = new CarHashSet();

    @Before
    public void setUp() throws Exception {
        for(int i = 0; i < 100; i++){
            carSet.add(new Car("Toyota" + i,i));
        }
    }

    @Test
    public void whenAdded4TheSameElementsThenSizeMustBe101(){
        Car a = new Car("You",1);
        Car b = new Car("You",1);
        Car c = new Car("You",1);
        Car d = new Car("You",1);
        assertTrue(carSet.add(a));
        assertFalse(carSet.add(b));
        assertFalse(carSet.add(c));
        assertFalse(carSet.add(d));
        assertEquals(101,carSet.size());
    }

    @Test
    public void whenAdded4DiffElementsThenSizeMustBe104(){
        Car a = new Car("a",1);
        Car b = new Car("b",2);
        Car c = new Car("c",3);
        Car d = new Car("d",4);
        assertTrue(carSet.add(a));
        assertTrue(carSet.add(b));
        assertTrue(carSet.add(c));
        assertTrue(carSet.add(d));
        assertEquals(104,carSet.size());
    }

    @Test
    public void whenRemove1From104ElementThenSizeMustBe103(){
        Car a = new Car("a",1);
        Car b = new Car("b",2);
        Car c = new Car("c",3);
        Car d = new Car("d",4);
        assertTrue(carSet.add(a));
        assertTrue(carSet.add(b));
        assertTrue(carSet.add(c));
        assertTrue(carSet.add(d));
        assertEquals(104,carSet.size());
        assertTrue(carSet.remove(a));
        assertFalse(carSet.remove(a));
        assertEquals(103,carSet.size());
    }

    @Test
    public void whenWeTryRemoveNonExistentElementThenMethodMustReturnFalse(){
        Car a = new Car("a",1);
        Car b = new Car("b",2);
        Car c = new Car("c",3);
        Car d = new Car("d",4);
        Car e = new Car("e",5);
        assertTrue(carSet.add(a));
        assertTrue(carSet.add(b));
        assertTrue(carSet.add(c));
        assertTrue(carSet.add(d));
        assertEquals(104,carSet.size());
        assertFalse(carSet.remove(e));
    }

    @Test
    public void whenWeClearThenSizeMustBe0(){
        carSet.clear();
        assertEquals(0,carSet.size());
    }

    @Test
    public void whenAddElementThenContainsWillReturnTrue(){
        Car car = new Car("BMW",2);
        carSet.add(car);
        assertEquals(true,carSet.contains(car));
    }

    @Test
    public void whenNonExistentElementInContainsThenContainsWillReturnTrue(){
        Car car = new Car("BMW",2);
        assertFalse(carSet.contains(car));
    }
}