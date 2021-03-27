package org.example.DataBase;

import org.example.domain.Customer;

import java.util.List;

public interface CustomerDataBase {

    void saveCustomers(List<Customer> customers, String filename);

    List<Customer> readCustomers(String filename);

}
