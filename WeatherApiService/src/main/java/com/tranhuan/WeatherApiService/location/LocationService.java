package com.tranhuan.WeatherApiService.location;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tranhuan.WeatherApiCommon.Location;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public Location add(Location location) {
        return locationRepository.save(location);
    }
    public List<Location> getNonTrashedLocations() {
        return locationRepository.getNonTrashedLocations();
    }
    public Location getLocationByCode(String code) {
        return locationRepository.getLocationByCode(code);
    }
}
