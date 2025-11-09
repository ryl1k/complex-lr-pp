package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що агрегує об'єкти Wagon і містить методи для керування складом поїзда
 */
public class Train {
    private String name;
    private String destination;
    private List<Wagon> wagons;

    public Train(String name, String destination) {
        this.name = name;
        this.destination = destination;
        this.wagons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void addWagon(Wagon wagon) {
        wagons.add(wagon);
    }

    public void removeWagon(int id) {
        wagons.removeIf(w -> w.getId() == id);
    }

    public Wagon getWagonById(int id) {
        return wagons.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public int getTotalPassengers() {
        return wagons.stream()
                .mapToInt(Wagon::getPassengerCount)
                .sum();
    }

    public double getTotalLuggage() {
        return wagons.stream()
                .mapToDouble(Wagon::getLuggageWeight)
                .sum();
    }

    public int getWagonCount() {
        return wagons.size();
    }

    public void printAllWagons() {
        if (wagons.isEmpty()) {
            System.out.println("Немає вагонів у складі!");
            return;
        }
        System.out.println("\n=== Склад поїзда: " + name + " ===");
        System.out.println("Напрямок: " + destination);
        System.out.println("Кількість вагонів: " + wagons.size());
        System.out.println("-----------------------------------------");
        for (Wagon wagon : wagons) {
            System.out.println(wagon);
        }
        System.out.println("-----------------------------------------\n");
    }

    @Override
    public String toString() {
        return "Train{" +
                "name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", wagons=" + wagons.size() +
                '}';
    }
}
