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

    public Location updateLocation(String code, Location location) throws LocationNotFoundException {
        Location locationFromDb = locationRepository.getLocationByCode(code);
        if (locationFromDb==null) {
            throw new LocationNotFoundException("No location found with the given code: " + code);
        }
        locationFromDb.setCityName(location.getCityName());
        locationFromDb.setCountryName(location.getCountryName());
        locationFromDb.setCountryCode(location.getCountryCode());
        locationFromDb.setRegionName(location.getRegionName());
        locationFromDb.setEnabled(location.isEnabled());
        return locationRepository.save(locationFromDb);
    }
    public void deleteLocation(String code) throws LocationNotFoundException {

        Location locationFromDb = locationRepository.getLocationByCode(code);
        if (locationFromDb==null) {
            throw new LocationNotFoundException("No location found with the given code: " + code);
        }
        locationRepository.trashLocationByCode(code);
    }
}

