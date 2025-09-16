package com.tranhuan.WeatherApiService.location;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/{code}")
    ResponseEntity<?> getLocationByCode(@PathVariable String code) {
        Location locationByCode = locationService.getLocationByCode(code);
        if (locationByCode==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locationByCode);
    }

    @PutMapping("/{code}")
    ResponseEntity<?> updateLocation(@PathVariable String code, @RequestBody @Valid Location location) {
        try {
            Location updatedLocation = locationService.updateLocation(code, location);
            return ResponseEntity.ok(updatedLocation);
        } catch (LocationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{code}")
    ResponseEntity<?> deleteLocation(@PathVariable String code) {
        try {
            locationService.deleteLocation(code);
            return ResponseEntity.noContent().build();
        } catch (LocationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
