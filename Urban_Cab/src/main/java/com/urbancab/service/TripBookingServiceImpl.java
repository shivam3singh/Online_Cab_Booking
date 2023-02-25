package com.urbancab.service;

import com.urbancab.dao.TripBookingDao;
import com.urbancab.model.TripBooking;
import com.urbancab.model.TripBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripBookingServiceImpl implements TripBookingService{
    @Autowired
    private TripBookingDao tripBookingDao;

    @Override
    public TripBooking addNewTripBooking(String uniqueKey, TripBooking tripBooking) {
        return tripBookingDao.addNewTripBooking(uniqueKey, tripBooking);
    }

    @Override
    public TripBooking updateTripBooking(String uniqueKey, TripBookingDTO tripBookingDTO) {
        return tripBookingDao.updateTripBooking(uniqueKey, tripBookingDTO);
    }

    @Override
    public String deleteTripBooking(String uniqueKey, Integer tripBookingId) {
        return tripBookingDao.deleteTripBooking(uniqueKey, tripBookingId);
    }

    @Override
    public List<TripBooking> ViewAllTripsByCustomer(String uniqueKey, Integer customerId) {
        return tripBookingDao.ViewAllTripsByCustomer(uniqueKey, customerId);
    }

    @Override
    public TripBooking calculateTripBill(String uniqueKey, Integer tripBookingId) {
        return tripBookingDao.calculateTripBill(uniqueKey, tripBookingId);
    }
}
