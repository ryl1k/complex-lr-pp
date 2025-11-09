package model;

/**
 * Базовий клас вагона, що містить основні параметри
 */
public class Wagon {
    private int id;
    private String type;
    private int passengerCount;
    private double luggageWeight;
    private int comfortLevel; // 1-5

    public Wagon(int id, String type, int passengerCount, double luggageWeight, int comfortLevel) {
        this.id = id;
        this.type = type;
        this.passengerCount = passengerCount;
        this.luggageWeight = luggageWeight;
        this.comfortLevel = comfortLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public double getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(double luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(int comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    @Override
    public String toString() {
        return "Wagon{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", passengerCount=" + passengerCount +
                ", luggageWeight=" + luggageWeight +
                ", comfortLevel=" + comfortLevel +
                '}';
    }
}
