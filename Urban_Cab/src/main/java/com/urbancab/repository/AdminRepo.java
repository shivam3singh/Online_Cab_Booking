package com.urbancab.repository;

import com.urbancab.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    public Optional<Admin> findByUsername(String username);

    public Optional<Admin> findByMobileNumber(String mobileNumber);

    public Optional<Admin> findByEmail(String email);

    @Query("select a.username from Admin a")
    public List<String> getAllUsernames();

    @Query("select a.mobileNumber from Admin a")
    public List<String> getAllMobileNumbers();

    @Query("select a.email from Admin a")
    public List<String> getAllEmails();
}
