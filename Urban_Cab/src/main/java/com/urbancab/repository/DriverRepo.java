package com.urbancab.repository;

import com.urbancab.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

    public Optional<Driver> findByUsername(String username);

    Optional<List<Driver>> findByAvailable(Boolean available);

    @Query("select d.username from Driver d")
    public List<String> getAllUsernames();

    @Query("select d.mobileNumber from Driver d")
    public List<String> getAllMobileNumbers();

    @Query("select d.email from Driver d")
    public List<String> getAllEmails();

    @Query("from Driver d where d.rating>=4.5")
    public Optional<List<Driver>> getBestDriver();
}
