package repository;

import DataBase.CustomerDataBase;
import DataBase.CustomerDataBaseJson;
import domain.Customer;

import java.util.List;

public class CustomerFileRepository implements CustomerRepository{

    private static final String CUSTOMERS_FILE = "customers_file";
    private final CustomerDataBase customerDataBase = new CustomerDataBaseJson();

    @Override
    public List<Customer> getAllCustomers() {
        return customerDataBase.readCustomers(CUSTOMERS_FILE);
    }

    @Override
    public void addCustomer(Customer customer) {
        List<Customer> customers = customerDataBase.readCustomers(CUSTOMERS_FILE);
        customers.add(customer);
        customerDataBase.saveCustomers(customers,CUSTOMERS_FILE);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        List<Customer> customers = customerDataBase.readCustomers(CUSTOMERS_FILE);
        customers.remove(customer);
        customerDataBase.saveCustomers(customers,CUSTOMERS_FILE);
    }
}
