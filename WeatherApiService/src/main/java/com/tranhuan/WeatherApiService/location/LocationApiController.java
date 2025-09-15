package com.tranhuan.WeatherApiService.location;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranhuan.WeatherApiCommon.Location;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/locations")
public class LocationApiController {

    private LocationService locationService;

    public LocationApiController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    ResponseEntity<Location>addLocation(@RequestBody @Valid Location location) {
        Location addedLocation = locationService.add(location);
        URI uri = URI.create("/v1/locations/" + location.getCode());
        return ResponseEntity.created(uri).body(addedLocation);
    }

    @GetMapping
    ResponseEntity<?> getNonTrashedLocations() {
        List<Location> nonTrashedLocations = locationService.getNonTrashedLocations();
        if(nonTrashedLocations.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(nonTrashedLocations);
        }
    }
}
