package service;

import model.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

public class FileServiceTest {

    private FileService fileService;
    private Train train;
    private String testFilePath;

    @Before
    public void setUp() throws IOException {
        // Create temporary file for tests
        File tempFile = File.createTempFile("test_train_", ".csv");
        testFilePath = tempFile.getAbsolutePath();

        fileService = new FileService(testFilePath);
        train = new Train("Test Train", "Kyiv-Lviv");

        // Add wagons
        train.addWagon(new PassengerWagon(1, "Platzkart", 54, 120.0, 3, false, false));
        train.addWagon(new PassengerWagon(2, "Coupe", 36, 80.0, 4, true, true));
        train.addWagon(new LuxuryWagon(3, "SV", 18, 40.0, 5, true, true, true, true, 500.0));
    }

    @After
    public void tearDown() {
        // Delete temporary file after tests
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveToCSV() {
        fileService.saveToCSV(train);
        File file = new File(testFilePath);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    public void testLoadFromCSV() {
        // First save
        fileService.saveToCSV(train);

        // Then load
        Train loadedTrain = fileService.loadFromCSV("Loaded", "Unknown");

        assertEquals(3, loadedTrain.getWagonCount());
        assertEquals(108, loadedTrain.getTotalPassengers()); // 54 + 36 + 18
        assertEquals(240.0, loadedTrain.getTotalLuggage(), 0.001); // 120 + 80 + 40
    }

    @Test
    public void testSaveAndLoadCSVPreservesData() {
        fileService.saveToCSV(train);
        Train loadedTrain = fileService.loadFromCSV("Test", "Test");

        Wagon wagon1 = loadedTrain.getWagonById(1);
        assertNotNull(wagon1);
        assertEquals("Platzkart", wagon1.getType());
        assertEquals(54, wagon1.getPassengerCount());
        assertEquals(120.0, wagon1.getLuggageWeight(), 0.001);
        assertEquals(3, wagon1.getComfortLevel());

        Wagon wagon2 = loadedTrain.getWagonById(2);
        assertNotNull(wagon2);
        assertTrue(wagon2 instanceof PassengerWagon);
        PassengerWagon pw = (PassengerWagon) wagon2;
        assertTrue(pw.isHasWiFi());
        assertTrue(pw.isHasAirConditioning());

        Wagon wagon3 = loadedTrain.getWagonById(3);
        assertNotNull(wagon3);
        assertTrue(wagon3 instanceof LuxuryWagon);
        LuxuryWagon lw = (LuxuryWagon) wagon3;
        assertTrue(lw.isHasRestaurant());
        assertTrue(lw.isHasBar());
        assertEquals(500.0, lw.getPricePerSeat(), 0.001);
    }

    @Test
    public void testSaveToJSON() throws IOException {
        File jsonFile = File.createTempFile("test_train_", ".json");
        String jsonFilePath = jsonFile.getAbsolutePath();

        try {
            FileService jsonFileService = new FileService(jsonFilePath);
            jsonFileService.saveToJSON(train);

            assertTrue(jsonFile.exists());
            assertTrue(jsonFile.length() > 0);
        } finally {
            jsonFile.delete();
        }
    }

    @Test
    public void testLoadFromJSON() throws IOException {
        File jsonFile = File.createTempFile("test_train_", ".json");
        String jsonFilePath = jsonFile.getAbsolutePath();

        try {
            FileService jsonFileService = new FileService(jsonFilePath);

            // Зберігаємо у JSON
            jsonFileService.saveToJSON(train);

            // Завантажуємо з JSON
            Train loadedTrain = jsonFileService.loadFromJSON("Loaded", "Unknown");

            assertEquals(3, loadedTrain.getWagonCount());
            assertEquals(108, loadedTrain.getTotalPassengers());
        } finally {
            jsonFile.delete();
        }
    }

    @Test
    public void testLoadFromNonExistentFile() {
        FileService nonExistentService = new FileService("non_existent_file.csv");
        Train loadedTrain = nonExistentService.loadFromCSV("Empty", "Empty");

        assertEquals(0, loadedTrain.getWagonCount());
    }

    @Test
    public void testSaveEmptyTrain() {
        Train emptyTrain = new Train("Порожній", "Ніде");
        fileService.saveToCSV(emptyTrain);

        File file = new File(testFilePath);
        assertTrue(file.exists());
    }

    @Test
    public void testLoadEmptyCSV() {
        // Зберігаємо порожній потяг
        Train emptyTrain = new Train("Порожній", "Ніде");
        fileService.saveToCSV(emptyTrain);

        // Завантажуємо
        Train loadedTrain = fileService.loadFromCSV("Test", "Test");
        assertEquals(0, loadedTrain.getWagonCount());
    }

    @Test
    public void testSetFilePath() {
        fileService.setFilePath("new_path.csv");
        assertEquals("new_path.csv", fileService.getFilePath());
    }

    @Test
    public void testGetFilePath() {
        assertEquals(testFilePath, fileService.getFilePath());
    }
}
