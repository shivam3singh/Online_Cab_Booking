package com.urbancab.controller;

import com.urbancab.model.Cab;
import com.urbancab.service.CabService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins/{uniquekey}/cabs")
public class CabController {
    @Autowired
    private CabService cabService;

    @PostMapping("/")
    public ResponseEntity<Cab> insertCab(@PathVariable("uniquekey") String uniqueKey, @Valid @RequestBody Cab cab) {
        return new ResponseEntity<>(cabService.insertCab(uniqueKey, cab), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Cab> updateCab(@PathVariable("uniquekey") String uniqueKey, @Valid @RequestBody Cab cab){
        return new ResponseEntity<>(cabService.updateCab(uniqueKey, cab), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cab>> viewCabsOfType(@PathVariable("uniquekey") String uniqueKey, @RequestParam("cabType") String cabType) {
        return new ResponseEntity<>(cabService.viewCabsOfType(uniqueKey, cabType), HttpStatus.FOUND);
    }

    @DeleteMapping("/")
    public ResponseEntity<Cab> deleteCab(@PathVariable("uniquekey") String uniqueKey, @RequestParam("cabId") Integer cabId) {
        return new ResponseEntity<>(cabService.deleteCab(uniqueKey, cabId), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countCabs(@PathVariable("uniquekey") String uniqueKey, @RequestParam("cabType") String cabType) {
        return new ResponseEntity<>(cabService.countCabs(uniqueKey, cabType), HttpStatus.FOUND);
    }
}
