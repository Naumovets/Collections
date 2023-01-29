import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarCollectionTest {
    private CarCollection carCollection;

    @Before
    public void setUp() throws Exception {
        carCollection = new CarHashSet();
        for(int i = 0; i < 100; i++){
            carCollection.add(new Car("Toyota" + i,i));
        }
    }

    @Test
    public void contains() {
        assertTrue(carCollection.contains(new Car("Toyota20",20)));
        assertFalse(carCollection.contains(new Car("Toyota21",19)));
    }
}