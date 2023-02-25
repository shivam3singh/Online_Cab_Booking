package com.urbancab.dao;

import com.urbancab.exception.CabException;
import com.urbancab.exception.UserException;
import com.urbancab.exception.UserSessionException;
import com.urbancab.model.Admin;
import com.urbancab.model.Cab;
import com.urbancab.model.Driver;
import com.urbancab.model.UserSession;
import com.urbancab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DriverDaoImpl implements DriverDao{

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

    @Override
    public Driver insertADriver(Driver driver) {

        List<String> adminUsernames = adminRepo.getAllUsernames();
        List<String> customerUsernames = customerRepo.getAllUsernames();
        List<String> driverUserNames = driverRepo.getAllUsernames();

        if (adminUsernames.contains(driver.getUsername()) || customerUsernames.contains(driver.getUsername()) || driverUserNames.contains(driver.getUsername())) throw new UserException("Username already exists.");

        List<String> adminMobileNumbers = adminRepo.getAllMobileNumbers();
        List<String> customerMobileNumbers = customerRepo.getAllMobileNumbers();
        List<String> driverMobileNumbers = driverRepo.getAllMobileNumbers();

        if (adminMobileNumbers.contains(driver.getMobileNumber()) || customerMobileNumbers.contains(driver.getMobileNumber()) || driverMobileNumbers.contains(driver.getMobileNumber())) throw new UserException("Mobile number already exists.");

        List<String> adminEmails = adminRepo.getAllEmails();
        List<String> customerEmails = customerRepo.getAllEmails();
        List<String> driverEmails = driverRepo.getAllEmails();

        if (adminEmails.contains(driver.getEmail()) || customerEmails.contains(driver.getEmail()) || driverEmails.contains(driver.getEmail())) throw new UserException("Email already exists.");

        List<Cab> cabs = cabRepo.findByAvailablity();

        if (cabs.size() == 0) {
            throw new CabException("No cab available to allocate this driver");
        }

        Cab cab = cabs.get(0);

        driver.setCab(cab);

        cab.setDriver(driver);
        
        cab.setAvailable(false);

        return driverRepo.save(driver);
    }

    @Override
    public Driver updateADriver(String uniqueKey, Driver driver) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        Optional<Admin> admin = adminRepo.findById(userSession.getUserId());

        Optional<Driver> optionalDriver = driverRepo.findById(driver.getDriverId());

        if (admin.isEmpty() && optionalDriver.isEmpty()) throw new UserException("No user found for id: " + driver.getDriverId());

        if (admin.isEmpty()) throw new UserException("No driver found for id: " + driver.getDriverId());

        Driver existingDriver = optionalDriver.get();

        if (existingDriver.getPassword() != existingDriver.getPassword()) throw new UserException("Incorrect password entered.");

        List<String> adminUsernames = adminRepo.getAllUsernames();
        List<String> customerUsernames = customerRepo.getAllUsernames();

        if (adminUsernames.contains(driver.getUsername()) || customerUsernames.contains(driver.getUsername())) throw new UserException("Username already exists.");

        List<String> adminMobileNumbers = adminRepo.getAllMobileNumbers();
        List<String> customerMobileNumbers = customerRepo.getAllMobileNumbers();

        if (adminMobileNumbers.contains(driver.getMobileNumber()) || customerMobileNumbers.contains(driver.getMobileNumber())) throw new UserException("Mobile number already exists.");

        List<String> adminEmails = adminRepo.getAllEmails();
        List<String> customerEmails = customerRepo.getAllEmails();

        if (adminEmails.contains(driver.getEmail()) || customerEmails.contains(driver.getEmail())) throw new UserException("Email already exists.");

        existingDriver.setEmail(driver.getEmail());
        existingDriver.setMobileNumber(driver.getMobileNumber());
        existingDriver.setUsername(driver.getUsername());
        existingDriver.setAddress(driver.getAddress());
        existingDriver.setLicenceNo(driver.getLicenceNo());
        existingDriver.setRating(driver.getRating());

        return driverRepo.save(existingDriver);
    }

    @Override
    public String deleteADriver(String uniqueKey, Integer driverId) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        Optional<Admin> admin = adminRepo.findById(userSession.getUserId());

        Optional<Driver> optionalDriver = driverRepo.findById(driverId);

        if (admin.isEmpty() && optionalDriver.isEmpty()) throw new UserException("No user found for id: " + driverId);

        if (optionalDriver.isEmpty()) throw new UserException("No driver found for id: " + driverId);

        Driver existingDriver = optionalDriver.get();

        Cab cab = existingDriver.getCab();
        cab.setAvailable(true);

        driverRepo.delete(existingDriver);

        return "Driver with username " + existingDriver.getUsername() + " deleted successfully.";
    }

    @Override
    public List<Driver> viewAllBestDriver(String uniqueKey) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("Admin not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        return driverRepo.getBestDriver().orElseThrow(() -> new UserException("No best driver found."));
    }

    @Override
    public Driver viewADriver(String uniqueKey, Integer driverId) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        return driverRepo.findById(driverId).orElseThrow(() -> new UserException("No driver found for id: " + driverId));
    }
}
