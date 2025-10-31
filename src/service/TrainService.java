package service;

import model.Wagon;
import model.Train;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Клас для виконання розрахунків: сортування, підрахунки, фільтрація
 */
public class TrainService {

    /**
     * Сортує вагони за рівнем комфортності (за спаданням)
     */
    public void sortWagonsByComfort(Train train) {
        if (train.getWagons().isEmpty()) {
            System.out.println("Немає вагонів для сортування!");
            return;
        }
        train.getWagons().sort((w1, w2) -> Integer.compare(w2.getComfortLevel(), w1.getComfortLevel()));
        System.out.println("Вагони відсортовані за комфортом (спадаючий порядок)");
    }

    /**
     * Сортує вагони за кількістю пасажирів
     */
    public void sortWagonsByPassengers(Train train) {
        if (train.getWagons().isEmpty()) {
            System.out.println("Немає вагонів для сортування!");
            return;
        }
        train.getWagons().sort((w1, w2) -> Integer.compare(w2.getPassengerCount(), w1.getPassengerCount()));
        System.out.println("Вагони відсортовані за кількістю пасажирів");
    }

    /**
     * Находит вагоны, в которых количество пассажиров находится в заданном диапазоне
     */
    public List<Wagon> findWagonsByPassengerRange(Train train, int minPassengers, int maxPassengers) {
        return train.getWagons().stream()
                .filter(w -> w.getPassengerCount() >= minPassengers && w.getPassengerCount() <= maxPassengers)
                .collect(Collectors.toList());
    }

    /**
     * Находит вагоны по типу
     */
    public List<Wagon> findWagonsByType(Train train, String type) {
        return train.getWagons().stream()
                .filter(w -> w.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Находит вагоны по уровню комфорта
     */
    public List<Wagon> findWagonsByComfortLevel(Train train, int comfortLevel) {
        return train.getWagons().stream()
                .filter(w -> w.getComfortLevel() == comfortLevel)
                .collect(Collectors.toList());
    }

    /**
     * Подраховує середню кількість пасажирів на вагон
     */
    public double getAveragePassengers(Train train) {
        if (train.getWagons().isEmpty()) {
            return 0;
        }
        return (double) train.getTotalPassengers() / train.getWagonCount();
    }

    /**
     * Подраховує середній рівень комфорту
     */
    public double getAverageComfortLevel(Train train) {
        if (train.getWagons().isEmpty()) {
            return 0;
        }
        return train.getWagons().stream()
                .mapToDouble(Wagon::getComfortLevel)
                .average()
                .orElse(0);
    }

    /**
     * Знаходить найбільш комфортний вагон
     */
    public Wagon getMostComfortableWagon(Train train) {
        if (train.getWagons().isEmpty()) {
            return null;
        }
        return train.getWagons().stream()
                .max(Comparator.comparingInt(Wagon::getComfortLevel))
                .orElse(null);
    }

    /**
     * Знаходить вагон з найбільшою кількістю пасажирів
     */
    public Wagon getWagonWithMostPassengers(Train train) {
        if (train.getWagons().isEmpty()) {
            return null;
        }
        return train.getWagons().stream()
                .max(Comparator.comparingInt(Wagon::getPassengerCount))
                .orElse(null);
    }

    /**
     * Обчислює загальну вагу багажу за типом вагона
     */
    public double getLuggageByWagonType(Train train, String type) {
        return train.getWagons().stream()
                .filter(w -> w.getType().equalsIgnoreCase(type))
                .mapToDouble(Wagon::getLuggageWeight)
                .sum();
    }

    /**
     * Виводить статистику поїзда
     */
    public void printTrainStatistics(Train train) {
        if (train.getWagons().isEmpty()) {
            System.out.println("Поїзд порожній!");
            return;
        }

        System.out.println("\n=== Статистика поїзда: " + train.getName() + " ===");
        System.out.println("Загальна кількість пасажирів: " + train.getTotalPassengers());
        System.out.println("Загальна вага багажу: " + train.getTotalLuggage() + " кг");
        System.out.println("Кількість вагонів: " + train.getWagonCount());
        System.out.println("Середня кількість пасажирів на вагон: " + String.format("%.2f", getAveragePassengers(train)));
        System.out.println("Середній рівень комфорту: " + String.format("%.2f", getAverageComfortLevel(train)));

        Wagon mostComfortable = getMostComfortableWagon(train);
        if (mostComfortable != null) {
            System.out.println("Найбільш комфортний вагон: ID=" + mostComfortable.getId() + " (комфорт=" + mostComfortable.getComfortLevel() + ")");
        }

        Wagon mostPassengers = getWagonWithMostPassengers(train);
        if (mostPassengers != null) {
            System.out.println("Вагон з найбільшою кількістю пасажирів: ID=" + mostPassengers.getId() + " (" + mostPassengers.getPassengerCount() + " пасажирів)");
        }
        System.out.println("====================================\n");
    }
}
