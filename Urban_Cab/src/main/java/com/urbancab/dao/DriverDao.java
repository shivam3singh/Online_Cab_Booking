package com.urbancab.Dao;

import java.util.List;

import com.urbancab.exception.DriverException;
import com.urbancab.model.Driver;



public interface DriverDao {

	public Driver insertADriver(Driver driver) throws DriverException;
	
	public Driver updateADriver(Driver driver) throws DriverException;
	
	public Driver deleteADriver(Integer driverId) throws DriverException;
	
	public List<Driver> viewAllBestDriver()throws DriverException;
	
	public Driver viewADriver(Integer driverId)throws DriverException;
}
