package com.urbancab.repository;

import com.urbancab.model.Customer;
import com.urbancab.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripBookingRepo extends JpaRepository<TripBooking, Integer> {

    public Optional<List<TripBooking>> findByCustomer(Customer customer);

    public List<TripBooking> findByFromDateTime(LocalDateTime dateTime);

    public List<TripBooking> findByFromDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}
