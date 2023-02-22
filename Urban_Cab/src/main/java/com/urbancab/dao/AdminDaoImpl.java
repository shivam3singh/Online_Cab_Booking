package com.urbancab.dao;

import com.urbancab.exception.CabException;
import com.urbancab.exception.TripBookingException;
import com.urbancab.exception.UserException;
import com.urbancab.exception.UserSessionException;
import com.urbancab.model.*;
import com.urbancab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AdminDaoImpl implements AdminDao{

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private CabRepo cabRepo;

    @Autowired
    private UserSessionRepo userSessionRepo;

    @Autowired
    private TripBookingRepo tripBookingRepo;

    @Override
    public Admin addNewAdmin(Admin admin) {

        List<String> adminUsernames = adminRepo.getAllUsernames();
        List<String> customerUsernames = customerRepo.getAllUsernames();
        List<String> driverUserNames = driverRepo.getAllUsernames();

        if (adminUsernames.contains(admin.getUsername()) || customerUsernames.contains(admin.getUsername()) || driverUserNames.contains(admin.getUsername())) throw new UserException("Username already exists.");

        List<String> adminMobileNumbers = adminRepo.getAllMobileNumbers();
        List<String> customerMobileNumbers = customerRepo.getAllMobileNumbers();
        List<String> driverMobileNumbers = driverRepo.getAllMobileNumbers();

        if (adminMobileNumbers.contains(admin.getMobileNumber()) || customerMobileNumbers.contains(admin.getMobileNumber()) || driverMobileNumbers.contains(admin.getMobileNumber())) throw new UserException("Mobile number already exists.");

        List<String> adminEmails = adminRepo.getAllEmails();
        List<String> customerEmails = customerRepo.getAllEmails();
        List<String> driverEmails = driverRepo.getAllEmails();

        if (adminEmails.contains(admin.getEmail()) || customerEmails.contains(admin.getEmail()) || driverEmails.contains(admin.getEmail())) throw new UserException("Email already exists.");


        return adminRepo.save(admin);
    }

    @Override
    public Admin getAdmin(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        Admin existingAdmin = adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        return existingAdmin;
    }

    @Override
    public Admin updateAdmin(String uniqueKey, Admin admin) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        Admin existingAdmin = adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());
        existingAdmin.setMobileNumber(admin.getMobileNumber());
        existingAdmin.setAddress(admin.getAddress());

        return adminRepo.save(existingAdmin);
    }

    @Override
    public String deleteAdmin(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        Admin existingAdmin = adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        adminRepo.delete(existingAdmin);

        return existingAdmin.getUsername() + " deleted successfully.";
    }

    @Override
    public List<TripBooking> getAllTrips(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        List<TripBooking> tripBookings = tripBookingRepo.findAll();
        if (tripBookings.isEmpty()){
            throw new TripBookingException("No trip booking found");
        }

        return tripBookings;
    }

    @Override
    public List<TripBooking> getTripsByCab(String uniqueKey, Integer cabId) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        Cab cab = cabRepo.findById(cabId).orElseThrow(() -> new CabException("No cab found with id: " + cabId));

        Driver driver = cab.getDriver();

        List<TripBooking> tripBookings = driver.getTrips();
        if (tripBookings.isEmpty()){
            throw new TripBookingException("No trip booking found for cab id: " + cabId);
        }

        return tripBookings;
    }

    @Override
    public List<TripBooking> getTripsByCustomer(String uniqueKey, Integer customerId) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        Customer customer= customerRepo.findById(customerId).orElseThrow(() -> new UserException("No customer found for customer id: " + customerId));

        List<TripBooking> tripBookings = customer.getTrips();

        if (tripBookings.isEmpty()){
            throw new TripBookingException("No trip booking found for customer id: " + customerId);
        }
        return tripBookings;
    }

    @Override
    public List<TripBooking> getTripsByDate(String uniqueKey, LocalDateTime date) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        List<TripBooking> tripBookings = tripBookingRepo.findByFromDateTime(date);
        if (tripBookings.isEmpty()){
            throw new TripBookingException("No Trip Booking Found On Date : " + date);
        }

        return tripBookings;
    }

    @Override
    public List<TripBooking> getAllTripsBetweenDays(String uniqueKey, LocalDateTime from_date, LocalDateTime to_date) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        List<TripBooking> tripBookings = tripBookingRepo.findByFromDateTimeBetween(from_date, from_date);

        if(tripBookings.isEmpty()){
            throw new TripBookingException("No trip booking found between " + from_date + " to " + to_date);
        }

        return tripBookings;
    }
}
