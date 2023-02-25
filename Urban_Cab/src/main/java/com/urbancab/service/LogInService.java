package com.urbancab.service;

import com.urbancab.model.LogInDTO;
import org.springframework.stereotype.Service;

public interface LogInService {
    public String logInUser(LogInDTO logInDTO);
    public String logOutUser(String uniqueKey);
}
