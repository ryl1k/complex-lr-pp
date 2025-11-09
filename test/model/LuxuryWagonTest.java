package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LuxuryWagonTest {

    private LuxuryWagon luxuryWagon;

    @Before
    public void setUp() {
        luxuryWagon = new LuxuryWagon(1, "SV", 18, 40.0, 5, true, true, true, true, 500.0);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, luxuryWagon.getId());
        assertEquals("SV", luxuryWagon.getType());
        assertEquals(18, luxuryWagon.getPassengerCount());
        assertEquals(40.0, luxuryWagon.getLuggageWeight(), 0.001);
        assertEquals(5, luxuryWagon.getComfortLevel());
        assertTrue(luxuryWagon.isHasWiFi());
        assertTrue(luxuryWagon.isHasAirConditioning());
        assertTrue(luxuryWagon.isHasRestaurant());
        assertTrue(luxuryWagon.isHasBar());
        assertEquals(500.0, luxuryWagon.getPricePerSeat(), 0.001);
    }

    @Test
    public void testInheritanceFromPassengerWagon() {
        assertTrue(luxuryWagon instanceof PassengerWagon);
        assertTrue(luxuryWagon instanceof Wagon);
    }

    @Test
    public void testSetHasRestaurant() {
        luxuryWagon.setHasRestaurant(false);
        assertFalse(luxuryWagon.isHasRestaurant());
    }

    @Test
    public void testSetHasBar() {
        luxuryWagon.setHasBar(false);
        assertFalse(luxuryWagon.isHasBar());
    }

    @Test
    public void testSetPricePerSeat() {
        luxuryWagon.setPricePerSeat(750.0);
        assertEquals(750.0, luxuryWagon.getPricePerSeat(), 0.001);
    }

    @Test
    public void testToString() {
        String result = luxuryWagon.toString();
        assertTrue(result.contains("LuxuryWagon"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("type='SV'"));
        assertTrue(result.contains("hasRestaurant=true"));
        assertTrue(result.contains("hasBar=true"));
        assertTrue(result.contains("pricePerSeat=500.0"));
    }

    @Test
    public void testLuxuryWagonWithoutExtras() {
        LuxuryWagon basic = new LuxuryWagon(2, "Budget SV", 20, 50.0, 4, false, false, false, false, 300.0);
        assertFalse(basic.isHasRestaurant());
        assertFalse(basic.isHasBar());
        assertEquals(300.0, basic.getPricePerSeat(), 0.001);
    }

    @Test
    public void testInheritedMethods() {
        luxuryWagon.setHasWiFi(false);
        luxuryWagon.setHasAirConditioning(false);

        assertFalse(luxuryWagon.isHasWiFi());
        assertFalse(luxuryWagon.isHasAirConditioning());
    }

    @Test
    public void testZeroPrice() {
        LuxuryWagon freeWagon = new LuxuryWagon(3, "Promo", 10, 20.0, 3, true, true, true, true, 0.0);
        assertEquals(0.0, freeWagon.getPricePerSeat(), 0.001);
    }
}
