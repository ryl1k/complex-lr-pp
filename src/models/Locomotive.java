package models;

import models.Enums.TractionType;


public class Locomotive extends RollingStock {
    private String model;
    private int powerKw;
    private TractionType tractionType;

    public Locomotive(String id, double massTons, String manufacturer,
                      String model, int powerKw, TractionType tractionType) {
        super(id, massTons, manufacturer);
        this.model = model;
        this.powerKw = powerKw;
        this.tractionType = tractionType;
    }

    public String getModel() { return model; }
    public void setModel(String model) {this.model = model;}

    public int getPowerKw() { return powerKw; }
    public void setPowerKw(int powerKw) { this.powerKw = powerKw; }

    public TractionType getTractionType() { return tractionType; }
    public void setTractionType(TractionType tractionType) {  this.tractionType = tractionType; }
}