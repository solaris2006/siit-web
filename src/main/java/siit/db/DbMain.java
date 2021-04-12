package siit.db;

import siit.model.Customer;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class DbMain {

    public static void main(String[] args) {

        CustomerDao customerDao = new CustomerDao();

        Customer customer = new Customer("Generated " + Math.random());
        customer.setEmail("a@b.c");
        customer.setPhone("+412324242");
        customer.setBirthDate(LocalDate.of(1980, Month.FEBRUARY, 20));
        customerDao.addCustomer(customer);

        List<Customer> allCustomers = customerDao.getCustomersByName("Generated%");

        for (Customer allCustomer : allCustomers) {
            System.out.println("id is " + allCustomer.getId() + " and name " + allCustomer.getName());
        }
    }

    //Add id and name fields in Customer class
    //Use getters in DbMain to print on console
    //Place code that was in DbMain before in CustomerDao class
    //Change code to create Customer instance instead of printing on console

}
