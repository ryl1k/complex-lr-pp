package models;

public abstract class Carriage extends RollingStock {
    protected int carNumber;
    protected double lengthMeters;
    protected boolean airConditioning;

    public Carriage(int carNumber, double lengthMeters, boolean airConditioning,
                    String id, double massTons, String manufacturer) {
        super(id, massTons, manufacturer);
        this.carNumber = carNumber;
        this.lengthMeters = lengthMeters;
        this.airConditioning = airConditioning;
    }

    public int getCarNumber() { return carNumber; }
    public void setCarNumber(int carNumber) {  this.carNumber = carNumber; }

    public double getLengthMeters() { return lengthMeters; }
    public void setLengthMeters(double lengthMeters) { this.lengthMeters = lengthMeters; }

    public boolean isAirConditioning() { return airConditioning; }
    public void setAirConditioning(boolean airConditioning) { this.airConditioning = airConditioning; }
}