package com.urbancab.dao;

import com.urbancab.model.Admin;
import com.urbancab.model.TripBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminDao {
    public Admin addNewAdmin(Admin admin);
    public Admin getAdmin(String uniqueKey);
    public Admin updateAdmin(String uniqueKey, Admin admin);
    public String deleteAdmin(String uniqueKey);
    public List<TripBooking> getAllTrips(String uniqueKey);
    public List<TripBooking> getTripsByCab(String uniqueKey, Integer cabId);
    public List<TripBooking> getTripsByCustomer(String uniqueKey, Integer customerId);
    public List<TripBooking> getTripsByDate(String uniqueKey, LocalDateTime date);
    public List<TripBooking> getAllTripsBetweenDays(String uniqueKey, LocalDateTime from_date, LocalDateTime to_date);
}
