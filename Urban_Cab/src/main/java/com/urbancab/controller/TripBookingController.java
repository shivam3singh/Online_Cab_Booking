package com.urbancab.controller;

import com.urbancab.model.TripBooking;
import com.urbancab.model.TripBookingDTO;
import com.urbancab.service.TripBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urban_cab/customers")
public class TripBookingController {

    @Autowired
    private TripBookingService tripBookingService;

    @PostMapping("/{uniquekey}")
    public ResponseEntity<TripBooking> addNewTripBooking (@PathVariable("uniquekey") String uniqueKey, @Valid @RequestBody TripBooking tripBooking){
        return new ResponseEntity<>(tripBookingService.addNewTripBooking(uniqueKey,tripBooking), HttpStatus.CREATED);
    }

    @PutMapping("/{uniquekey}")
    public ResponseEntity<TripBooking> updateTripBooking (@PathVariable("uniquekey") String uniqueKey, @Valid @RequestBody TripBookingDTO tripBookingDTO) {
        return new ResponseEntity<>(tripBookingService.updateTripBooking(uniqueKey, tripBookingDTO), HttpStatus.CREATED);
    }

//    @DeleteMapping("/{uniquekey}")
//    public ResponseEntity<String> deleteTripBooking (@PathVariable("uniquekey") String uniqueKey, @RequestParam("tripBookingId") Integer tripBookingId) {
//        return new ResponseEntity<>(tripBookingService.deleteTripBooking(uniqueKey, tripBookingId), HttpStatus.OK);
//    }

    @GetMapping("/{uniquekey}/trips")
    public ResponseEntity<List<TripBooking>> ViewAllTripsByCustomer (@PathVariable("uniquekey") String uniqueKey, @RequestParam("customerId") Integer customerId) {
        return new ResponseEntity<>(tripBookingService.ViewAllTripsByCustomer(uniqueKey, customerId), HttpStatus.OK);
    }

    @GetMapping("/{uniquekey}/trips/bill")
    public ResponseEntity<TripBooking> calculateTripBill (@PathVariable("uniquekey") String uniqueKey, @RequestParam("tripBookingId") Integer tripBookingId) {
        return new ResponseEntity<>(tripBookingService.calculateTripBill(uniqueKey, tripBookingId), HttpStatus.OK);
    }
}
