package com.urbancab.dao;

import com.urbancab.model.Cab;

import java.util.List;

public interface CabDao {
    public Cab insertCab(String uniqueKey, Cab cab);

    public Cab updateCab(String uniqueKey,Cab cab);

    public List<Cab> viewCabsOfType(String uniqueKey, String cabType);

    public Cab deleteCab(String uniqueKey,Integer cabId);

    public Integer countCabs(String uniqueKey, String cabType);
}
