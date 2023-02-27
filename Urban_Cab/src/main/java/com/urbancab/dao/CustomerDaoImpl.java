package com.urbancab.dao;

import com.urbancab.exception.CustomerException;
import com.urbancab.exception.TripBookingException;
import com.urbancab.exception.UserException;
import com.urbancab.exception.UserSessionException;
import com.urbancab.model.Admin;
import com.urbancab.model.Customer;
import com.urbancab.model.TripBooking;
import com.urbancab.model.UserSession;
import com.urbancab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDaoImpl implements CustomerDao{
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private UserSessionRepo userSessionRepo;

    @Autowired
    private TripBookingRepo tripBookingRepo;

    @Override
    public Customer addNewCustomer(Customer customer) throws CustomerException{
        List<String> adminUsernames = adminRepo.getAllUsernames();
        List<String> customerUsernames = customerRepo.getAllUsernames();
        List<String> driverUserNames = driverRepo.getAllUsernames();

        if (adminUsernames.contains(customer.getUsername()) || customerUsernames.contains(customer.getUsername()) || driverUserNames.contains(customer.getUsername())) throw new CustomerException("Username already exists.");

        List<String> adminMobileNumbers = adminRepo.getAllMobileNumbers();
        List<String> customerMobileNumbers = customerRepo.getAllMobileNumbers();
        List<String> driverMobileNumbers = driverRepo.getAllMobileNumbers();

        if (adminMobileNumbers.contains(customer.getMobileNumber()) || customerMobileNumbers.contains(customer.getMobileNumber()) || driverMobileNumbers.contains(customer.getMobileNumber())) throw new CustomerException("Mobile number already exists.");

        List<String> adminEmails = adminRepo.getAllEmails();
        List<String> customerEmails = customerRepo.getAllEmails();
        List<String> driverEmails = driverRepo.getAllEmails();

        if (adminEmails.contains(customer.getEmail()) || customerEmails.contains(customer.getEmail()) || driverEmails.contains(customer.getEmail())) throw new CustomerException("Email already exists.");

        return customerRepo.save(customer);
    }

    @Override
    public Customer getCustomer(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Customer not logged in."));

        return customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("No customer found with userName: " + userSession.getUserName()));
    }

    @Override
    public Customer updateCustomer(String uniqueKey,String oldPassword, Customer customer) throws CustomerException{
    	
    	UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Customer not logged in or Invali key.."));

        Customer c= customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("No customer found with userName: " + userSession.getUserName()));
      
        if(!c.getPassword().equals(oldPassword)) throw new CustomerException("Incorrect password.");
        c.setAddress(customer.getAddress());

        c.setEmail(customer.getEmail());
        
        c.setMobileNumber(customer.getMobileNumber());
        
        c.setPassword(customer.getPassword());
        
        c.setUsername(customer.getUsername());
        
        return customerRepo.save(c);
     
    }

    @Override
    public String deleteCustomer(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Customer not logged in or Invali key..."));

        Customer c= customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("No customer found with userName: " + userSession.getUserName()));

        customerRepo.delete(c);

        return "Customer with username " + c.getUsername() + " deleted successfully.";
    }

    @Override
    public List<TripBooking> getTripsByCustomer(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Customer not logged in or Invali key..."));

        Customer c= customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("No customer found with userName: " + userSession.getUserName()));

        if(c.getTrips().size()==0) throw new TripBookingException("No trip booking found");
        return c.getTrips();
    }
}
