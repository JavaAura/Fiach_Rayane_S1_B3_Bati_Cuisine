package Models;

abstract public class Component {
    private String name;
    private double vatRate;

    public Component(String name, double vatRate) {
        this.name = name;
        this.vatRate = vatRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }
}
