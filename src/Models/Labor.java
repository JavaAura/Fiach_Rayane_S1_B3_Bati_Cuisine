package Models;

public class Labor extends Component{
    private double hourlyRate;
    private double workHours;
    private double workerProductivity;

    public Labor(String name, double vatRate, double hourlyRate, double workHours, double workerProductivity) {
        super(name, vatRate);
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.workerProductivity = workerProductivity;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

    public void setWorkerProductivity(double workerProductivity) {
        this.workerProductivity = workerProductivity;
    }

    @Override
    public String toString() {
        return "Labor{" +
                "name='" + super.getName() + '\'' +
                ", vatRate=" + super.getVatRate() +
                "hourlyRate=" + hourlyRate +
                ", workHours=" + workHours +
                ", workerProductivity=" + workerProductivity +
                '}';
    }
}
