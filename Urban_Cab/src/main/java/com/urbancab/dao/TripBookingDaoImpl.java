package com.urbancab.dao;

import com.urbancab.exception.TripBookingException;
import com.urbancab.exception.UserException;
import com.urbancab.exception.UserSessionException;
import com.urbancab.model.*;
import com.urbancab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripBookingDaoImpl implements TripBookingDao{

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
    public TripBooking addNewTripBooking(String uniqueKey, TripBooking tripBooking) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        Customer customer = customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("Customer not found with username: " + userSession.getUserName()));

        tripBooking.setCustomer(customer);

        List<Driver> drivers = driverRepo.findByAvailable(true).orElseThrow(() -> new UserException("No driver available for this booking"));

        Driver driver = drivers.get(0);

        tripBooking.setStatus(true);
        tripBooking.setDriver(driver);
        tripBooking.setBill(tripBooking.getDistanceInKm() * driver.getCab().getPerKmRate());

        driver.setAvailable(false);
        driver.getTrips().add(tripBooking);

        return tripBookingRepo.save(tripBooking);
    }

    @Override
    public TripBooking updateTripBooking(String uniqueKey, TripBookingDTO tripBookingDTO) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        Customer customer = customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("Customer not found with username: " + userSession.getUserName()));

        TripBooking existingTripBooking = tripBookingRepo.findById(tripBookingDTO.getTripBookingId()).orElseThrow(() -> new TripBookingException("No trip booking found for id: " + tripBookingDTO.getTripBookingId()));

        existingTripBooking.setFromLocation(tripBookingDTO.getFromLocation());
        existingTripBooking.setToLocation(tripBookingDTO.getToLocation());
        existingTripBooking.setFromDateTime(tripBookingDTO.getFromDateTime());
        existingTripBooking.setToDateTime(tripBookingDTO.getToDateTime());
        existingTripBooking.setDistanceInKm(tripBookingDTO.getDistanceInKm());
        existingTripBooking.setBill(tripBookingDTO.getDistanceInKm() * existingTripBooking.getDriver().getCab().getPerKmRate());

        return tripBookingRepo.save(existingTripBooking);
    }

    @Override
    public String deleteTripBooking(String uniqueKey, Integer tripBookingId) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("Customer not found with username: " + userSession.getUserName()));

        TripBooking existingTripBooking = tripBookingRepo.findById(tripBookingId).orElseThrow(() -> new TripBookingException("No trip booking found for id: " + tripBookingId));

        tripBookingRepo.delete(existingTripBooking);

        return existingTripBooking + " deleted successfully.";
    }

    @Override
    public List<TripBooking> ViewAllTripsByCustomer(String uniqueKey, Integer customerId) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        Customer customer = customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("Customer not found with username: " + userSession.getUserName()));

        return tripBookingRepo.findByCustomer(customer).orElseThrow(() -> new TripBookingException("No trip found for customer username: " + customer.getUsername()));
    }

    @Override
    public TripBooking calculateTripBill(String uniqueKey, Integer tripBookingId) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        Customer customer = customerRepo.findByUsername(userSession.getUserName()).orElseThrow(() -> new UserException("Customer not found with username: " + userSession.getUserName()));

        TripBooking existingTripBooking = tripBookingRepo.findById(tripBookingId).orElseThrow(() -> new TripBookingException("No trip booking found for id: " + tripBookingId));

        Float distanceInKm = existingTripBooking.getDistanceInKm();

        existingTripBooking.setBill(distanceInKm * existingTripBooking.getDriver().getCab().getPerKmRate());

        return  tripBookingRepo.save(existingTripBooking);
    }
}
