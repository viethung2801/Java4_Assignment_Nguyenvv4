package viethung.utilities;


import viethung.models.Customer;
import viethung.repositories.CustomerRepositoryImpl;
import viethung.repositories.impl.CustomerRepository;

public class CustomerUltil {
    public static String validateInsert(Customer customer) {
        CustomerRepository customerRepo = new CustomerRepositoryImpl();
        if ( customer.getCode() == null || customer.getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if ( customer.getFirstName() == null || customer.getFirstName().trim().equals("")) {
            return "Fail! First Name is empty";
        }
        if ( customer.getAddress() == null || customer.getAddress().trim().equals("")) {
            return "Fail! Address is empty";
        }
        if ( customer.getPhoneNumber() == null || customer.getPhoneNumber().trim().equals("")) {
            return "Fail! Phone Number is empty";
        }
        if ( customer.getPassword() == null || customer.getPassword().trim().equals("")) {
            return "Fail! Password is empty";
        }
        if (customerRepo.getByCode(customer.getCode()) != null) {
            return "Fail! Code is exist";
        }
        return null;
    }
    public static String validateUpdate(Customer customer) {
        CustomerRepository customerRepo = new CustomerRepositoryImpl();
        if ( customer.getCode() == null || customer.getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if ( customer.getFirstName() == null || customer.getFirstName().trim().equals("")) {
            return "Fail! First Name is empty";
        }
        if ( customer.getAddress() == null || customer.getAddress().trim().equals("")) {
            return "Fail! Address is empty";
        }
        if ( customer.getPhoneNumber() == null || customer.getPhoneNumber().trim().equals("")) {
            return "Fail! Phone Number is empty";
        }
        if ( customer.getPassword() == null || customer.getPassword().trim().equals("")) {
            return "Fail! Password is empty";
        }
        return null;
    }
}
