package models;

import models.Enums.ComfortLevel;


public class PassengerCarriage extends Carriage {
    private ComfortLevel comfort;
    private int seats;
    private int passengers;
    private int baggageKg;
    private boolean wifi;

    public PassengerCarriage(ComfortLevel comfort, int seats, int passengers, int baggageKg, boolean wifi, String id,
                             double massTons, String manufacturer, int carNumber, double lengthMeters, boolean airConditioning) {
        super(carNumber,lengthMeters,airConditioning,id,massTons,manufacturer);
        this.comfort = comfort;
        this.seats = seats;
        this.passengers = passengers;
        this.baggageKg = baggageKg;
        this.wifi = wifi;
    }

    public ComfortLevel getComfort() { return comfort; }
    public void setComfort(ComfortLevel comfort) {this.comfort = comfort;}

    public int getSeats() { return seats; }
    public void setSeats(int seats) {this.seats = seats;}

    public int getPassengers() { return passengers; }
    public void setPassengers(int passengers) {this.passengers = passengers;}

    public int getBaggageKg() { return baggageKg; }
    public void setBaggageKg(int baggageKg) {this.baggageKg = baggageKg;}

    public boolean isWifi() { return wifi; }
    public void setWifi(boolean wifi) {this.wifi = wifi;}
}