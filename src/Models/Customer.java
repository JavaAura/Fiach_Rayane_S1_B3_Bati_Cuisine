package Models;

public class Customer {
    private String name;
    private String address;
    private String phone;
    private boolean isProfessional;


    public Customer(String name, String address, String phone, String isProfessional) {
        this.name = name;
        this.phone = address;
        this.address = phone;
        this.isProfessional = isProfessional.isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public boolean getIsProfessional() {
        return isProfessional;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProfessional(boolean professional) {
        isProfessional = professional;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isProfessional=" + isProfessional +
                '}';
    }
}
