package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PassengerWagonTest {

    private PassengerWagon passengerWagon;

    @Before
    public void setUp() {
        passengerWagon = new PassengerWagon(1, "Coupe", 36, 80.0, 4, true, true);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, passengerWagon.getId());
        assertEquals("Coupe", passengerWagon.getType());
        assertEquals(36, passengerWagon.getPassengerCount());
        assertEquals(80.0, passengerWagon.getLuggageWeight(), 0.001);
        assertEquals(4, passengerWagon.getComfortLevel());
        assertTrue(passengerWagon.isHasWiFi());
        assertTrue(passengerWagon.isHasAirConditioning());
    }

    @Test
    public void testInheritanceFromWagon() {
        assertTrue(passengerWagon instanceof Wagon);
    }

    @Test
    public void testSetHasWiFi() {
        passengerWagon.setHasWiFi(false);
        assertFalse(passengerWagon.isHasWiFi());
    }

    @Test
    public void testSetHasAirConditioning() {
        passengerWagon.setHasAirConditioning(false);
        assertFalse(passengerWagon.isHasAirConditioning());
    }

    @Test
    public void testToString() {
        String result = passengerWagon.toString();
        assertTrue(result.contains("PassengerWagon"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("type='Coupe'"));
        assertTrue(result.contains("passengerCount=36"));
        assertTrue(result.contains("hasWiFi=true"));
        assertTrue(result.contains("hasAirConditioning=true"));
    }

    @Test
    public void testPassengerWagonWithoutAmenities() {
        PassengerWagon basic = new PassengerWagon(2, "Economy", 54, 100.0, 2, false, false);
        assertFalse(basic.isHasWiFi());
        assertFalse(basic.isHasAirConditioning());
    }

    @Test
    public void testSettersFromParentClass() {
        passengerWagon.setPassengerCount(40);
        passengerWagon.setLuggageWeight(90.0);
        passengerWagon.setComfortLevel(5);

        assertEquals(40, passengerWagon.getPassengerCount());
        assertEquals(90.0, passengerWagon.getLuggageWeight(), 0.001);
        assertEquals(5, passengerWagon.getComfortLevel());
    }
}
