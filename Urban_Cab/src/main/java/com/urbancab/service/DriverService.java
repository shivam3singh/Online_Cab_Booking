package com.urbancab.service;

import com.urbancab.model.Driver;

import java.util.List;

public interface DriverService {
    public Driver insertADriver(Driver driver);

    public Driver updateADriver(String uniqueKey, Driver driver);

    public String deleteADriver(String uniqueKey, Integer driverId);

    public List<Driver> viewAllBestDriver(String uniqueKey);
    public Driver viewADriver(String uniqueKey, Integer driverId);
}
