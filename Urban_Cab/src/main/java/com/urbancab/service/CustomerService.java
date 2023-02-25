package com.urbancab.service;

import com.urbancab.model.Customer;
import com.urbancab.model.TripBooking;

import java.util.List;

public interface CustomerService {

    public Customer addNewCustomer(Customer customer);
    public Customer getCustomer(String uniqueKey);
    public Customer updateCustomer(String uniqueKey, Customer customer);
    public String deleteCustomer(String uniqueKey);
    public List<TripBooking> getTripsByCustomer(String uniqueKey);
}
