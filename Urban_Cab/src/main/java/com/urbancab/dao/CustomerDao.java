package com.urbancab.dao;

import com.urbancab.exception.CustomerException;
import com.urbancab.model.Customer;
import com.urbancab.model.TripBooking;

import java.util.List;

public interface CustomerDao {
    public Customer addNewCustomer(Customer customer) throws CustomerException;
    public Customer getCustomer(String uniqueKey);
    public Customer updateCustomer(String uniqueKey,String oldPassword,Customer customer)throws CustomerException;
    public String deleteCustomer(String uniqueKey);
    public List<TripBooking> getTripsByCustomer(String uniqueKey);
}
