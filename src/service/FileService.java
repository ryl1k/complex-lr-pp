package service;

import model.*;
import java.io.*;
import java.util.*;

/**
 * Клас для зчитування та запису інформації про поїзд у файл
 */
public class FileService {
    private String filePath;

    public FileService(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Зберігає поїзд у CSV файл
     */
    public void saveToCSV(Train train) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ID,Type,PassengerCount,LuggageWeight,ComfortLevel,WiFi,AirConditioning,Restaurant,Bar,PricePerSeat");
            for (Wagon wagon : train.getWagons()) {
                writer.println(wagonToCSVLine(wagon));
            }
            System.out.println("Поїзд збережено у файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні у файл: " + e.getMessage());
        }
    }

    /**
     * Завантажує поїзд з CSV файлу
     */
    public Train loadFromCSV(String trainName, String destination) {
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
            System.out.println("Поїзд завантажено з файлу: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка при завантаженні з файлу: " + e.getMessage());
        }
        return train;
    }

    /**
     * Зберігає поїзд у JSON-подібному форматі
     */
    public void saveToJSON(Train train) {
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
            System.out.println("Поїзд збережено у JSON файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні у JSON файл: " + e.getMessage());
        }
    }

    /**
     * Завантажує поїзд з JSON файлу (спрощена версія)
     */
    public Train loadFromJSON(String trainName, String destination) {
        Train train = new Train(trainName, destination);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            System.out.println("Поїзд завантажено з JSON файлу: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("JSON файл не знайдено: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка при завантаженні з JSON: " + e.getMessage());
        }
        return train;
    }

    /**
     * Конвертує вагон у CSV рядок
     */
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

    /**
     * Розпарсює CSV рядок у Wagon об'єкт
     */
    private Wagon parseCSVLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 5) {
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int passengers = Integer.parseInt(parts[2]);
            double luggage = Double.parseDouble(parts[3]);
            int comfort = Integer.parseInt(parts[4]);

            if (parts.length >= 9) {
                boolean wifi = Boolean.parseBoolean(parts[5]);
                boolean ac = Boolean.parseBoolean(parts[6]);

                if (parts.length >= 10) {
                    boolean restaurant = Boolean.parseBoolean(parts[7]);
                    boolean bar = Boolean.parseBoolean(parts[8]);
                    double price = Double.parseDouble(parts[9]);
                    return new LuxuryWagon(id, type, passengers, luggage, comfort, wifi, ac, restaurant, bar, price);
                } else {
                    return new PassengerWagon(id, type, passengers, luggage, comfort, wifi, ac);
                }
            } else {
                return new Wagon(id, type, passengers, luggage, comfort);
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка при розпарсуванні рядка: " + line);
            return null;
        }
    }

    /**
     * Конвертує вагон у JSON формат
     */
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
}
