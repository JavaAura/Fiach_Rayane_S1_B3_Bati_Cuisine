package Models;

abstract public class Component {
    private String name;
    private double vatRate;
    private int projectId;

    public Component(String name, double vatRate, int projectId) {
        this.name = name;
        this.vatRate = vatRate;
        this.projectId = projectId;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
