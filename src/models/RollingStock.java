package models;

public abstract class RollingStock {
    protected String id;
    protected double massTons;
    protected String manufacturer;

    public RollingStock(String id, double massTons, String manufacturer) {
        this.id = id;
        this.massTons = massTons;
        this.manufacturer = manufacturer;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getMassTons() { return massTons; }
    public void setMassTons(double massTons) { this.massTons = massTons; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
}
