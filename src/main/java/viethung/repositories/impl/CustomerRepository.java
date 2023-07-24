package viethung.repositories.impl;


import viethung.models.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer insert(Customer customer);
    Customer update(Customer customer, String customerId);
    Customer delete(String customerId);
    List<Customer> getAll();
    Customer getById(String customerId);
    Customer getByCode(String customerCode);
}
