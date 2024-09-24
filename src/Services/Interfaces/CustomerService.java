package Services.Interfaces;

import Models.Customer;
import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    Customer getCustomerByName(String customerName);
    List<Customer> getAllCustomers();

}