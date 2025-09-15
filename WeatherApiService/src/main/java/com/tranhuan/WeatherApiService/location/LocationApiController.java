package com.tranhuan.WeatherApiService.location;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranhuan.WeatherApiCommon.Location;

@RestController
@RequestMapping("/v1/locations")
public class LocationApiController {

    private LocationService locationService;

    public LocationApiController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    ResponseEntity<Location>addLocation(Location location) {
        Location addedLocation = locationService.add(location);
        URI uri = URI.create("/v1/locations" + location.getCode());
        return ResponseEntity.created(uri).body(addedLocation);
    }
}
