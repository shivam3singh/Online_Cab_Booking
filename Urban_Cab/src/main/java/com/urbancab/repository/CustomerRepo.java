package com.urbancab.repository;

import com.urbancab.model.Customer;
import com.urbancab.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    

    @Query("select c.username from Customer c")
    public List<String> getAllUsernames();

    @Query("select c.mobileNumber from Customer c")
    public List<String> getAllMobileNumbers();

    @Query("select c.email from Customer c")
    public List<String> getAllEmails();

    @Query("select c.trips from Customer c where c.customerId = ?1")
    public Optional<List<TripBooking>> findAllTripsOfCustomer(Integer customerId);
    
    public Optional<Customer> findByUsername(String username);
}
