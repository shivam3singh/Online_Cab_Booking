package com.urbancab.controller;

import com.urbancab.model.Driver;
import com.urbancab.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;


    @PostMapping("/")
    public Driver insertADriver(@Valid @RequestBody Driver driver) {
        return driverService.insertADriver(driver);
    }

    @PutMapping("/{uniquekey}")
    public Driver updateADriver(@PathVariable("uniquekey") String uniqueKey, @Valid @RequestBody Driver driver) {
        return driverService.updateADriver(uniqueKey, driver);
    }

    @DeleteMapping("/{uniquekey}")
    public String deleteADriver(@PathVariable("uniquekey") String uniqueKey, @RequestParam("driverId") Integer driverId) {
        return driverService.deleteADriver(uniqueKey, driverId);
    }

    @GetMapping("/{uniquekey}/allbest")
    public List<Driver> viewAllBestDriver(@PathVariable("uniquekey") String uniqueKey) {
        return driverService.viewAllBestDriver(uniqueKey);
    }

    @GetMapping("/{uniquekey}")
    public Driver viewADriver(@PathVariable("uniquekey") String uniqueKey, @RequestParam("driverId") Integer driverId) {
        return driverService.viewADriver(uniqueKey, driverId);
    }
}
