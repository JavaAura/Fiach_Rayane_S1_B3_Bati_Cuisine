package Services;

import Models.Customer;
import Repository.Interfaces.CustomerRepository;
import Repository.CustomerRepositoryImpl;
import Services.Interfaces.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {
        this.customerRepository = new CustomerRepositoryImpl();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer);
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        return customerRepository.getCustomerByName(customerName);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}