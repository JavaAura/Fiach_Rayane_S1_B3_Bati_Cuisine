package Models;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String phone;
    private boolean isProfessional;


    public Customer(int id, String name, String address, String phone, boolean isProfessional) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isProfessional = isProfessional;
    }

    public int getId() {return id;}

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
                "id=" + id +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isProfessional=" + isProfessional +
                '}';
    }
}
