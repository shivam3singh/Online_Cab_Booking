package com.urbancab.dao;

import com.urbancab.model.LogInDTO;

public interface LogInDao {
    public String logInUser(LogInDTO logInDTO);
    public String logOutUser(String uniqueKey);
}
