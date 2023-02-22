package com.urbancab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbancab.Dao.DriverDao;
import com.urbancab.exception.DriverException;
import com.urbancab.model.Driver;

@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverDao ddao;
	
	@Override
	public Driver insertDriver(Driver driver) throws DriverException {
		// TODO Auto-generated method stub
		return ddao.insertADriver(driver);
	}

	@Override
	public Driver updateDriver(Driver driver) throws DriverException {
		// TODO Auto-generated method stub
		return ddao.updateADriver(driver);
	}

	@Override
	public Driver deleteDriver(Integer driverId) throws DriverException {
		// TODO Auto-generated method stub
		return ddao.deleteADriver(driverId);
	}

	@Override
	public List<Driver> viewBestDriver() throws DriverException {
		// TODO Auto-generated method stub
		return ddao.viewAllBestDriver();
	}

	@Override
	public Driver viewDriver(Integer driverId) throws DriverException {
		// TODO Auto-generated method stub
		return ddao.viewADriver(driverId);
	}

	

}
