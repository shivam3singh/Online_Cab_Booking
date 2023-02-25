package com.urbancab.dao;

import com.urbancab.model.TripBooking;
import com.urbancab.model.TripBookingDTO;

import java.util.List;

public interface TripBookingDao {

    public TripBooking addNewTripBooking (String uniqueKey, TripBooking tripBooking);

    public TripBooking updateTripBooking (String uniqueKey, TripBookingDTO tripBookingDTO);

    public String deleteTripBooking (String uniqueKey, Integer tripBookingId);

    public List<TripBooking> ViewAllTripsByCustomer (String uniqueKey, Integer customerId);

    public TripBooking calculateTripBill (String uniqueKey, Integer tripBookingId);
}
