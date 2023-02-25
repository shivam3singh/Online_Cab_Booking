package com.urbancab.controller;

import com.urbancab.model.Admin;
import com.urbancab.model.TripBooking;
import com.urbancab.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController("urban_cab/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/")
    public ResponseEntity<Admin> addNewAdmin(@Valid @RequestBody Admin admin){
        return new ResponseEntity<>(adminService.addNewAdmin(admin), HttpStatus.CREATED);
    }

    @GetMapping("/{uniquekey}")
    public ResponseEntity<Admin> getAdmin(@PathVariable("uniquekey") String uniqueKey) {
        return new ResponseEntity<>(adminService.getAdmin(uniqueKey), HttpStatus.FOUND);
    }


    @PutMapping("/{uniquekey}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("uniquekey") String uniqueKey,@Valid @RequestBody Admin admin){
        return new ResponseEntity<>(adminService.updateAdmin(uniqueKey, admin), HttpStatus.CREATED);
    }

    @DeleteMapping("/{uniquekey}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("uniquekey") String uniqueKey){
        return new ResponseEntity<>(adminService.deleteAdmin(uniqueKey), HttpStatus.OK);
    }

    @GetMapping("/{uniquekey}/trips")
    public ResponseEntity<List<TripBooking>> getAllTrips(@PathVariable("uniquekey") String uniqueKey){
        return new ResponseEntity<>(adminService.getAllTrips(uniqueKey), HttpStatus.FOUND);
    }

    @GetMapping("/{uniquekey}/trips/cabs")
    public ResponseEntity<List<TripBooking>> getTripsByCab(@PathVariable("uniquekey") String uniqueKey, @RequestParam("cabId") Integer cabId){
        return new ResponseEntity<>(adminService.getTripsByCab(uniqueKey, cabId), HttpStatus.FOUND);
    }

    @GetMapping("/{uniquekey}/trips/customer")
    public ResponseEntity<List<TripBooking>> getTripsByCustomer(@PathVariable("uniquekey") String uniqueKey, @RequestParam("customerId") Integer customerId){
        return new ResponseEntity<>(adminService.getTripsByCustomer(uniqueKey, customerId), HttpStatus.FOUND);
    }

    @GetMapping("/{uniquekey}/trips/date")
    public ResponseEntity<List<TripBooking>> getTripsByDate(@PathVariable("uniquekey") String uniqueKey, @RequestParam("datetime") LocalDateTime date){
        return new ResponseEntity<>(adminService.getTripsByDate(uniqueKey, date), HttpStatus.FOUND);
    }

    @GetMapping("/{uniquekey}/trips/dates")
    public ResponseEntity<List<TripBooking>> getAllTripsBetweenDays(@PathVariable("uniquekey") String uniqueKey, @RequestParam("fromdatetime")LocalDateTime from_date, @RequestParam("todatetime")LocalDateTime to_date){
        return new ResponseEntity<>(adminService.getAllTripsBetweenDays(uniqueKey, from_date, to_date), HttpStatus.FOUND);
    }
}
