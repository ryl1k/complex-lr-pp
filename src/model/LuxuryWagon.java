package model;

/**
 * Підклас PassengerWagon для вагонів підвищеного класу
 */
public class LuxuryWagon extends PassengerWagon {
    private boolean hasRestaurant;
    private boolean hasBar;
    private double pricePerSeat;

    public LuxuryWagon(int id, String type, int passengerCount, double luggageWeight,
                      int comfortLevel, boolean hasWiFi, boolean hasAirConditioning,
                      boolean hasRestaurant, boolean hasBar, double pricePerSeat) {
        super(id, type, passengerCount, luggageWeight, comfortLevel, hasWiFi, hasAirConditioning);
        this.hasRestaurant = hasRestaurant;
        this.hasBar = hasBar;
        this.pricePerSeat = pricePerSeat;
    }

    public boolean isHasRestaurant() {
        return hasRestaurant;
    }

    public void setHasRestaurant(boolean hasRestaurant) {
        this.hasRestaurant = hasRestaurant;
    }

    public boolean isHasBar() {
        return hasBar;
    }

    public void setHasBar(boolean hasBar) {
        this.hasBar = hasBar;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    @Override
    public String toString() {
        return "LuxuryWagon{" +
                "id=" + getId() +
                ", type='" + getType() + '\'' +
                ", passengerCount=" + getPassengerCount() +
                ", luggageWeight=" + getLuggageWeight() +
                ", comfortLevel=" + getComfortLevel() +
                ", hasWiFi=" + isHasWiFi() +
                ", hasAirConditioning=" + isHasAirConditioning() +
                ", hasRestaurant=" + hasRestaurant +
                ", hasBar=" + hasBar +
                ", pricePerSeat=" + pricePerSeat +
                '}';
    }
}
