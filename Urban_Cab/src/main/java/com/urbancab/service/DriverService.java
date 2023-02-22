package com.urbancab.service;

import java.util.List;

import com.urbancab.exception.DriverException;
import com.urbancab.model.Driver;


public interface DriverService {

	public Driver insertDriver(Driver driver) throws DriverException;
	
	public Driver updateDriver(Driver driver) throws DriverException;
	
	public Driver deleteDriver(Integer driverId) throws DriverException;
	
	public List<Driver> viewBestDriver()throws DriverException;
	
	public Driver viewDriver(Integer driverId)throws DriverException;
}
