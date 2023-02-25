package com.urbancab.controller;

import com.urbancab.model.Customer;
import com.urbancab.model.TripBooking;
import com.urbancab.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/urban_cab/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public Customer addNewCustomer(@Valid @RequestBody Customer customer) {
        return customerService.addNewCustomer(customer);
    }

    @GetMapping("/customer/{uniquekey}")
    public Customer getCustomer(@PathVariable("uniquekey") String uniqueKey) {
        return customerService.getCustomer(uniqueKey);
    }

    @PutMapping("/customer/{uniquekey}")
    public Customer updateCustomer(@PathVariable("uniquekey") String uniqueKey, @Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(uniqueKey, customer);
    }

    @DeleteMapping("/customer/{uniquekey}")
    public String deleteCustomer(@PathVariable("uniquekey") String uniqueKey) {
        return customerService.deleteCustomer(uniqueKey);
    }

    @GetMapping("/customer/{uniquekey}/alltrips")
    public List<TripBooking> getTripsByCustomer(@PathVariable("uniquekey") String uniqueKey) {
        return customerService.getTripsByCustomer(uniqueKey);
    }

}
