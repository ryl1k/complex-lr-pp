package model;

public class PassengerWagon extends Wagon {
    private boolean hasWiFi;
    private boolean hasAirConditioning;

    public PassengerWagon(int id, String type, int passengerCount, double luggageWeight,
                         int comfortLevel, boolean hasWiFi, boolean hasAirConditioning) {
        super(id, type, passengerCount, luggageWeight, comfortLevel);
        this.hasWiFi = hasWiFi;
        this.hasAirConditioning = hasAirConditioning;
    }

    public boolean isHasWiFi() {
        return hasWiFi;
    }

    public void setHasWiFi(boolean hasWiFi) {
        this.hasWiFi = hasWiFi;
    }

    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    @Override
    public String toString() {
        return "PassengerWagon{" +
                "id=" + getId() +
                ", type='" + getType() + '\'' +
                ", passengerCount=" + getPassengerCount() +
                ", luggageWeight=" + getLuggageWeight() +
                ", comfortLevel=" + getComfortLevel() +
                ", hasWiFi=" + hasWiFi +
                ", hasAirConditioning=" + hasAirConditioning +
                '}';
    }
}
