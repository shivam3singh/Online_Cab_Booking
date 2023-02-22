package com.urbancab.repository;

import com.urbancab.exception.DriverException;
import com.urbancab.model.Driver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {
	
	@Query("from Driver d where d.rating>=4.5")
	public List<Driver> getBestDriver() throws DriverException;
}
