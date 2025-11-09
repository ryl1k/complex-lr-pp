

package service;

import model.Wagon;
import model.Train;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class TrainService {
    private static final Logger logger = Logger.getLogger(TrainService.class.getName());

    public void sortWagonsByComfort(Train train) {
        if (train.getWagons().isEmpty()) {
            System.out.println("No wagons to sort!");
            logger.warning("Attempt to sort empty train");
            return;
        }
        logger.info("Sorting wagons by comfort. Count: " + train.getWagonCount());
        train.getWagons().sort((w1, w2) -> Integer.compare(w2.getComfortLevel(), w1.getComfortLevel()));
        System.out.println("Вагони відсортовані за комфортом (спадаючий порядок)");
    }

    public void sortWagonsByPassengers(Train train) {
        if (train.getWagons().isEmpty()) {
            System.out.println("No wagons to sort!");
            return;
        }
        train.getWagons().sort((w1, w2) -> Integer.compare(w2.getPassengerCount(), w1.getPassengerCount()));
        System.out.println("Вагони відсортовані за кількістю пасажирів");
    }

    public List<Wagon> findWagonsByPassengerRange(Train train, int minPassengers, int maxPassengers) {
        logger.info("Search wagons by passenger range: " + minPassengers + "-" + maxPassengers);
        List<Wagon> result = train.getWagons().stream()
                .filter(w -> w.getPassengerCount() >= minPassengers && w.getPassengerCount() <= maxPassengers)
                .collect(Collectors.toList());
        logger.info("Found wagons: " + result.size());
        return result;
    }

    public List<Wagon> findWagonsByType(Train train, String type) {
        return train.getWagons().stream()
                .filter(w -> w.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Wagon> findWagonsByComfortLevel(Train train, int comfortLevel) {
        return train.getWagons().stream()
                .filter(w -> w.getComfortLevel() == comfortLevel)
                .collect(Collectors.toList());
    }

    public double getAveragePassengers(Train train) {
        if (train.getWagons().isEmpty()) {
            return 0;
        }
        return (double) train.getTotalPassengers() / train.getWagonCount();
    }

    public double getAverageComfortLevel(Train train) {
        if (train.getWagons().isEmpty()) {
            return 0;
        }
        return train.getWagons().stream()
                .mapToDouble(Wagon::getComfortLevel)
                .average()
                .orElse(0);
    }

    public Wagon getMostComfortableWagon(Train train) {
        if (train.getWagons().isEmpty()) {
            return null;
        }
        return train.getWagons().stream()
                .max(Comparator.comparingInt(Wagon::getComfortLevel))
                .orElse(null);
    }

    public Wagon getWagonWithMostPassengers(Train train) {
        if (train.getWagons().isEmpty()) {
            return null;
        }
        return train.getWagons().stream()
                .max(Comparator.comparingInt(Wagon::getPassengerCount))
                .orElse(null);
    }

    public double getLuggageByWagonType(Train train, String type) {
        return train.getWagons().stream()
                .filter(w -> w.getType().equalsIgnoreCase(type))
                .mapToDouble(Wagon::getLuggageWeight)
                .sum();
    }

    public void printTrainStatistics(Train train) {
        if (train.getWagons().isEmpty()) {
            System.out.println("Train is empty!");
            return;
        }

        System.out.println("\n=== Train statistics: " + train.getName() + " ===");
        System.out.println("Total passengers: " + train.getTotalPassengers());
        System.out.println("Total luggage weight: " + train.getTotalLuggage() + " кг");
        System.out.println("Wagon count: " + train.getWagonCount());
        System.out.println("Average passengers per wagon: " + String.format("%.2f", getAveragePassengers(train)));
        System.out.println("Average comfort level: " + String.format("%.2f", getAverageComfortLevel(train)));

        Wagon mostComfortable = getMostComfortableWagon(train);
        if (mostComfortable != null) {
            System.out.println("Most comfortable wagon: ID=" + mostComfortable.getId() + " (comfort=" + mostComfortable.getComfortLevel() + ")");
        }

        Wagon mostPassengers = getWagonWithMostPassengers(train);
        if (mostPassengers != null) {
            System.out.println("Wagon with most passengers: ID=" + mostPassengers.getId() + " (" + mostPassengers.getPassengerCount() + " passengers)");
        }
        System.out.println("====================================\n");
    }
}
