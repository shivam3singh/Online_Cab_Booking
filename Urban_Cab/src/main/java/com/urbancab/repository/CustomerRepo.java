package com.urbancab.repository;

import com.urbancab.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    public Optional<Customer> findByUsername(String username);

    @Query("select c.username from Customer c")
    public List<String> getAllUsernames();

    @Query("select c.mobileNumber from Customer c")
    public List<String> getAllMobileNumbers();

    @Query("select c.email from Customer c")
    public List<String> getAllEmails();
}
