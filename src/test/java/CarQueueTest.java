import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarQueueTest {

    private CarQueue array;

    @Before
    public void setUp() throws Exception {
        array = new CarLinkedList();
        for(int i = 0; i<10; i++){
            array.add(new Car("Brand"+i,i));
        }
    }

    @Test
    public void add() {
        assertEquals(10,array.size());
    }

    @Test
    public void peek() {
        Car car = array.peek();
        assertEquals("Brand0",car.getBrand());
        assertEquals(10,array.size());
    }

    @Test
    public void poll() {
        Car car = array.poll();
        assertEquals("Brand0",car.getBrand());
        assertEquals(9,array.size());
    }
}