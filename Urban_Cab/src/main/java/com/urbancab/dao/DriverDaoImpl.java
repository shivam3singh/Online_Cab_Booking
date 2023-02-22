package com.urbancab.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbancab.exception.DriverException;
import com.urbancab.model.Driver;
import com.urbancab.repository.DriverRepo;


@Service
public class DriverDaoImpl implements DriverDao{

	@Autowired
	private DriverRepo drepo;
	
	@Override
	public Driver insertADriver(Driver driver) throws DriverException {
		// TODO Auto-generated method stub
		Driver newdriver=drepo.save(driver);
		if(newdriver!=null) {
			return newdriver;
		}
		throw new DriverException("Driver Not saved!");
	}

	@Override
	public Driver updateADriver(Driver driver) throws DriverException {
		// TODO Auto-generated method stub
		try {
			Driver existingDriver=viewADriver(driver.getDriverId());
			existingDriver=driver;
			Driver updatedDriver=drepo.save(existingDriver);
			return updatedDriver;
		} catch (Exception e) {
			// TODO: handle exception
			throw new DriverException(e.getMessage());
		}
	}

	@Override
	public Driver deleteADriver(Integer driverId) throws DriverException {
		Driver existingDriver=viewADriver(driverId);
		drepo.delete(existingDriver);
		return existingDriver;
	}

	@Override
	public List<Driver> viewAllBestDriver() throws DriverException {
		List<Driver> bestDrivers=drepo.getBestDriver();
		if(bestDrivers.size()>0) {
			return bestDrivers;
		}
		throw new DriverException("No Best Driver Found!!");
	}

	@Override
	public Driver viewADriver(Integer driverId) throws DriverException {
		// TODO Auto-generated method stub
		Optional<Driver>opt=drepo.findById(driverId);
		if(opt.isPresent()) {
			Driver existingDriver=opt.get();
			return existingDriver;
		}
		throw new DriverException("Driver Not present with id "+driverId);
	}

}
