package models;

import java.util.List;

public class Train {
    private String number;
    private Locomotive locomotive;
    private List<PassengerCarriage> cars;

    public Train(String number, Locomotive locomotive, List<PassengerCarriage> cars) {
        this.number = number;
        this.locomotive = locomotive;
        this.cars = cars;
    }

    public String getNumber() { return number; }
    public void setNumber(String number) {this.number = number; }

    public Locomotive getLocomotive() { return locomotive; }
    public void setLocomotive(Locomotive locomotive) {this.locomotive = locomotive; }

    public List<PassengerCarriage> getCars() { return cars; }
    public void setCars(List<PassengerCarriage> cars) {this.cars = cars; }

    public void addCar(PassengerCarriage car) {
        cars.add(car);
    }

    public boolean removeCarByNumber(int carNumber) {
        return cars.removeIf(car -> car.getCarNumber() == carNumber);
    }

    public int totalPassengers() {
        int sum = 0;
        for (PassengerCarriage car : cars) {
            sum += car.getPassengers();
        }
        return sum;
    }

    public int totalBaggageKg() {
        int sum = 0;
        for (PassengerCarriage car : cars) {
            sum += car.getBaggageKg();
        }
        return sum;
    }

    public void sortByComfortDesc() {
        for (int i = 0; i < cars.size() - 1; i++) {
            for (int j = i + 1; j < cars.size(); j++) {
                if (cars.get(i).getComfort().ordinal() < cars.get(j).getComfort().ordinal()) {
                    PassengerCarriage temp = cars.get(i);
                    cars.set(i, cars.get(j));
                    cars.set(j, temp);
                }
            }
        }
    }

    public List<PassengerCarriage> findCarsByPassengerRange(int minInclusive, int maxInclusive) {
        cars.stream()
                .filter(car -> car.getPassengers() >= minInclusive && car.getPassengers() <= maxInclusive);
        return cars;
    }
}
