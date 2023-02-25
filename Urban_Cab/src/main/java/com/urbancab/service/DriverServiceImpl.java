package com.urbancab.service;

import com.urbancab.dao.DriverDao;
import com.urbancab.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverDao driverDao;

    @Override
    public Driver insertADriver(Driver driver) {
        return driverDao.insertADriver(driver);
    }

    @Override
    public Driver updateADriver(String uniqueKey, Driver driver) {
        return driverDao.updateADriver(uniqueKey, driver);
    }

    @Override
    public String deleteADriver(String uniqueKey, Integer driverId) {
        return driverDao.deleteADriver(uniqueKey, driverId);
    }

    @Override
    public List<Driver> viewAllBestDriver(String uniqueKey) {
        return driverDao.viewAllBestDriver(uniqueKey);
    }

    @Override
    public Driver viewADriver(String uniqueKey, Integer driverId) {
        return driverDao.viewADriver(uniqueKey, driverId);
    }
}
