package com.urbancab.service;

import com.urbancab.dao.AdminDao;
import com.urbancab.model.Admin;
import com.urbancab.model.TripBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin addNewAdmin(Admin admin) {
        return adminDao.addNewAdmin(admin);
    }

    @Override
    public Admin getAdmin(String uniqueKey){
        return adminDao.getAdmin(uniqueKey);
    }

    @Override
    public Admin updateAdmin(String uniqueKey, Admin admin) {
        return adminDao.updateAdmin(uniqueKey, admin);
    }

    @Override
    public String deleteAdmin(String uniqueKey) {
        return adminDao.deleteAdmin(uniqueKey);
    }

    @Override
    public List<TripBooking> getAllTrips(String uniqueKey) {
        return adminDao.getAllTrips(uniqueKey);
    }

    @Override
    public List<TripBooking> getTripsByCab(String uniqueKey, Integer cabId) {
        return adminDao.getTripsByCab(uniqueKey, cabId);
    }

    @Override
    public List<TripBooking> getTripsByCustomer(String uniqueKey, Integer customerId) {
        return adminDao.getTripsByCustomer(uniqueKey, customerId);
    }

    @Override
    public List<TripBooking> getTripsByDate(String uniqueKey, LocalDateTime date) {
        return adminDao.getTripsByDate(uniqueKey, date);
    }

    @Override
    public List<TripBooking> getAllTripsBetweenDays(String uniqueKey, LocalDateTime from_date, LocalDateTime to_date) {
        return adminDao.getAllTripsBetweenDays(uniqueKey, from_date, to_date);
    }
}
