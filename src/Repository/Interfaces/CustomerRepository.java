package Repository.Interfaces;

import Models.Customer;
import java.util.List;

public interface CustomerRepository {
    void addCustomer(Customer customer);
    Customer getCustomerByName(String customerName);
    List<Customer> getAllCustomers();

}
