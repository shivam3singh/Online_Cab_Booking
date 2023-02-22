package com.urbancab.repository;

import com.urbancab.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabRepo extends JpaRepository<Cab, Integer> {
}
