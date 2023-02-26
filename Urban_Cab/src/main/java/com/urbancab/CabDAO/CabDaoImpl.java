package com.urbancab.dao;

import com.urbancab.exception.CabException;
import com.urbancab.exception.UserException;
import com.urbancab.exception.UserSessionException;
import com.urbancab.model.Cab;
import com.urbancab.model.UserSession;
import com.urbancab.repository.AdminRepo;
import com.urbancab.repository.CabRepo;
import com.urbancab.repository.UserSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CabDaoImpl implements CabDao{
    @Autowired
    private UserSessionRepo userSessionRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CabRepo cabRepo;

    @Override
    public Cab insertCab(String uniqueKey, Cab cab) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        return cabRepo.save(cab);
    }

    @Override
    public Cab updateCab(String uniqueKey, Cab cab) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        Cab existingCab = cabRepo.findById(cab.getCabId()).orElseThrow(() -> new CabException("No cab found for id: " + cab.getCabId()));

        existingCab.setDriver(cab.getDriver());
        existingCab.setCabType(cab.getCabType());
        existingCab.setPerKmRate(cab.getPerKmRate());

        return cabRepo.save(existingCab);
    }

    @Override
    public List<Cab> viewCabsOfType(String uniqueKey, String cabType) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        return cabRepo.findByCabType(cabType).orElseThrow(() -> new CabException("No cab found with cab type: " + cabType));
    }

    @Override
    public Cab deleteCab(String uniqueKey, Integer cabId) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        Cab existingCab = cabRepo.findById(cabId).orElseThrow(() -> new CabException("No cab found for id: " + cabId));

        cabRepo.delete(existingCab);

        return existingCab;
    }

    @Override
    public Integer countCabs(String uniqueKey, String cabType) {
        UserSession userSession = userSessionRepo.findByUuid(uniqueKey).orElseThrow(() -> new UserSessionException("User not logged in."));

        adminRepo.findById(userSession.getUserId()).orElseThrow(() -> new UserException("You are not an admin"));

        return cabRepo.countCabByType(cabType);
    }
}
