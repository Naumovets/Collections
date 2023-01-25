import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarListTest {

    private CarList carList;

    @Before
    public void setUp() throws Exception {
        carList = new CarArrayList();
        for(int i = 0; i < 100; i++){
            carList.add(new Car("Toyota" + i,i));
        }
    }

    @Test
    public void whenAdded100CarsThenMustBe100(){
        assertEquals(100,carList.size());
    }

    @Test
    public void whenRemoveByIndexThenMustBe99(){
        assertTrue(carList.removeAt(40));
    }

    @Test
    public void whenRemoveThenMustBe100(){
        Car car = new Car("toyota",12);
        carList.add(car);
        assertEquals(101,carList.size());
        carList.remove(car);
        assertEquals(100,carList.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse(){
        Car car = new Car("toyota",12);
        assertFalse(carList.remove(car));
    }

    @Test
    public void whenClearThenSize0(){
        carList.clear();
        assertEquals(0,carList.size());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIndexOutOfBoundsExceptionThenThrowException(){
        carList.get(101);
    }

    @Test
    public void methodGetReturnedRightValue(){
        Car car = carList.get(0);
        assertEquals("Toyota0",car.getBrand());
    }
}