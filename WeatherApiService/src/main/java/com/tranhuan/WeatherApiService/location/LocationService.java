package com.tranhuan.WeatherApiService.location;

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
}
