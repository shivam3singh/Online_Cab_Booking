package com.urbancab.repository;

import com.urbancab.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CabRepo extends JpaRepository<Cab, Integer> {

    public Optional<List<Cab>> findByCabType(String cabType);

    @Query("select c from Cab c where c.available = true")
    public List<Cab> findByAvailablity();

    @Query("select COUNT(c) from Cab c where c.cabType = ?1")
    public Integer countCabByType(String cabType);
}
