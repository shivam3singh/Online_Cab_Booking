package com.urbancab.dao;

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
    public Customer addNewCustomer(Customer customer) {
        List<String> adminUsernames = adminRepo.getAllUsernames();
        List<String> customerUsernames = customerRepo.getAllUsernames();
        List<String> driverUserNames = driverRepo.getAllUsernames();

        if (adminUsernames.contains(customer.getUsername()) || customerUsernames.contains(customer.getUsername()) || driverUserNames.contains(customer.getUsername())) throw new UserException("Username already exists.");

        List<String> adminMobileNumbers = adminRepo.getAllMobileNumbers();
        List<String> customerMobileNumbers = customerRepo.getAllMobileNumbers();
        List<String> driverMobileNumbers = driverRepo.getAllMobileNumbers();

        if (adminMobileNumbers.contains(customer.getMobileNumber()) || customerMobileNumbers.contains(customer.getMobileNumber()) || driverMobileNumbers.contains(customer.getMobileNumber())) throw new UserException("Mobile number already exists.");

        List<String> adminEmails = adminRepo.getAllEmails();
        List<String> customerEmails = customerRepo.getAllEmails();
        List<String> driverEmails = driverRepo.getAllEmails();

        if (adminEmails.contains(customer.getEmail()) || customerEmails.contains(customer.getEmail()) || driverEmails.contains(customer.getEmail())) throw new UserException("Email already exists.");

        return customerRepo.save(customer);
    }

    @Override
    public Customer getCustomer(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        return customerRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("No customer found with id: " + userSession.getUserId()));
    }

    @Override
    public Customer updateCustomer(String uniqueKey, Customer customer) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        Customer existingCustomer = customerRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("No customer found with id: " + userSession.getUserId()));

        if (!existingCustomer.getPassword().equals(customer.getPassword())) throw new UserException("Incorrect password entered.");

        List<String> adminUsernames = adminRepo.getAllUsernames();
        List<String> driverUserNames = driverRepo.getAllUsernames();

        if (adminUsernames.contains(customer.getUsername()) || driverUserNames.contains(customer.getUsername())) throw new UserException("Username already exists.");

        List<String> adminMobileNumbers = adminRepo.getAllMobileNumbers();
        List<String> driverMobileNumbers = driverRepo.getAllMobileNumbers();

        if (adminMobileNumbers.contains(customer.getMobileNumber()) || driverMobileNumbers.contains(customer.getMobileNumber())) throw new UserException("Mobile number already exists.");

        List<String> adminEmails = adminRepo.getAllEmails();
        List<String> driverEmails = driverRepo.getAllEmails();

        if (adminEmails.contains(customer.getEmail()) || driverEmails.contains(customer.getEmail())) throw new UserException("Email already exists.");

        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setUsername(customer.getUsername());
        existingCustomer.setMobileNumber(customer.getMobileNumber());

        userSession.setUserName(customer.getUsername());

        userSessionRepo.save(userSession);

        return customerRepo.save(existingCustomer);
    }

    @Override
    public String deleteCustomer(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Customer not logged in."));

        Customer existingCustomer = customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("No customer found with username: " + userSession.getUserName()));

        existingCustomer.setTrips(null);

        customerRepo.delete(existingCustomer);

        return "Customer with username " + existingCustomer.getUsername() + " deleted successfully.";
    }

    @Override
    public List<TripBooking> getTripsByCustomer(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        customerRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("No customer found with id: " + userSession.getUserId()));

        return customerRepo.findAllTripsOfCustomer(userSession.getUserId()).orElseThrow(() -> new TripBookingException("No trip booking found"));
    }
}
