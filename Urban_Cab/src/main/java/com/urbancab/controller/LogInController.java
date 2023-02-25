package com.urbancab.controller;

import com.urbancab.model.LogInDTO;
import com.urbancab.service.LogInService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/urban_cab/users")
public class LogInController {

    @Autowired
    private LogInService logInService;

    @PostMapping("/login")
    public ResponseEntity<String> logInUser(@Valid @RequestBody LogInDTO logInDTO) {
        String logInDetails = logInService.logInUser(logInDTO);

        return new ResponseEntity<>(logInDetails, HttpStatus.ACCEPTED);
    }

    @GetMapping("/logout/{uniquekey}")
    public ResponseEntity<String> logOutUser(@PathVariable("uniquekey") String uniqueKey) {
        String logOutDetails = logInService.logOutUser(uniqueKey);

        return new ResponseEntity<>(logOutDetails, HttpStatus.OK);
    }
}
