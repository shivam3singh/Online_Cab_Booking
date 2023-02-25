package com.urbancab.service;

import com.urbancab.dao.CabDao;
import com.urbancab.model.Cab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabServiceImpl implements CabService{
    @Autowired
    private CabDao cabDao;

    @Override
    public Cab insertCab(String uniqueKey, Cab cab) {
        return cabDao.insertCab(uniqueKey, cab);
    }

    @Override
    public Cab updateCab(String uniqueKey, Cab cab) {
        return cabDao.updateCab(uniqueKey, cab);
    }

    @Override
    public List<Cab> viewCabsOfType(String uniqueKey, String cabType) {
        return cabDao.viewCabsOfType(uniqueKey, cabType);
    }

    @Override
    public Cab deleteCab(String uniqueKey, Integer cabId) {
        return cabDao.deleteCab(uniqueKey, cabId);
    }

    @Override
    public Integer countCabs(String uniqueKey, String cabType) {
        return cabDao.countCabs(uniqueKey, cabType);
    }
}
