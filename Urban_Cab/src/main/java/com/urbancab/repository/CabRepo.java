package com.urbancab.repository;

import com.urbancab.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepo extends JpaRepository<Cab, Integer> {
}
