package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WagonTest {

    private Wagon wagon;

    @Before
    public void setUp() {
        wagon = new PassengerWagon(1, "Platzkart", 54, 120.5, 3, false, false);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, wagon.getId());
        assertEquals("Platzkart", wagon.getType());
        assertEquals(54, wagon.getPassengerCount());
        assertEquals(120.5, wagon.getLuggageWeight(), 0.001);
        assertEquals(3, wagon.getComfortLevel());
    }

    @Test
    public void testSetId() {
        wagon.setId(2);
        assertEquals(2, wagon.getId());
    }

    @Test
    public void testSetType() {
        wagon.setType("Coupe");
        assertEquals("Coupe", wagon.getType());
    }

    @Test
    public void testSetPassengerCount() {
        wagon.setPassengerCount(36);
        assertEquals(36, wagon.getPassengerCount());
    }

    @Test
    public void testSetLuggageWeight() {
        wagon.setLuggageWeight(150.0);
        assertEquals(150.0, wagon.getLuggageWeight(), 0.001);
    }

    @Test
    public void testSetComfortLevel() {
        wagon.setComfortLevel(5);
        assertEquals(5, wagon.getComfortLevel());
    }

    @Test
    public void testToString() {
        String expected = "PassengerWagon{id=1, type='Platzkart', passengerCount=54, luggageWeight=120.5, comfortLevel=3, hasWiFi=false, hasAirConditioning=false}";
        assertEquals(expected, wagon.toString());
    }

    @Test
    public void testWagonWithZeroPassengers() {
        Wagon emptyWagon = new PassengerWagon(2, "Empty", 0, 0.0, 1, false, false);
        assertEquals(0, emptyWagon.getPassengerCount());
        assertEquals(0.0, emptyWagon.getLuggageWeight(), 0.001);
    }

    @Test
    public void testWagonWithMaxComfortLevel() {
        Wagon luxWagon = new PassengerWagon(3, "СВ", 18, 50.0, 5, false, false);
        assertEquals(5, luxWagon.getComfortLevel());
    }
}
