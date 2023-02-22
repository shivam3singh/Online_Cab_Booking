package com.urbancab.dao;

import com.urbancab.exception.LogInException;
import com.urbancab.exception.LogOutException;
import com.urbancab.model.*;
import com.urbancab.repository.AdminRepo;
import com.urbancab.repository.CustomerRepo;
import com.urbancab.repository.DriverRepo;
import com.urbancab.repository.UserSessionRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class LogInDaoImpl implements LogInDao {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private UserSessionRepo userSessionRepo;

    @Override
    public String logInUser(LogInDTO logInDTO) {
        Admin admin = adminRepo.findByUsername(logInDTO.getUserName()).get();

        Customer customer = customerRepo.findByUsername(logInDTO.getUserName()).get();

        Driver driver = driverRepo.findByUsername(logInDTO.getUserName()).get();

        if (admin != null) {
            return logInAdmin(admin, logInDTO);
        } else if (customer != null) {
            return logInCustomer(customer, logInDTO);
        } else if (driver != null) {
            return logInDriver(driver, logInDTO);
        } else {
            throw new LogInException("User not registered with username: " + logInDTO.getUserName());
        }
    }

    private String logInAdmin(Admin admin, LogInDTO logInDTO) {
        Optional<UserSession> userSession = userSessionRepo.findById(logInDTO.getUserName());

        if (userSession.isPresent())
            throw new LogInException("Admin already logged in with username: " + logInDTO.getUserName());

        if (admin.getPassword().equals(logInDTO.getPassword())) {
            String key = RandomString.make(6);

            String localDateTime = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss").format(LocalDateTime.now());

            key += localDateTime;

            UserSession currentUserSession = new UserSession(logInDTO.getUserName(), admin.getAdminId(), key, LocalDateTime.now());

            userSessionRepo.save(currentUserSession);

            return currentUserSession.toString();
        } else {
            throw new LogInException("Incorrect password entered");
        }
    }

    private String logInCustomer(Customer customer, LogInDTO logInDTO) {
        Optional<UserSession> userSession = userSessionRepo.findById(logInDTO.getUserName());

        if (userSession.isPresent())
            throw new LogInException("Customer already logged in with username: " + logInDTO.getUserName());

        if (customer.getPassword().equals(logInDTO.getPassword())) {
            String key = RandomString.make(6);

            String localDateTime = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss").format(LocalDateTime.now());

            key += localDateTime;

            UserSession currentUserSession = new UserSession(logInDTO.getUserName(), customer.getCustomerId(), key, LocalDateTime.now());

            userSessionRepo.save(currentUserSession);

            return currentUserSession.toString();
        } else {
            throw new LogInException("Incorrect password entered");
        }
    }

    private String logInDriver(Driver driver, LogInDTO logInDTO) {
        Optional<UserSession> userSession = userSessionRepo.findById(logInDTO.getUserName());

        if (userSession.isPresent())
            throw new LogInException("Driver already logged in with username: " + logInDTO.getUserName());

        if (driver.getPassword().equals(logInDTO.getPassword())) {
            String key = RandomString.make(6);

            String localDateTime = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss").format(LocalDateTime.now());

            key += localDateTime;

            UserSession currentUserSession = new UserSession(logInDTO.getUserName(), driver.getDriverId(), key, LocalDateTime.now());

            userSessionRepo.save(currentUserSession);

            return currentUserSession.toString();
        } else {
            throw new LogInException("Incorrect password entered");
        }
    }

    @Override
    public String logOutUser(String uniqueKey) {
            UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new LogOutException("User has been logged out already."));

            userSessionRepo.delete(userSession);

            return "User logged out successfully.";
    }
}
