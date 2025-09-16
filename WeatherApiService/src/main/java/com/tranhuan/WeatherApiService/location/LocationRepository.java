package com.tranhuan.WeatherApiService.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tranhuan.WeatherApiCommon.Location;


public interface LocationRepository extends JpaRepository<Location, String> {

    @Query("SELECT l FROM Location l WHERE l.trashed = false")
    List<Location> getNonTrashedLocations();

    @Query("SELECT l FROM Location l WHERE l.trashed = false AND l.code = ?1")
    Location getLocationByCode(String code);

    @Modifying
    @Transactional
    @Query("UPDATE Location SET trashed = true WHERE code = ?1")
    void trashLocationByCode(String code);
}
