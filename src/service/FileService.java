package service;

import model.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

// Handles saving/loading trains to CSV and JSON files
// Note: JSON parsing is done manually without external libs
public class FileService {
    private static final Logger logger = Logger.getLogger(FileService.class.getName());
    private String filePath;

    public FileService(String filePath) {
        this.filePath = filePath;
    }

    public void saveToCSV(Train train) {
        logger.info("Starting to save train to CSV file: " + filePath);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ID,Type,PassengerCount,LuggageWeight,ComfortLevel,WiFi,AirConditioning,Restaurant,Bar,PricePerSeat");
            for (Wagon wagon : train.getWagons()) {
                writer.println(wagonToCSVLine(wagon));
            }
            System.out.println("Train saved to file: " + filePath);
            logger.info("Train successfully saved to CSV file. Wagon count: " + train.getWagonCount());
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
            logger.log(Level.SEVERE, "Critical error saving to CSV file: " + filePath, e);
        }
    }

    public Train loadFromCSV(String trainName, String destination) {
        logger.info("Starting to load train from CSV file: " + filePath);
        Train train = new Train(trainName, destination);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                Wagon wagon = parseCSVLine(line);
                if (wagon != null) {
                    train.addWagon(wagon);
                }
            }
            System.out.println("Train loaded from file: " + filePath);
            logger.info("Train successfully loaded from CSV file. Wagon count: " + train.getWagonCount());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            logger.log(Level.WARNING, "CSV file not found: " + filePath, e);
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
            logger.log(Level.SEVERE, "Critical error loading from CSV file: " + filePath, e);
        }
        return train;
    }

    public void saveToJSON(Train train) {
        logger.info("Starting to save train to JSON file: " + filePath);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("{");
            writer.println("  \"train\": {");
            writer.println("    \"name\": \"" + train.getName() + "\",");
            writer.println("    \"destination\": \"" + train.getDestination() + "\",");
            writer.println("    \"wagons\": [");

            List<Wagon> wagons = train.getWagons();
            for (int i = 0; i < wagons.size(); i++) {
                writer.print(wagonToJSON(wagons.get(i)));
                if (i < wagons.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }

            writer.println("    ]");
            writer.println("  }");
            writer.println("}");
            System.out.println("Train saved to JSON file: " + filePath);
            logger.info("Train successfully saved to JSON file. Wagon count: " + train.getWagonCount());
        } catch (IOException e) {
            System.out.println("Error saving to JSON file: " + e.getMessage());
            logger.log(Level.SEVERE, "Critical error saving to JSON file: " + filePath, e);
        }
    }

    public Train loadFromJSON(String trainName, String destination) {
        logger.info("Starting to load train from JSON file: " + filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read entire JSON file into a string
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }

            String json = jsonContent.toString();

            // Extract train name and destination from JSON
            String name = extractStringValue(json, "name");
            String dest = extractStringValue(json, "destination");

            Train train = new Train(name != null ? name : trainName,
                                   dest != null ? dest : destination);

            // Find the wagons array in JSON
            int wagonsStart = json.indexOf("\"wagons\":");
            if (wagonsStart == -1) {
                System.out.println("Wagons array not found in JSON");
                logger.warning("Wagons array not found in JSON file: " + filePath);
                return train;
            }

            int arrayStart = json.indexOf("[", wagonsStart);
            int arrayEnd = json.lastIndexOf("]");

            if (arrayStart == -1 || arrayEnd == -1) {
                System.out.println("Invalid wagons array format");
                logger.warning("Invalid wagons array format у JSON файлі: " + filePath);
                return train;
            }

            String wagonsArray = json.substring(arrayStart + 1, arrayEnd);

            // Split into individual wagon JSON objects
            List<String> wagonObjects = splitWagonObjects(wagonsArray);

            for (String wagonJson : wagonObjects) {
                Wagon wagon = parseWagonFromJSON(wagonJson);
                if (wagon != null) {
                    train.addWagon(wagon);
                }
            }

            System.out.println("Train loaded from JSON file: " + filePath);
            logger.info("Train successfully loaded from JSON file. Wagon count: " + train.getWagonCount());
            return train;

        } catch (FileNotFoundException e) {
            System.out.println("JSON file not found: " + filePath);
            logger.log(Level.WARNING, "JSON file not found: " + filePath, e);
        } catch (IOException e) {
            System.out.println("Error loading from JSON: " + e.getMessage());
            logger.log(Level.SEVERE, "Критична помилка при завантаженні з JSON файлу: " + filePath, e);
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
            logger.log(Level.SEVERE, "Critical error parsing JSON: " + e.getMessage(), e);
        }
        return new Train(trainName, destination);
    }

    private String wagonToCSVLine(Wagon wagon) {
        StringBuilder sb = new StringBuilder();
        sb.append(wagon.getId()).append(",");
        sb.append(wagon.getType()).append(",");
        sb.append(wagon.getPassengerCount()).append(",");
        sb.append(wagon.getLuggageWeight()).append(",");
        sb.append(wagon.getComfortLevel()).append(",");

        if (wagon instanceof PassengerWagon) {
            PassengerWagon pw = (PassengerWagon) wagon;
            sb.append(pw.isHasWiFi()).append(",");
            sb.append(pw.isHasAirConditioning()).append(",");

            if (wagon instanceof LuxuryWagon) {
                LuxuryWagon lw = (LuxuryWagon) wagon;
                sb.append(lw.isHasRestaurant()).append(",");
                sb.append(lw.isHasBar()).append(",");
                sb.append(lw.getPricePerSeat());
            } else {
                sb.append("false,false,0.0");
            }
        } else {
            sb.append("false,false,false,false,0.0");
        }

        return sb.toString();
    }

    private Wagon parseCSVLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 5) {
            return null;
        }

        try {
            // Parse basic wagon fields
            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int passengers = Integer.parseInt(parts[2]);
            double luggage = Double.parseDouble(parts[3]);
            int comfort = Integer.parseInt(parts[4]);

            // Determine wagon type based on number of fields
            if (parts.length >= 9) {
                boolean wifi = Boolean.parseBoolean(parts[5]);
                boolean ac = Boolean.parseBoolean(parts[6]);

                if (parts.length >= 10) {
                    // Has restaurant/bar fields = LuxuryWagon
                    boolean restaurant = Boolean.parseBoolean(parts[7]);
                    boolean bar = Boolean.parseBoolean(parts[8]);
                    double price = Double.parseDouble(parts[9]);
                    return new LuxuryWagon(id, type, passengers, luggage, comfort, wifi, ac, restaurant, bar, price);
                } else {
                    // Has wifi/ac but no restaurant = PassengerWagon
                    return new PassengerWagon(id, type, passengers, luggage, comfort, wifi, ac);
                }
            } else {
                // Basic wagon with no amenities
                return new PassengerWagon(id, type, passengers, luggage, comfort, false, false);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

    private String wagonToJSON(Wagon wagon) {
        StringBuilder sb = new StringBuilder();
        sb.append("      {");
        sb.append("\"id\": ").append(wagon.getId()).append(", ");
        sb.append("\"type\": \"").append(wagon.getType()).append("\", ");
        sb.append("\"passengerCount\": ").append(wagon.getPassengerCount()).append(", ");
        sb.append("\"luggageWeight\": ").append(wagon.getLuggageWeight()).append(", ");
        sb.append("\"comfortLevel\": ").append(wagon.getComfortLevel());

        if (wagon instanceof PassengerWagon) {
            PassengerWagon pw = (PassengerWagon) wagon;
            sb.append(", \"hasWiFi\": ").append(pw.isHasWiFi());
            sb.append(", \"hasAirConditioning\": ").append(pw.isHasAirConditioning());

            if (wagon instanceof LuxuryWagon) {
                LuxuryWagon lw = (LuxuryWagon) wagon;
                sb.append(", \"hasRestaurant\": ").append(lw.isHasRestaurant());
                sb.append(", \"hasBar\": ").append(lw.isHasBar());
                sb.append(", \"pricePerSeat\": ").append(lw.getPricePerSeat());
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    private String extractStringValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) {
            return null;
        }

        int valueStart = json.indexOf("\"", keyIndex + searchKey.length()) + 1;
        int valueEnd = json.indexOf("\"", valueStart);

        if (valueStart > 0 && valueEnd > valueStart) {
            return json.substring(valueStart, valueEnd);
        }
        return null;
    }

    private Integer extractIntValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) {
            return null;
        }

        int valueStart = keyIndex + searchKey.length();

        // Skip whitespace
        while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
            valueStart++;
        }

        int valueEnd = valueStart;

        // Find end of number (comma or bracket)
        while (valueEnd < json.length() &&
               (Character.isDigit(json.charAt(valueEnd)) ||
               json.charAt(valueEnd) == '-')) {
            valueEnd++;
        }

        try {
            return Integer.parseInt(json.substring(valueStart, valueEnd).trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double extractDoubleValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) {
            return null;
        }

        int valueStart = keyIndex + searchKey.length();

        // Skip whitespace
        while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
            valueStart++;
        }

        int valueEnd = valueStart;

        // Find end of number (comma or bracket)
        while (valueEnd < json.length() &&
               (Character.isDigit(json.charAt(valueEnd)) ||
                json.charAt(valueEnd) == '.' ||
                json.charAt(valueEnd) == '-')) {
            valueEnd++;
        }

        try {
            return Double.parseDouble(json.substring(valueStart, valueEnd).trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Boolean extractBooleanValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) {
            return null;
        }

        int valueStart = keyIndex + searchKey.length();

        // Skip whitespace
        while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
            valueStart++;
        }

        String remaining = json.substring(valueStart);

        if (remaining.startsWith("true")) {
            return true;
        } else if (remaining.startsWith("false")) {
            return false;
        }
        return null;
    }

    // Manually split JSON array into individual wagon objects by counting braces
    private List<String> splitWagonObjects(String wagonsArray) {
        List<String> wagonObjects = new ArrayList<>();
        int braceCount = 0;
        int objectStart = -1;

        for (int i = 0; i < wagonsArray.length(); i++) {
            char c = wagonsArray.charAt(i);

            if (c == '{') {
                if (braceCount == 0) {
                    objectStart = i;
                }
                braceCount++;
            } else if (c == '}') {
                braceCount--;
                if (braceCount == 0 && objectStart != -1) {
                    wagonObjects.add(wagonsArray.substring(objectStart, i + 1));
                    objectStart = -1;
                }
            }
        }

        return wagonObjects;
    }

    // Parse a single wagon from JSON and figure out what type it is
    private Wagon parseWagonFromJSON(String wagonJson) {
        try {
            // Parse required fields first
            Integer id = extractIntValue(wagonJson, "id");
            String type = extractStringValue(wagonJson, "type");
            Integer passengerCount = extractIntValue(wagonJson, "passengerCount");
            Double luggageWeight = extractDoubleValue(wagonJson, "luggageWeight");
            Integer comfortLevel = extractIntValue(wagonJson, "comfortLevel");

            if (id == null || type == null || passengerCount == null ||
                luggageWeight == null || comfortLevel == null) {
                System.out.println("Failed to parse required wagon fields: id=" + id + ", type=" + type +
                    ", passengerCount=" + passengerCount + ", luggageWeight=" + luggageWeight + ", comfortLevel=" + comfortLevel);
                return null;
            }

            // Check for optional passenger wagon fields
            Boolean hasWiFi = extractBooleanValue(wagonJson, "hasWiFi");
            Boolean hasAirConditioning = extractBooleanValue(wagonJson, "hasAirConditioning");

            if (hasWiFi == null && hasAirConditioning == null) {
                // Basic wagon with no amenities
                return new PassengerWagon(id, type, passengerCount, luggageWeight, comfortLevel, false, false);
            }

            // Check for luxury wagon fields
            Boolean hasRestaurant = extractBooleanValue(wagonJson, "hasRestaurant");
            Boolean hasBar = extractBooleanValue(wagonJson, "hasBar");
            Double pricePerSeat = extractDoubleValue(wagonJson, "pricePerSeat");

            if (hasRestaurant != null && hasBar != null && pricePerSeat != null) {
                // Has restaurant/bar = LuxuryWagon
                return new LuxuryWagon(id, type, passengerCount, luggageWeight, comfortLevel,
                                      hasWiFi != null ? hasWiFi : false,
                                      hasAirConditioning != null ? hasAirConditioning : false,
                                      hasRestaurant, hasBar, pricePerSeat);
            } else {
                // Only has wifi/ac = PassengerWagon
                return new PassengerWagon(id, type, passengerCount, luggageWeight, comfortLevel,
                                         hasWiFi != null ? hasWiFi : false,
                                         hasAirConditioning != null ? hasAirConditioning : false);
            }

        } catch (Exception e) {
            System.out.println("Error parsing wagon: " + e.getMessage());
            return null;
        }
    }
}
