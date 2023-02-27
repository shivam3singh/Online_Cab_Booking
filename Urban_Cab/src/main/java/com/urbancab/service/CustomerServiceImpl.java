package com.urbancab.service;

import com.urbancab.dao.CustomerDao;
import com.urbancab.exception.CustomerException;
import com.urbancab.model.Customer;
import com.urbancab.model.TripBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer addNewCustomer(Customer customer) throws CustomerException {
        return customerDao.addNewCustomer(customer);
    }

    @Override
    public Customer getCustomer(String uniqueKey) {
        return customerDao.getCustomer(uniqueKey);
    }

    @Override
    public Customer updateCustomer(String uniqueKey,String oldPassword ,Customer customer) throws CustomerException{
        return customerDao.updateCustomer(uniqueKey, oldPassword,customer);
    }

    @Override
    public String deleteCustomer(String uniqueKey) {
        return customerDao.deleteCustomer(uniqueKey);
    }

    @Override
    public List<TripBooking> getTripsByCustomer(String uniqueKey) {
        return customerDao.getTripsByCustomer(uniqueKey);
    }
}
