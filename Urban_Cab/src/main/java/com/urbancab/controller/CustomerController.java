package com.urbancab.controller;

import com.urbancab.model.Customer;
import com.urbancab.model.TripBooking;
import com.urbancab.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/urban_cab/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    
  //Save a Customer -> http://localhost:8888/swagger-ui/index.html#/customer-controller/addNewCustomer
    @PostMapping("/customer")
    public ResponseEntity<Customer> addNewCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.addNewCustomer(customer), HttpStatus.CREATED);
    }

    
    //get Customer -> http://localhost:8888/swagger-ui/index.html#/customer-controller/getCustomer
    @GetMapping("/customer/{uniquekey}")
    public Customer getCustomer(@PathVariable("uniquekey") String uniqueKey) {
        return customerService.getCustomer(uniqueKey);
    }

    //update Customer -> http://localhost:8888/swagger-ui/index.html#/customer-controller/updateCustomer
    @PutMapping("/customer/{uniquekey}/{oldPassword}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("uniquekey") String uniqueKey,@PathVariable("oldPassword") String oldPassword ,@Valid @RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.updateCustomer(uniqueKey,oldPassword ,customer),HttpStatus.ACCEPTED) ;
    }

    
    //Delete Customer -> http://localhost:8888/swagger-ui/index.html#/customer-controller/deleteCustomer
    @DeleteMapping("/customer/{uniquekey}")
    public String deleteCustomer(@PathVariable("uniquekey") String uniqueKey) {
        return customerService.deleteCustomer(uniqueKey);
    }

    @GetMapping("/customer/{uniquekey}/alltrips")
    public List<TripBooking> getTripsByCustomer(@PathVariable("uniquekey") String uniqueKey) {
        return customerService.getTripsByCustomer(uniqueKey);
    }

}
