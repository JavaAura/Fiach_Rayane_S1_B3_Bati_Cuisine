package Repository.Interfaces;

import Models.Customer;
import java.util.List;

public interface CustomerRepository {
    void addCustomer(Customer customer);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();

}
