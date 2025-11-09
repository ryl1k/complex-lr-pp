package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrainTest {

    private Train train;
    private Wagon wagon1;
    private Wagon wagon2;
    private PassengerWagon passengerWagon;

    @Before
    public void setUp() {
        train = new Train("Intercity+", "Kyiv-Lviv");
        wagon1 = new PassengerWagon(1, "Platzkart", 54, 120.0, 3, false, false);
        wagon2 = new PassengerWagon(2, "Coupe", 36, 80.0, 4, false, false);
        passengerWagon = new PassengerWagon(3, "Coupe Deluxe", 30, 60.0, 5, true, true);
    }

    @Test
    public void testConstructor() {
        assertEquals("Intercity+", train.getName());
        assertEquals("Kyiv-Lviv", train.getDestination());
        assertNotNull(train.getWagons());
        assertEquals(0, train.getWagons().size());
    }

    @Test
    public void testSetName() {
        train.setName("Express");
        assertEquals("Express", train.getName());
    }

    @Test
    public void testSetDestination() {
        train.setDestination("Kyiv-Odesa");
        assertEquals("Kyiv-Odesa", train.getDestination());
    }

    @Test
    public void testAddWagon() {
        train.addWagon(wagon1);
        assertEquals(1, train.getWagonCount());
        assertTrue(train.getWagons().contains(wagon1));
    }

    @Test
    public void testAddMultipleWagons() {
        train.addWagon(wagon1);
        train.addWagon(wagon2);
        train.addWagon(passengerWagon);
        assertEquals(3, train.getWagonCount());
    }

    @Test
    public void testRemoveWagon() {
        train.addWagon(wagon1);
        train.addWagon(wagon2);
        assertEquals(2, train.getWagonCount());

        train.removeWagon(1);
        assertEquals(1, train.getWagonCount());
        assertFalse(train.getWagons().contains(wagon1));
    }

    @Test
    public void testRemoveNonExistentWagon() {
        train.addWagon(wagon1);
        train.removeWagon(999);
        assertEquals(1, train.getWagonCount());
    }

    @Test
    public void testGetWagonById() {
        train.addWagon(wagon1);
        train.addWagon(wagon2);

        Wagon found = train.getWagonById(1);
        assertNotNull(found);
        assertEquals(wagon1, found);
    }

    @Test
    public void testGetWagonByIdNotFound() {
        train.addWagon(wagon1);
        Wagon found = train.getWagonById(999);
        assertNull(found);
    }

    @Test
    public void testGetTotalPassengers() {
        train.addWagon(wagon1); // 54 passengers
        train.addWagon(wagon2); // 36 passengers
        train.addWagon(passengerWagon); // 30 passengers

        assertEquals(120, train.getTotalPassengers());
    }

    @Test
    public void testGetTotalPassengersEmptyTrain() {
        assertEquals(0, train.getTotalPassengers());
    }

    @Test
    public void testGetTotalLuggage() {
        train.addWagon(wagon1); // 120.0 kg
        train.addWagon(wagon2); // 80.0 kg
        train.addWagon(passengerWagon); // 60.0 kg

        assertEquals(260.0, train.getTotalLuggage(), 0.001);
    }

    @Test
    public void testGetTotalLuggageEmptyTrain() {
        assertEquals(0.0, train.getTotalLuggage(), 0.001);
    }

    @Test
    public void testGetWagonCount() {
        assertEquals(0, train.getWagonCount());
        train.addWagon(wagon1);
        assertEquals(1, train.getWagonCount());
        train.addWagon(wagon2);
        assertEquals(2, train.getWagonCount());
    }

    @Test
    public void testToString() {
        String result = train.toString();
        assertTrue(result.contains("Train"));
        assertTrue(result.contains("name='Intercity+'"));
        assertTrue(result.contains("destination='Kyiv-Lviv'"));
        assertTrue(result.contains("wagons=0"));
    }

    @Test
    public void testToStringWithWagons() {
        train.addWagon(wagon1);
        train.addWagon(wagon2);
        String result = train.toString();
        assertTrue(result.contains("wagons=2"));
    }
}
