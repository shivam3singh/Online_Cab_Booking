package com.urbancab.repository;

import com.urbancab.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripBookingRepo extends JpaRepository<TripBooking, Integer> {
}
