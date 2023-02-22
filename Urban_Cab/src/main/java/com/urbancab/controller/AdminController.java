package com.urbancab.controller;

import com.urbancab.model.Admin;
import com.urbancab.model.TripBooking;
import com.urbancab.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController("urban_cab/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/")
    public Admin addNewAdmin(@Valid @RequestBody Admin admin){
        return adminService.addNewAdmin(admin);
    }

    @GetMapping("/{uniquekey}")
    public Admin getAdmin(@PathVariable("uniquekey") String uniqueKey) {
        return adminService.getAdmin(uniqueKey);
    }


    @PutMapping("/{uniquekey}")
    public Admin updateAdmin(@PathVariable("uniquekey") String uniqueKey,@Valid @RequestBody Admin admin){
        return adminService.updateAdmin(uniqueKey, admin);
    }

    @DeleteMapping("/{uniquekey}")
    public String deleteAdmin(@PathVariable("uniquekey") String uniqueKey){
        return adminService.deleteAdmin(uniqueKey);
    }

    @GetMapping("/{uniquekey}/trips")
    public List<TripBooking> getAllTrips(@PathVariable("uniquekey") String uniqueKey){
        return adminService.getAllTrips(uniqueKey);
    }

    @GetMapping("/{uniquekey}/trips/cabs")
    public List<TripBooking> getTripsByCab(@PathVariable("uniquekey") String uniqueKey, @RequestParam("cabId") Integer cabId){
        return adminService.getTripsByCab(uniqueKey, cabId);
    }

    @GetMapping("/{uniquekey}/trips/customer")
    public List<TripBooking> getTripsByCustomer(@PathVariable("uniquekey") String uniqueKey, @RequestParam("customerId") Integer customerId){
        return adminService.getTripsByCustomer(uniqueKey, customerId);
    }

    @GetMapping("/{uniquekey}/trips/date")
    public List<TripBooking> getTripsByDate(@PathVariable("uniquekey") String uniqueKey, @RequestParam("datetime") LocalDateTime date){
        return adminService.getTripsByDate(uniqueKey, date);
    }

    @GetMapping("/{uniquekey}/trips/dates")
    public List<TripBooking> getAllTripsBetweenDays(@PathVariable("uniquekey") String uniqueKey, @RequestParam("fromdatetime")LocalDateTime from_date, @RequestParam("todatetime")LocalDateTime to_date){
        return adminService.getAllTripsBetweenDays(uniqueKey, from_date, to_date);
    }
}
