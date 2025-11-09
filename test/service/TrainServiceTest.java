package service;

import model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class TrainServiceTest {

    private TrainService trainService;
    private Train train;
    private Wagon wagon1;
    private Wagon wagon2;
    private PassengerWagon passengerWagon;
    private LuxuryWagon luxuryWagon;

    @Before
    public void setUp() {
        trainService = new TrainService();
        train = new Train("Test Train", "Kyiv-Lviv");

        wagon1 = new PassengerWagon(1, "Platzkart", 54, 120.0, 2, false, false);
        wagon2 = new PassengerWagon(2, "Platzkart", 48, 100.0, 3, false, false);
        passengerWagon = new PassengerWagon(3, "Coupe", 36, 80.0, 4, true, true);
        luxuryWagon = new LuxuryWagon(4, "SV", 18, 40.0, 5, true, true, true, true, 500.0);

        train.addWagon(wagon1);
        train.addWagon(wagon2);
        train.addWagon(passengerWagon);
        train.addWagon(luxuryWagon);
    }

    @Test
    public void testSortWagonsByComfort() {
        trainService.sortWagonsByComfort(train);
        List<Wagon> wagons = train.getWagons();

        // Verify descending sort order
        assertEquals(5, wagons.get(0).getComfortLevel());
        assertEquals(4, wagons.get(1).getComfortLevel());
        assertEquals(3, wagons.get(2).getComfortLevel());
        assertEquals(2, wagons.get(3).getComfortLevel());
    }

    @Test
    public void testSortWagonsByPassengers() {
        trainService.sortWagonsByPassengers(train);
        List<Wagon> wagons = train.getWagons();

        // Verify descending sort order
        assertEquals(54, wagons.get(0).getPassengerCount());
        assertEquals(48, wagons.get(1).getPassengerCount());
        assertEquals(36, wagons.get(2).getPassengerCount());
        assertEquals(18, wagons.get(3).getPassengerCount());
    }

    @Test
    public void testFindWagonsByPassengerRange() {
        List<Wagon> result = trainService.findWagonsByPassengerRange(train, 30, 50);

        assertEquals(2, result.size());
        assertTrue(result.contains(wagon2)); // 48 passengers
        assertTrue(result.contains(passengerWagon)); // 36 passengers
    }

    @Test
    public void testFindWagonsByPassengerRangeNoResults() {
        List<Wagon> result = trainService.findWagonsByPassengerRange(train, 100, 200);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindWagonsByType() {
        List<Wagon> result = trainService.findWagonsByType(train, "Platzkart");

        assertEquals(2, result.size());
        assertTrue(result.contains(wagon1));
        assertTrue(result.contains(wagon2));
    }

    @Test
    public void testFindWagonsByTypeNoResults() {
        List<Wagon> result = trainService.findWagonsByType(train, "Невідомий");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindWagonsByComfortLevel() {
        List<Wagon> result = trainService.findWagonsByComfortLevel(train, 3);

        assertEquals(1, result.size());
        assertTrue(result.contains(wagon2));
    }

    @Test
    public void testGetAveragePassengers() {
        double avg = trainService.getAveragePassengers(train);
        // (54 + 48 + 36 + 18) / 4 = 39
        assertEquals(39.0, avg, 0.001);
    }

    @Test
    public void testGetAveragePassengersEmptyTrain() {
        Train emptyTrain = new Train("Порожній", "Ніде");
        double avg = trainService.getAveragePassengers(emptyTrain);
        assertEquals(0.0, avg, 0.001);
    }

    @Test
    public void testGetAverageComfortLevel() {
        double avg = trainService.getAverageComfortLevel(train);
        // (2 + 3 + 4 + 5) / 4 = 3.5
        assertEquals(3.5, avg, 0.001);
    }

    @Test
    public void testGetAverageComfortLevelEmptyTrain() {
        Train emptyTrain = new Train("Порожній", "Ніде");
        double avg = trainService.getAverageComfortLevel(emptyTrain);
        assertEquals(0.0, avg, 0.001);
    }

    @Test
    public void testGetMostComfortableWagon() {
        Wagon result = trainService.getMostComfortableWagon(train);
        assertEquals(luxuryWagon, result);
        assertEquals(5, result.getComfortLevel());
    }

    @Test
    public void testGetMostComfortableWagonEmptyTrain() {
        Train emptyTrain = new Train("Порожній", "Ніде");
        Wagon result = trainService.getMostComfortableWagon(emptyTrain);
        assertNull(result);
    }

    @Test
    public void testGetWagonWithMostPassengers() {
        Wagon result = trainService.getWagonWithMostPassengers(train);
        assertEquals(wagon1, result);
        assertEquals(54, result.getPassengerCount());
    }

    @Test
    public void testGetWagonWithMostPassengersEmptyTrain() {
        Train emptyTrain = new Train("Порожній", "Ніде");
        Wagon result = trainService.getWagonWithMostPassengers(emptyTrain);
        assertNull(result);
    }

    @Test
    public void testGetLuggageByWagonType() {
        double luggage = trainService.getLuggageByWagonType(train, "Platzkart");
        // 120.0 + 100.0 = 220.0
        assertEquals(220.0, luggage, 0.001);
    }

    @Test
    public void testGetLuggageByWagonTypeNoResults() {
        double luggage = trainService.getLuggageByWagonType(train, "Невідомий");
        assertEquals(0.0, luggage, 0.001);
    }
}
