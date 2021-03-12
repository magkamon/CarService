package repository;

import domain.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getAllCustomers();
    void addCustomer (Customer customer);
    void deleteCustomer (Customer customer);

}
