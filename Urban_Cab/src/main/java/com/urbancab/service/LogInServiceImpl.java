package com.urbancab.service;

import com.urbancab.dao.LogInDao;
import com.urbancab.model.LogInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImpl implements LogInService{

    @Autowired
    private LogInDao logInDao;

    @Override
    public String logInUser(LogInDTO logInDTO) {
        return logInDao.logInUser(logInDTO);
    }

    @Override
    public String logOutUser(String uniqueKey) {
        return logInDao.logOutUser(uniqueKey);
    }
}
